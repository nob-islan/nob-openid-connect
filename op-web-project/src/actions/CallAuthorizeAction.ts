import axios from 'axios';
import UrlConst from '../constants/UrlConst';

export const CallAuthorizeActionType = {};

/**
 * リクエスト用のペイロード
 */
export type CallAuthorizeActionPayload = {};

type ValueOf<T> = T[keyof T];

export type CallAuthorizeAction = {
  type: ValueOf<typeof CallAuthorizeActionType>;
  payload: CallAuthorizeActionPayload;
};

/**
 * 認可APIをコールします。
 *
 * @param redirectUri
 * @param codeChallenge
 * @param codeChallengeMethod
 * @returns
 */
export const authorize = (
  redirectUri: string,
  codeChallenge: string,
  codeChallengeMethod: string
) => {
  // クエリパラメータ
  const queryParam =
    '?redirectUri=' +
    redirectUri +
    '&codeChallenge=' +
    codeChallenge +
    '&codeChallengeMethod=' +
    codeChallengeMethod;
  // 認可APIをコールし、例外が発生しなければログイン画面に遷移
  return async () => {
    await axios
      .get(UrlConst.AUTHORIZATION + queryParam)
      .then(
        (response) =>
          (window.location.href =
            UrlConst.LOGIN +
            '?redirectUri=' +
            encodeURIComponent(response.data.redirectUri))
      );
  };
};
