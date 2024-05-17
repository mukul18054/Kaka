package com.work.kaka.repository;
import com.work.kaka.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RequirementRepository extends JpaRepository<Requirement, Long> {
}
