import axios from 'axios';
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
      .post(UrlConst.CERTIFICATION, request)
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
