package nob.example.opproject;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;

/**
 * UT実行前の設定を定義するクラスです。
 * 
 * @author nob
 */
public class TestConfig {

    /**
     * テストデータをCSVから読み込んでUT向けの一時DBに投入します。
     * 
     * @param component
     * @param testDataDir
     * @throws Exception
     */
    public static void testDataSetup(String component, String testDataDir) throws Exception {

        // データベース名
        final String DATABASE_NAME = "opdb";
        // プロジェクト名
        final String PROJECT_NAME = "opproject";

        // テスト用csvデータのファイルパス
        final String TESTDATA_BASIC_PATH = "src/test/java/nob/example/" + PROJECT_NAME + "/" + component + "/dbdata/";
        // DBの接続情報
        final String DATABASE_URL = "jdbc:mariadb://localhost:3306/" + DATABASE_NAME;
        final String DATABASE_USER_NAME = "root";
        final String DATABASE_PASSWORD = "password";

        // DBコネクション取得
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME,
                DATABASE_PASSWORD);
        IDatabaseConnection databaseConnection = new MySqlConnection(connection, DATABASE_NAME);

        // csv用データセット作成
        IDataSet dataset = new CsvDataSet(new File(TESTDATA_BASIC_PATH + testDataDir));

        // データの削除、挿入
        DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataset);
    }
}
