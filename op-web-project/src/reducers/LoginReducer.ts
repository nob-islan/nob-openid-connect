import { Reducer } from 'redux';
import { LoginState, initLoginState } from '../states/LoginState';
import { LoginAction, LoginActionType } from '../actions/LoginAction';

export const LoginReducer: Reducer<LoginState, LoginAction> = (
  state: LoginState = initLoginState,
  action: LoginAction
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
