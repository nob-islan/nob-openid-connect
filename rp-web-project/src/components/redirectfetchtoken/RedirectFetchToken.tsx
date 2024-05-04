import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useLocation } from 'react-router-dom';
import { fetchToken } from '../../actions/RedirectFetchTokenAction';
import { store } from '../..';
import { useCookies } from 'react-cookie';

interface Props {}

/**
 * アクセストークン取得APIをコールするリダイレクト用の画面です。
 *
 * @param props
 * @returns
 */
const RedirectFetchToken: React.FC<Props> = (props) => {
  const search: string = useLocation().search;
  const dispatch = useDispatch();
  const [cookies] = useCookies(['codeVerifier']);
  /**
   * 初期表示処理です。
   */
  useEffect(() => {
    // リダイレクトURLを取得
    const query = new URLSearchParams(search) || '';
    const authorizationCode = query.get('authorizationCode') || '';
    const codeVerifier = cookies.codeVerifier;
    // 認可エンドポイントをコール
    store.dispatch(fetchToken(authorizationCode, codeVerifier));
  }, [cookies.codeVerifier, dispatch, search]);

  return <>リダイレクト中…</>;
};

export default RedirectFetchToken;
