import styles from './Login.module.scss';
import { certification } from '../../actions/LoginAction';
import { store } from '../..';
import { Field, reduxForm } from 'redux-form';
import { useLocation } from 'react-router-dom';

interface Props {}

export const loginFormName = 'LoginForm';

/**
 * 認証・ユーザ情報提供画面です。
 *
 */
const Login: React.FC<Props> = (props) => {
  const search: string = useLocation().search;

  /**
   * 認証APIをコールします。
   */
  const submit = () => {
    // リダイレクトURIを取得
    const query = new URLSearchParams(search) || '';
    const redirectUri = query.get('redirectUri') || '';
    // 認証APIをコール
    store.dispatch(certification(redirectUri));
  };

  return (
    <div>
      <div className={styles.beginningMessage}>Nob OpenID Connect</div>
      <form>
        <table>
          <tbody>
            <tr>
              <td align="left">ユーザID:</td>
              <td>
                <Field name="userId" component="input" type="text" />
              </td>
            </tr>
            <tr>
              <td align="left">パスワード:</td>
              <td>
                <Field name="password" component="input" type="password" />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <div className={styles.buttonWrapper}>
        <button className={styles.button} onClick={submit}>
          ログイン
        </button>
      </div>
    </div>
  );
};

export default reduxForm({
  form: loginFormName
})(Login);
