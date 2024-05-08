import axios from 'axios';
import Cookies from 'browser-cookies';
import UrlConst from '../constants/UrlConst';
import { loginFormName } from '../components/login/Login';
import { formValueSelector } from 'redux-form';
import { store } from '..';

export const LoginActionType = {};

/**
 * リクエスト用のペイロード
 */
export type LoginActionPayload = {
  /**
   *ユーザID
   */
  userId: string;

  /**
   * パスワード
   */
  password: string;

  /**
   * リダイレクトURI
   */
  redirectUri: string;
};

type ValueOf<T> = T[keyof T];

/**
 * ログイン時のアクションです。
 */
export type LoginAction = {
  type: ValueOf<typeof LoginActionType>;
  payload: LoginActionPayload;
};

/**
 * 認可APIをコールします。
 *
 * @param redirectUri
 * @param codeChallenge
 * @param codeChallengeMethod
 * @returns
 */
export const authorize = (
  clientId: string,
  redirectUri: string,
  codeChallenge: string,
  codeChallengeMethod: string
) => {
  // クエリパラメータ作成
  const queryParam =
    '?clientId=' +
    clientId +
    '&redirectUri=' +
    redirectUri +
    '&codeChallenge=' +
    codeChallenge +
    '&codeChallengeMethod=' +
    codeChallengeMethod;
  // 認可APIをコールし、例外が発生しなければ描画前処理を行いログイン画面を表示
  return async () => {
    await axios
      .get(UrlConst.AUTHORIZATION + queryParam)
      .then(() => preDrawLogin(codeChallenge));
  };
};

/**
 * ログイン画面描画前の処理です。
 */
const preDrawLogin = (codeChallenge: string) => {
  Cookies.set('codeChallenge', codeChallenge);
};

/**
 * 認証APIをコールしてユーザID, パスワードを検証します。
 *
 * @returns
 */
export const certification = (redirectUri: string) => {
  const selector = formValueSelector(loginFormName);
  return async () => {
    const request: LoginActionPayload = {
      userId: selector(store.getState(), 'userId'),
      password: selector(store.getState(), 'password'),
      redirectUri: redirectUri
    };
    await axios
      .post(UrlConst.CERTIFICATION, request, { withCredentials: true })
      .then((response) =>
        redirectToRedirectUri(
          response.data.redirectUri,
          response.data.authorizationCode
        )
      );
    // TODO 例外処理
  };
};

/**
 * 認可コードをクエリパラメータに付与して指定されたリダイレクトURIにリダイレクトします。
 *
 * @param redirectUri
 * @param authorizationCode
 */
const redirectToRedirectUri = (
  redirectUri: string,
  authorizationCode: string
) => {
  // クエリパラメータ作成
  const queryParam = '?authorizationCode=' + authorizationCode;
  // リダイレクト
  window.location.href = redirectUri + queryParam;
};
