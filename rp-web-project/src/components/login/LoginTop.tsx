import { useState } from 'react';
import styles from './LoginTop.module.scss';
import axios from 'axios';
import UrlConst from '../../const/UrlConst';

/**
 * ログイン画面です。
 *
 */
const LoginTop = () => {
  /**
   * 入力されたユーザIDを管理するstateです。
   */
  const [userId, setUserId] = useState('');

  /**
   * 入力されたパスワードを管理するstateです。
   */
  const [password, setPassword] = useState('');

  /**
   * 認証の成否を管理するstateです。
   */
  const [isCertificated, setIsCertificated] = useState(false);

  // TODO モックサーバ

  /**
   * 認証APIをコールします。
   */
  const submit = () => {
    const request = {
      userId: userId,
      password: password
    };
    axios.post(UrlConst.CERTIFICATION, request).then((response) => {
      setIsCertificated(response.data);
    });
  };

  /**
   * 入力された値を元にstateを更新します。
   *
   * @param e
   */
  const changeState = (e: React.ChangeEvent<HTMLInputElement>): void => {
    if (e.currentTarget.id === 'userId') {
      setUserId(e.target.value);
    } else if (e.currentTarget.id === 'password') {
      setPassword(e.target.value);
    }
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
                  changeState(e)
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
                  changeState(e)
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

export default LoginTop;
