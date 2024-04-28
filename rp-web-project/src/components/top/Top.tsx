import { useSelector } from 'react-redux';
import { RootState } from '../..';
import { TopState } from '../../states/TopState';

interface Props {}

/**
 * ログイン後トップ画面です。
 *
 */
const Top: React.FC<Props> = (props) => {
  const userName: string = useSelector<RootState, TopState['userName']>(
    (state: RootState) => state.top.userName
  );

  return <>ようこそ、{userName}さん！</>;
};

export default Top;
