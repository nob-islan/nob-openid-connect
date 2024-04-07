/**
 * 認証・認可用の状態を管理するstateです。
 */
export type LoginState = {
  /**
   *ユーザID
   */
  userId: string;

  /**
   * パスワード
   */
  password: string;
};

/**
 * 初期値
 */
export const initLoginState: LoginState = {
  userId: '',
  password: ''
};
