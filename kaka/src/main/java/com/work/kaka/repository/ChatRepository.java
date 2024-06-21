package com.work.kaka.repository;

import com.work.kaka.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
