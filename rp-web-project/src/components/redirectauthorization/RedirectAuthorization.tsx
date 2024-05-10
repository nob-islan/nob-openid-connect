import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { store } from '../..';
import { redirectAuthorization } from '../../actions/RedirectAuthorizationAction';
import ParamConst from '../../constants/ParamConst';

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
    // 認可エンドポイントへのリダイレクトAPIをコール
    store.dispatch(
      redirectAuthorization(ParamConst.CLIENT_ID, ParamConst.REDIRECT_URI)
    );
  }, [search]);

  return <>認証開始</>;
};

export default RedirectAuthorization;
