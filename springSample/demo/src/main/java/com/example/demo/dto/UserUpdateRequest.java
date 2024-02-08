package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報更新リクエストデータ
 * userAddRequestクラスを継承する
 * callSuper = false)はequalsおよびhashCodeメソッドはidファイールドだけ考慮して生成されるため親クラスであるUserAddRequestのフィールドは対象外
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateRequest extends UserAddRequest {
    /**
     * ユーザーID
     */
    @NotNull
    private Long id;
}