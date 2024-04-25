import styles from './Login.module.scss';
import { verifyCredential } from '../../actions/LoginAction';
import { store } from '../..';
import { Field, reduxForm } from 'redux-form';

interface Props {}

export const loginFormName = 'LoginForm';

/**
 * 認証・ユーザ情報提供画面です。
 *
 */
const Login: React.FC<Props> = (props) => {
  // TODO モックサーバ

  /**
   * 認証APIをコールします。
   */
  const submit = () => {
    store.dispatch(verifyCredential());
  };

  return (
    <div>
      <div className={styles.beginningMessage}>Nob OpenID Connect</div>
      <form onSubmit={submit}>
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
        <div className={styles.buttonWrapper}>
          <button className={styles.button} type="submit">
            ログイン
          </button>
        </div>
      </form>
    </div>
  );
};

export default reduxForm({
  form: loginFormName
})(Login);
