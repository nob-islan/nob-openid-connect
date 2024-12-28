package com.example.op_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.op_project.repository.entity.UserInfo;

/**
 * user_infoテーブルのrepositoryインターフェースクラスです。
 * 
 * @author nob
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    /**
     * ユーザ名、パスワードからユーザ情報を抽出します。
     * 
     * @param username ユーザ名
     * @param password パスワード
     * @return ユーザ情報
     */
    @Query(value = "SELECT * FROM user_info WHERE username = ?1 AND password = ?2", nativeQuery = true)
    UserInfo selectByUserInfo(String username, String password); // TODO このメソッド自体省略できないかドキュメントを確認
}
