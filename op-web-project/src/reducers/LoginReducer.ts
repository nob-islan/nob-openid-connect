import { LoginState, initLoginState } from '../states/LoginState';

/**
 * ログイン画面のreducerです。
 */
export const LoginReducer = (
  state: LoginState = initLoginState,
  action: any
): LoginState => {
  switch (action.type) {
    default:
      return state;
  }
};
