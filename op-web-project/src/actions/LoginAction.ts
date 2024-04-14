import axios from 'axios';
import UrlConst from '../constants/UrlConst';
import { Dispatch } from 'redux';

export const LoginActionType = {
  /**
   * ユーザIDを入力された値で更新します。
   */
  UPDATE_USER_ID: 'UPDATE_USER_ID',

  /**
   * パスワードを入力された値で更新します。
   */
  UPDATE_PASSWORD: 'UPDATE_PASSWORD'
};

/**
 * リクエスト用のペイロード
 */
export type LoginStatePayload = {
  /**
   *ユーザID
   */
  userId?: string;

  /**
   * パスワード
   */
  password?: string;
};

type ValueOf<T> = T[keyof T];

/**
 * ログイン時のアクションです。
 */
export type LoginAction = {
  type: ValueOf<typeof LoginActionType>;
  payload: LoginStatePayload;
};

/**
 * ユーザIDを入力された値で更新します。
 *
 * @returns
 */
export const updateUserId = (inputUserId: string): LoginAction => ({
  type: LoginActionType.UPDATE_USER_ID,
  payload: { userId: inputUserId }
});

/**
 * パスワードを入力された値で更新します。
 *
 * @returns
 */
export const updatePassword = (inputPassword: string): LoginAction => ({
  type: LoginActionType.UPDATE_PASSWORD,
  payload: { password: inputPassword }
});

/**
 * ユーザID, パスワードを検証します。
 *
 * @returns
 */
export const verifyCredential = () => {
  return async (dispatch: Dispatch) => {
    const request = {
      userId: 'testUserId',
      password: 'testPassword'
    };
    const response = await axios.post(UrlConst.CERTIFICATION, request);
    console.log(response);
  };
};
