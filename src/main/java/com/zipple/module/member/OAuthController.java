package com.zipple.module.member;

import com.zipple.common.auth.jwt.token.AuthTokens;
import com.zipple.common.oauth.kakao.KakaoLoginParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "카카오 로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @Operation(
            summary = "카카오 회원가입/로그인"
            ,description = "authorizationCode: url에 code값 입력"
    )
    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        System.out.println(params);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
