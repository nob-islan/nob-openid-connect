import { useState } from 'react';
import styles from './LoginTop.module.scss';

/**
 * ログイン画面
 *
 */
const LoginTop = () => {
  /**
   * ユーザ名を管理するstate
   */
  const [userName, setUserName] = useState();

  /**
   * パスワードを管理するstate
   */
  const [password, setPassword] = useState();

  /**
   * ログインAPIをコール
   */
  const submit = () => {
    console.log(userName);
    console.log(password);
  };

  return (
    <div>
      <div className={styles.beginningMessage}>Nob OpenID Connect</div>
      <table>
        <tbody>
          <tr>
            <td align="left">ユーザID:</td>
            <td>
              <input type="text"></input>
            </td>
          </tr>
          <tr>
            <td align="left">パスワード:</td>
            <td>
              <input type="password"></input>
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

export default LoginTop;
