package com.example.op_project.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * token_managementテーブル向けのエンティティクラスです。
 * 
 * @author nob
 */
@Entity
@Table(name = "token_management")
@Data
public class TokenManagement {

    /**
     * クライアントID
     */
    @Id
    @Column(length = 15, nullable = false)
    private String clientId;

    /**
     * SHA256のシークレットキー
     */
    @Column(length = 30, nullable = false)
    private String hmacKey;
}
