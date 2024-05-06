import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { store } from '../..';
import { redirectAuthorization } from '../../actions/RedirectAuthorizationAction';

interface Props {}

/**
 * 認証開始画面です。
 *
 * @param props
 * @returns
 */
const RedirectAuthorization: React.FC<Props> = (props) => {
  const search: string = useLocation().search;
  /**
   * 初期表示処理です。
   */
  useEffect(() => {
    // リダイレクトURI、クライアントIDをクエリパラメータから取得
    const query = new URLSearchParams(search) || '';
    const redirectUri = query.get('redirectUri') || '';
    const clientId = query.get('clientId') || '';
    // 認可エンドポイントへのリダイレクトAPIをコール
    store.dispatch(redirectAuthorization(clientId, redirectUri));
  }, [search]);

  return <>認証開始</>;
};

export default RedirectAuthorization;
