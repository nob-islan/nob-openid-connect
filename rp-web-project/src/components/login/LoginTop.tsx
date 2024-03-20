import styles from './LoginTop.module.scss';

const LoginTop = () => {
  return (
    <div>
      <div className={styles.beginningMessage}>
        ユーザID, パスワードを入力してください：
      </div>
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
      <button>ログイン</button>
    </div>
  );
};

export default LoginTop;
