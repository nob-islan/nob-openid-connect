import axios from 'axios';
import UrlConst from '../constants/UrlConst';

export const SigninActionType = {};

/**
 * リクエスト用のペイロード
 */
export type SigninActionPayload = {
  redirectUri: string;
};

type ValueOf<T> = T[keyof T];

/**
 * 認証開始時のアクションです。
 */
export type SigninAction = {
  type: ValueOf<typeof SigninActionType>;
  payload: SigninActionPayload;
};

/**
 * 認可エンドポイントへのリダイレクトAPIをコールします。
 *
 * @param redirectUri
 * @returns
 */
export const redirectAuthorization = (redirectUri: string) => {
  return async () => {
    const params: SigninActionPayload = {
      redirectUri: redirectUri
    };
    await axios(UrlConst.AUTHORIZATION, { params: params }).then((response) =>
      redirectLogin(response)
    );
  };
};

/**
 * ログイン画面へリダイレクトします。
 */
const redirectLogin = (response: any) => {
  const queryParam = '?redirectUri=' + response.data.redirectUri;
  window.location.href = UrlConst.LOGIN + queryParam;
};
