package com.user.micro.demo.infrastructure.repository;

import com.user.micro.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
