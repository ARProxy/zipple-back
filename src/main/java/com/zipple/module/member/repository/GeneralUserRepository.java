package com.zipple.module.member.repository;

import com.zipple.module.member.entity.GeneralUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralUserRepository extends JpaRepository<GeneralUser, Long> {
}
