import styles from './LoginTop.module.scss';

const LoginTop = () => {
  return (
    <div>
      <div className={styles.beginningMessage}>Nob OpenID Connect</div>
      <table>
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
      </table>
      <div className={styles.buttonWrapper}>
        <button className={styles.button}>ログイン</button>
      </div>
    </div>
  );
};

export default LoginTop;
