package com.zipple.module.member.oauth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "OAuth 로그인 후 추가 일반 사용자 요청 데이터")
public class GeneralUserRequest {

    @Schema(description = "사용자 이름", example = "이지혜")
    private String generalName;

    @Schema(description = "이메일", example = "kakao@kakao.com")
    private String email;

    @Schema(description = "비밀번호", example = "일치하면 하나만 요청 보낼 것")
    private String password;

    @Schema(description = "주소", example = "서울특별시 역삼3동")
    private String generalAddress;

    @Schema(description = "주거 형태", example = "문자열로 보내주면 enum 처리할 거임")
    private String housingType;

    @Schema(description = "마케팅 수신 동의", example = "수신 : Y, 거부 : N (필수 체크는 안보내도 됌)")
    private String marketingNotificationTerms;

}
