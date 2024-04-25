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
  return async () => {
    const request: LoginActionPayload = {
      userId: selector(store.getState(), 'userId'),
      password: selector(store.getState(), 'password')
    };
    const response = await axios.post(UrlConst.CERTIFICATION, request);
    console.log(response); // TODO 検証用ログ削除
  };
};
