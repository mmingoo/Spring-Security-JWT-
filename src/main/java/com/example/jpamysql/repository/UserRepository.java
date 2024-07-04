package com.example.jpamysql.repository;

import com.example.jpamysql.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Boolean existsByUsername(String username);

    //username을 받아 db테이블에서 회원을 조회하는 메서드
    UserEntity findByUsername(String username);
}
