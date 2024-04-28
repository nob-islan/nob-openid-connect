/**
 * ログイン後トップ画面の状態を管理するstateです。
 */
export type TopState = {
  /**
   * ユーザ名
   */
  userName: string;
};

/**
 * 初期値
 */
export const initTopState: TopState = {
  userName: 'nob'
};
