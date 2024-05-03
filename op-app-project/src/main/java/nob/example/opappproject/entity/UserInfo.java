package nob.example.opappproject.entity;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
     * ユーザID
     */
    @Id
    @Column(length = 10, nullable = false)
    private String userId;

    /**
     * パスワード
     */
    @Column(length = 32, nullable = false)
    private String password;
}