package nob.example.opappproject.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * authorization_code_infoテーブル向けエンティティクラスです。
 * 
 * @author nob
 */
@Entity
@Table(name = "authorization_code_info")
@Data
public class AuthorizationCodeInfo {

    /**
     * コードID
     */
    @Id
    @Column(length = 10, nullable = false)
    private String codeId;

    /**
     * コード値
     */
    @Column(length = 32, nullable = false)
    private String codeValue;

    /**
     * codeChallenge
     */
    @Column(length = 64, nullable = false)
    private String codeChallenge;

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
