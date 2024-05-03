export const RedirectFetchTokenActionType = {};

/**
 * リクエスト用のペイロード
 */
export type RedirectFetchTokenActionPayload = {};

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
export const callFetchToken = () => {
  return async () => {}; // TODO 実装
};
