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
 * ユーザID, パスワードを検証します。
 *
 * @returns
 */
export const verifyCredential = () => {
  const selector = formValueSelector(loginFormName);
  console.log('開始');
  return async () => {
    // TODO クエリパラメータからリダイレクトURIを取得してリクエストを作成
    // const search: string = useLocation().search;
    // const query = new URLSearchParams(search);
    const request: LoginActionPayload = {
      userId: selector(store.getState(), 'userId'),
      password: selector(store.getState(), 'password'),
      redirectUri: 'sample'
    };
    const response: any = await axios.post(UrlConst.CERTIFICATION, request);
    console.log(response); // TODO 検証用ログ削除
  };
};
