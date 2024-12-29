package com.example.op_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.op_project.repository.entity.AuthorizationInfo;

/**
 * authorization_infoテーブルのrepositoryインターフェースクラスです。
 * 
 * @author nob
 */
@Repository
public interface AuthorizationInfoRepository extends JpaRepository<AuthorizationInfo, Integer> {

    /**
     * 認可コードから認可情報を抽出します。
     * 
     * @param code 認可コード
     * @return 認可情報
     */
    @Query(value = "SELECT * FROM authorization_info WHERE code = ?1 AND is_deleted = false", nativeQuery = true)
    AuthorizationInfo selectByCode(String code); // TODO このメソッド自体省略できないかドキュメントを確認
}
