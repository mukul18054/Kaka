package com.work.kaka.repository;

import com.work.kaka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository// Optional, but good for clarity
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

