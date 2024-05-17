package com.work.kaka.repository;

import com.work.kaka.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository// Optional, but good for clarity
public interface CommunityRepository extends JpaRepository<Community, Long> {
    // Existing methods from JpaRepository:
    // - findAll
    // - findById
    // - save
    // - deleteById
    // - etc.

    // Additional custom methods
    boolean existsByCommunityName(String communityName);

    // Find community by admin (userId)
    Optional<Community> findByAdmin_UserId(Long userId);
}