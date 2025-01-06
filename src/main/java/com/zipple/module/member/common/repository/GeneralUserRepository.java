package com.zipple.module.member.common.repository;

import com.zipple.module.member.common.entity.GeneralUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralUserRepository extends JpaRepository<GeneralUser, Long> {
}
