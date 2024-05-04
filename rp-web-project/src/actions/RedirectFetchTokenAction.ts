import axios from 'axios';
import UrlConst from '../constants/UrlConst';

export const RedirectFetchTokenActionType = {};

/**
 * リクエスト用のペイロード
 */
export type RedirectFetchTokenActionPayload = {
  /**
   * 認可コード
   */
  authorizationCode: string;

  /**
   * 検証用のランダム文字列
   */
  codeVerifier: string;
};

type ValueOf<T> = T[keyof T];

/**
 * リダイレクト時のアクションです。
 */
export type RedirectFetchTokenAction = {
  type: ValueOf<typeof RedirectFetchTokenActionType>;
  payload: RedirectFetchTokenActionPayload;
};

/**
 * アクセストークン取得APIをコールします。
 *
 * @returns
 */
export const fetchToken = (authorizationCode: string, codeVerifier: string) => {
  return async () => {
    const request: RedirectFetchTokenActionPayload = {
      authorizationCode: authorizationCode,
      codeVerifier: codeVerifier
    };
    await axios
      .post(UrlConst.TOKEN_FETCH, request)
      .then((response) => redirectTop());
  };
};

const redirectTop = () => {
  // TODO アクセストークン管理
  // トップ画面へリダイレクト
  window.location.href = '/top';
};
