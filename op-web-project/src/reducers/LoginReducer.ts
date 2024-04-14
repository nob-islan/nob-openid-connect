import { LoginActionType } from '../actions/LoginAction';
import { LoginState, initLoginState } from '../states/LoginState';

export const LoginReducer = (
  state: LoginState = initLoginState,
  action: any
): LoginState => {
  switch (action.type) {
    /**
     * ユーザIDを入力された値で更新します。
     */
    case LoginActionType.UPDATE_USER_ID:
      return {
        ...state,
        userId: action.payload.userId || ''
      };

    /**
     * パスワードを入力された値で更新します。
     */
    case LoginActionType.UPDATE_PASSWORD:
      return {
        ...state,
        password: action.payload.password || ''
      };

    default:
      return state;
  }
};
