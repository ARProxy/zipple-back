package com.zipple.common.utils;

import com.zipple.module.member.entity.User;
import com.zipple.module.member.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetMember {

    private static UserRepository UserRepository;

    @Autowired
    public void setMemberRepository(UserRepository memberRepository) {
        GetMember.UserRepository = memberRepository;
    }
    public static User getCurrentMember() {
        return UserRepository
                .findByEmail(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .orElseThrow(() -> new IllegalStateException("다시 로그인 해주세요"));
    }
}
