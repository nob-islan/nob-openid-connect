package com.example.op_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.op_project.repository.entity.ClientInfo;

/**
 * client_infoテーブルのrepositoryインターフェースクラスです。
 * 
 * @author nob
 */
@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, String> {

    /**
     * クライアントIDからクライアント情報を抽出します。
     * 
     * @param clientId クライアントID
     * @return クライアント情報
     */
    @Query(value = "SELECT * FROM client_info WHERE client_id = ?1", nativeQuery = true)
    ClientInfo selectByClientId(String clientId); // TODO このメソッド自体省略できないかドキュメントを確認
}
