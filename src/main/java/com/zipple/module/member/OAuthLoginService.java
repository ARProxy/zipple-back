package com.zipple.module.member;

import com.zipple.common.auth.jwt.token.AuthTokens;
import com.zipple.common.auth.jwt.token.AuthTokensGenerator;
import com.zipple.common.oauth.OAuthInfoResponse;
import com.zipple.common.oauth.OAuthLoginParams;
import com.zipple.common.oauth.RequestOAuthInfoService;
import com.zipple.common.oauth.kakao.KakaoInfoResponse;
import com.zipple.module.member.common.entity.User;
import com.zipple.module.member.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;
    private final RestTemplate restTemplate;

    public void logout(String accessToken) {
        String url = "https://kapi.kakao.com/v1/user/logout";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer" +  accessToken);

        HttpEntity<?> request = new HttpEntity<>("", httpHeaders);
        restTemplate.postForObject(url, request, KakaoInfoResponse.class);
    }

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        User user = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .gender(oAuthInfoResponse.getGender())
                .age_range(oAuthInfoResponse.getAge_range())
                .profile_image_url(oAuthInfoResponse.getProfile_image_url())
                .birthday(oAuthInfoResponse.getBirthday())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        return userRepository.save(user).getId();
    }
}
