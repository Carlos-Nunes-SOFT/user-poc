package com.user.micro.demo.infrastructure.repository;

import com.user.micro.demo.domain.user.EncryptedUser;
import com.user.micro.demo.domain.user.User;
import com.user.micro.demo.infrastructure.projections.UserBalanceProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptedUserRepository extends JpaRepository<EncryptedUser, String> {
    void deleteById(String id);
}
