package com.example.op_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.op_project.repository.entity.TokenManagement;

/**
 * token_managementテーブルのrepositoryインターフェースクラスです。
 * 
 * @author nob
 */
@Repository
public interface TokenManagementRepository extends JpaRepository<TokenManagement, String> {

    /**
     * クライアントIDからトークン情報を抽出します。
     * 
     * @param clientId
     * @return トークン情報
     */
    @Query(value = "SELECT * FROM token_management WHERE client_id = ?1", nativeQuery = true)
    TokenManagement selectByClientId(String clientId);
}
