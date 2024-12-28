package com.example.op_project.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * client_infoテーブル向けのエンティティクラスです。
 * 
 * @author nob
 */
@Entity
@Table(name = "client_info")
@Data
public class ClientInfo {

    /**
     * クライアントID
     */
    @Id
    @Column(length = 15, nullable = false)
    private String clientId;

    /**
     * クライアントシークレット
     */
    @Column(length = 32, nullable = false)
    private String clientSecret;

    /**
     * リダイレクトURI
     */
    @Column(nullable = false)
    private String redirectUri;
}
