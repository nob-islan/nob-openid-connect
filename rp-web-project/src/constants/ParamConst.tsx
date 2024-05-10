/**
 * クエリパラメータに付与する値を管理するクラスです。
 */
class ParamConst {
  /**
   * OPから発行されたクライアントID
   */
  static readonly CLIENT_ID = 'nobClient';

  /**
   * 認可レスポンスを受け取った後のリダイレクト先
   */
  static readonly REDIRECT_URI =
    'http%3A%2F%2Flocalhost%3A3000%2Fredirect%2Ffetchtoken';
}

export default ParamConst;
