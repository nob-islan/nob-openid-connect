class UrlConst {
  /**
   * OpenIDプロバイダAPIのドメイン
   */
  static readonly OP_APP_DOMAIN = 'http://localhost:8081';

  /**
   * OpenIDプロバイダAPIのベースURL
   */
  static readonly OP_APP_BASE_URL = '/api/op';

  /**
   * 認可API
   */
  static readonly AUTHORIZATION =
    this.OP_APP_DOMAIN + this.OP_APP_BASE_URL + '/authorization';

  /**
   * 認証API
   */
  static readonly CERTIFICATION =
    this.OP_APP_DOMAIN + this.OP_APP_BASE_URL + '/certification';
}

export default UrlConst;
