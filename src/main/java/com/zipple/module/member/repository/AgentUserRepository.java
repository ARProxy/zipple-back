package com.zipple.module.member.repository;

import com.zipple.module.member.entity.AgentUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentUserRepository extends JpaRepository<AgentUser, Long> {
}
