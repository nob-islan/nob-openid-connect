package com.example.op_project.repository.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * authorization_infoテーブル向けのエンティティクラスです。
 * 
 * @author nob
 */
@Entity
@Table(name = "authorization_info")
@Data
public class AuthorizationInfo {

    /**
     * データ管理用認可ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, nullable = false)
    private Integer authorizationId;

    /**
     * 認可コード
     */
    @Column(length = 40, nullable = false)
    private String code;

    /**
     * 有効期限
     */
    @Column(nullable = false)
    private Date expirationDateTime;

    /**
     * 削除フラグ
     */
    @Column(nullable = false)
    private Boolean isDeleted;
}
