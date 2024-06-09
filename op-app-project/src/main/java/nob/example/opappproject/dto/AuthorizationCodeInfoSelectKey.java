package nob.example.opappproject.dto;

import java.util.Date;

import lombok.Data;

/**
 * 認可コード情報取得の検索条件dtoです。
 * 
 * @author nob
 */
@Data
public class AuthorizationCodeInfoSelectKey {

    /**
     * コード値
     */
    private String codeValue;

    /**
     * 現在日時（期限切れのチェック向け）
     */
    private Date nowDate;
}
