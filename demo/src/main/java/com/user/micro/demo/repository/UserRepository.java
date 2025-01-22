package com.user.micro.demo.repository;

import com.user.micro.demo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
