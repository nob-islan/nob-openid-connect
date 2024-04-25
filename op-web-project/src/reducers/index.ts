import { reducer as FormReducer } from 'redux-form';
import { LoginReducer } from './LoginReducer';

/**
 * reducer一覧
 */
export const reducers = {
  form: FormReducer,
  login: LoginReducer
};
