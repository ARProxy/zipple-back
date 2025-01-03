package com.zipple.module.member;

import com.zipple.common.auth.jwt.token.AuthTokens;
import com.zipple.common.oauth.kakao.KakaoLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        System.out.println(params);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
