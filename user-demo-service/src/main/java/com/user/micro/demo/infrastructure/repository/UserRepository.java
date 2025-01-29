package com.user.micro.demo.infrastructure.repository;

import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.infrastructure.projections.UserBalanceProjection;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserBalanceProjection findBalanceById(Long id);
}
