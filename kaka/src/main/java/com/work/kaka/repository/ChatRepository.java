package com.work.kaka.repository;

import com.work.kaka.model.Chat;
import com.work.kaka.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findByParticipant1AndParticipant2(User sender, User recipient);
}
