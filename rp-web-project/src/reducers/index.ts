import { reducer as FormReducer } from 'redux-form';
import { TopReducer } from './TopReducer';

/**
 * reducer一覧
 */
export const reducers = {
  form: FormReducer,
  top: TopReducer
};
