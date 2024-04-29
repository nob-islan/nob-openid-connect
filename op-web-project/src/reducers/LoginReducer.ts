import { LoginActionType } from '../actions/LoginAction';
import { LoginState, initLoginState } from '../states/LoginState';

/**
 * ログイン画面のreducerです。
 */
export const LoginReducer = (
  state: LoginState = initLoginState,
  action: any
): LoginState => {
  switch (action.type) {
    /**
     * リダイレクトURIを保持します。
     */
    case LoginActionType.UPDATE_REDIRECT_URI: {
      return {
        ...state,
        redirectUri: action.payload.redirectUri
      };
    }
    default:
      return state;
  }
};
