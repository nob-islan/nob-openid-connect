/**
 * 認証・認可用の状態を管理するstateです。
 */
export type LoginState = {
  /**
   * リダイレクトURI
   */
  redirectUri: string;
};

/**
 * 初期値
 */
export const initLoginState: LoginState = { redirectUri: '' };
