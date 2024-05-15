package com.example.account.repository;

import com.example.account.domain.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUserId(String userId);

    Optional<Users> findByPassword(String password);
}
