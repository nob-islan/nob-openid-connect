import { useEffect } from 'react';
import { store } from '../..';
import { authorize } from '../../actions/RedirectAuthorizeAction';
import { useLocation } from 'react-router-dom';

interface Props {}

/**
 * 認可APIをコールするリダイレクト用の画面です。
 *
 * @param props
 * @returns
 */
const RedirectAuthorize: React.FC<Props> = (props) => {
  const search: string = useLocation().search;

  /**
   * 初期表示処理です。
   */
  useEffect(() => {
    // リダイレクトURIを取得
    const query = new URLSearchParams(search) || '';
    const redirectUri = query.get('redirectUri') || '';
    const codeChallenge = query.get('codeChallenge') || '';
    const codeChallengeMethod = query.get('codeChallengeMethod') || '';
    // 認可APIをコール
    store.dispatch(authorize(redirectUri, codeChallenge, codeChallengeMethod));
  }, [search]);

  return <>リダイレクト中…</>;
};

export default RedirectAuthorize;
