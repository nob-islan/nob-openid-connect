import { LoginState, initLoginState } from '../states/LoginState';

export const LoginReducer = (
  state: LoginState = initLoginState,
  action: any
): LoginState => {
  switch (action.type) {
    default:
      return state;
  }
};
