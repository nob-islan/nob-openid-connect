class UrlConst {
  /**
   * リライングパーティAPIのドメイン
   */
  static readonly RP_APP_DOMAIN = 'http://localhost:8080';

  /**
   * リライングパーティAPIのベースURL
   */
  static readonly RP_APP_BASE_URL = '/api/rp';

  /**
   * 認可エンドポイントへのリダイレクトAPI
   */
  static readonly AUTHORIZATION =
    this.RP_APP_DOMAIN + this.RP_APP_BASE_URL + '/authorization';

  /**
   * アクセストークン取得API
   */
  static readonly TOKEN_FETCH =
    this.RP_APP_DOMAIN + this.RP_APP_BASE_URL + '/token/fetch';

  /**
   * OpenIDプロバイダWebのドメイン
   */
  static readonly OP_WEB_DOMAIN = 'http://localhost:3001';

  /**
   * 認証・ユーザ情報提供同意画面
   */
  static readonly LOGIN = this.OP_WEB_DOMAIN + '/login';
}

export default UrlConst;
