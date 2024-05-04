import axios from 'axios';
import UrlConst from '../constants/UrlConst';

export const RedirectAuthorizeActionType = {};

/**
 * リクエスト用のペイロード
 */
export type RedirectAuthorizeActionPayload = {};

type ValueOf<T> = T[keyof T];

export type RedirectAuthorizeAction = {
  type: ValueOf<typeof RedirectAuthorizeActionType>;
  payload: RedirectAuthorizeActionPayload;
};

// 認可APIをコールします。
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
  return async () => {
    await axios
      .get(UrlConst.AUTHORIZATION + queryParam)
      .then(
        (response) =>
          (window.location.href = UrlConst.LOGIN + response.data.redirectUri)
      );
  };
};
