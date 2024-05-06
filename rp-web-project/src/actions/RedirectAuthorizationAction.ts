import Cookies from 'browser-cookies';
import UrlConst from '../constants/UrlConst';

export const RedirectAuthorizationActionType = {};

/**
 * リクエスト用のペイロード
 */
export type RedirectAuthorizationActionPayload = {
  redirectUri: string;
};

type ValueOf<T> = T[keyof T];

/**
 * 認証開始時のアクションです。
 */
export type SigninAction = {
  type: ValueOf<typeof RedirectAuthorizationActionType>;
  payload: RedirectAuthorizationActionPayload;
};

/**
 * 認可エンドポイントリダイレクト画面に遷移します。
 *
 * @param redirectUri
 * @returns
 */
export const redirectAuthorization = (
  clientId: string,
  redirectUri: string
) => {
  return async () => {
    const codeChallengeMethod: string = 'S256';
    // codeVerifierを生成してCookieに保持
    const codeVerifier: string = createCodeVerifer();
    Cookies.set('codeVerifier', codeVerifier);
    // codeChallengeを計算
    const codeChallenge: string = calcCodeChallenge(codeVerifier);
    // クエリパラメータ作成
    const queryParam =
      '?clientId=' +
      clientId +
      '&redirectUri=' +
      redirectUri +
      '&codeChallenge=' +
      codeChallenge +
      '&codeChallengeMethod=' +
      codeChallengeMethod;
    window.location.href = UrlConst.LOGIN + queryParam;
  };
};

/**
 * codeVerifierを生成します。
 *
 * @returns
 */
const createCodeVerifer = (): string => {
  // codeVerifierの文字列長を決定
  const minLength: number = 43;
  const maxLength: number = 128;
  const length: number =
    Math.floor(Math.random() * (maxLength - minLength + 1)) + minLength;
  // ランダム文字列を生成
  const chars: string =
    '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
  let codeVerifier = '';
  for (let i = 0; i < length; i++) {
    codeVerifier += chars.charAt(Math.floor(Math.random() * chars.length));
  }

  return codeVerifier;
};

/**
 * codeChallengeを計算します。
 *
 * @param codeVerifier
 * @returns
 */
const calcCodeChallenge = (codeVerifier: string): string => {
  const cryptoJS = require('crypto-js');
  const hash = cryptoJS.SHA256(codeVerifier);
  const codeChallenge = hash.toString(cryptoJS.enc.Hex);

  return codeChallenge;
};
