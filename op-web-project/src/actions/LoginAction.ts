import axios from 'axios';
import UrlConst from '../constants/UrlConst';
import { loginFormName } from '../components/login/Login';
import { formValueSelector } from 'redux-form';
import { store } from '..';

export const LoginActionType = {
  /**
   * リダイレクトURIを保持します。
   */
  UPDATE_REDIRECT_URI: 'UPDATE_REDIRECT_URI'
};

/**
 * リクエスト用のペイロード
 */
export type LoginActionPayload = {
  /**
   *ユーザID
   */
  userId?: string;

  /**
   * パスワード
   */
  password?: string;

  /**
   * リダイレクトURI
   */
  redirectUri?: string;
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
 * リダイレクトURIを保持します。
 */
export const updateRedirectUri = (redirectUri: string): LoginAction => ({
  type: LoginActionType.UPDATE_REDIRECT_URI,
  payload: { redirectUri: redirectUri }
});

/**
 * ユーザID, パスワードを検証します。
 *
 * @returns
 */
export const verifyCredential = () => {
  const selector = formValueSelector(loginFormName);
  return async () => {
    const request: LoginActionPayload = {
      userId: selector(store.getState(), 'userId'),
      password: selector(store.getState(), 'password'),
      redirectUri: store.getState().login.redirectUri
    };
    await axios
      .post(UrlConst.CERTIFICATION, request)
      .then((response) => redirectFetchToken(response.data));
  };
};

/**
 * アクセストークン取得APIへのリダイレクトを行います。
 *
 * @param redirectUri
 */
const redirectFetchToken = (responseData: any) => {
  // TODO クエリパラメータ付与　そもそも画面まで戻すかも検討　codeVerifierの扱いとか
  window.location.href = 'http://localhost:8080/api/rp/token/fetch';
};
