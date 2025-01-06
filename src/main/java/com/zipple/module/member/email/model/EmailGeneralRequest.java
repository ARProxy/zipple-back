package com.zipple.module.member.email.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "EmailGeneralRequest", description = "이메일 회원가입 요청 데이터")
public class EmailGeneralRequest {

    @Schema(description = "사용자 이름", example = "이지혜")
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String generalName;

    @Schema(description = "이메일", example = "kakao@kakao.com")
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "유효한 이메일 주소 형식이 아닙니다.")
    private String email;

    @Schema(description = "비밀번호", example = "일치하면 하나만 요청 보낼 것")
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자리 이상이어야 합니다.")
    private String password;

    @Schema(description = "주소", example = "서울특별시 역삼3동")
    @NotBlank(message = "주소는 필수 입력 항목입니다.")
    private String generalAddress;

    @Schema(description = "주거 형태", example = "문자열로 보내주면 enum 처리할 거임")
    private String housingType;

    @Schema(description = "마케팅 수신 동의", example = "수신 : Y, 거부 : N (필수 체크는 안보내도 됌)")
    @Pattern(regexp = "^(Y|N)$", message = "마케팅 수신 동의 값은 Y 또는 N이어야 합니다.")
    private String marketingNotificationTerms;

}
