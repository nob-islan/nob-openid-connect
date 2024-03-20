class UrlConst {

    /**
     * OpenIDプロバイダAPIのドメイン
     */
    static readonly OP_DOMAIN = 'http://localhost:8080';

    /**
     * OpenIDプロバイダAPIのベースURL
     */
    static readonly OP_BASE_URL = '/api/op';

    static readonly CERTIFICATION = this.OP_DOMAIN + this.OP_BASE_URL + '/certification';
}

export default UrlConst;