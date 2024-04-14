import styles from './Login.module.scss';
import { useDispatch } from 'react-redux';
import {
  updatePassword,
  updateUserId,
  verifyCredential
} from '../../actions/LoginAction';
import { store } from '../..';

interface Props {}

/**
 * 認証・ユーザ情報提供画面です。
 *
 */
const Login: React.FC<Props> = (props) => {
  const dispatch = useDispatch();

  // TODO モックサーバ

  /**
   * 認証APIをコールします。
   */
  const submit = () => {
    store.dispatch(verifyCredential());
  };

  /**
   * ユーザIDを入力された値で更新します。
   *
   * @param event
   */
  const handleOnChangeUserId = (event: any) => {
    dispatch(updateUserId(event.target.value));
  };

  /**
   * パスワードを入力された値で更新します。
   *
   * @param event
   */
  const handleOnChangePassword = (event: any) => {
    dispatch(updatePassword(event.target.value));
  };

  return (
    <div>
      <div className={styles.beginningMessage}>Nob OpenID Connect</div>
      <table>
        <tbody>
          <tr>
            <td align="left">ユーザID:</td>
            <td>
              <input
                type="text"
                id="userId"
                onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                  handleOnChangeUserId(e)
                }
              ></input>
            </td>
          </tr>
          <tr>
            <td align="left">パスワード:</td>
            <td>
              <input
                type="password"
                id="password"
                onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                  handleOnChangePassword(e)
                }
              ></input>
            </td>
          </tr>
        </tbody>
      </table>
      <div className={styles.buttonWrapper}>
        <button className={styles.button} onClick={submit}>
          ログイン
        </button>
      </div>
    </div>
  );
};

export default Login;
