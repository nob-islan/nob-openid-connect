package com.example.op_project.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * code_challenge_infoテーブル向けのエンティティクラスです。
 * 
 * @author nob
 */
@Entity
@Table(name = "code_challenge_info")
@Data
public class CodeChallengeInfo {

    /**
     * データ管理用code_challenge ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, nullable = false)
    private Integer codeChallengeId;

    /**
     * code_challenge
     */
    @Column(length = 128, nullable = false)
    private String codeChallenge;

    /**
     * code_challenge_method
     */
    @Column(length = 10, nullable = false)
    private String codeChallengeMethod;

    /**
     * 削除フラグ
     */
    @Column(nullable = false)
    private Boolean isDeleted;
}
