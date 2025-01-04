package com.example.op_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.op_project.repository.entity.CodeChallengeInfo;

/**
 * code_challenge_infoテーブルのrepositoryインターフェースクラスです。
 * 
 * @author nob
 */
@Repository
public interface CodeChallengeInfoRepository extends JpaRepository<CodeChallengeInfo, Integer> {

    /**
     * code_challengeからcode_challenge情報を抽出します。
     * 
     * @param codeChallenge code_challenge
     * @return code_challenge情報
     */
    @Query(value = "SELECT * FROM code_challenge_info WHERE code_challenge = ?1 AND code_challenge_method = 'S256' AND is_deleted = false", nativeQuery = true)
    CodeChallengeInfo selectByCodeChallenge(String codeChallenge);
}
