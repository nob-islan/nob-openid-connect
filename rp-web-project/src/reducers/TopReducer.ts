import { TopState, initTopState } from '../states/TopState';

/**
 * ログイン後Top画面のreducerです。
 */
export const TopReducer = (
  state: TopState = initTopState,
  action: any
): TopState => {
  switch (action.type) {
    default:
      return state;
  }
};
