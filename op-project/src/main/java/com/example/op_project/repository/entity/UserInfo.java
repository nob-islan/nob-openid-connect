package com.example.op_project.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * user_infoテーブル向けエンティティクラスです。
 * 
 * @author nob
 */
@Entity
@Table(name = "user_info")
@Data
public class UserInfo {

    /**
     * ユーザ名
     */
    @Id
    @Column(length = 10, nullable = false)
    private String username;

    /**
     * パスワード
     */
    @Column(length = 32, nullable = false)
    private String password;
}
