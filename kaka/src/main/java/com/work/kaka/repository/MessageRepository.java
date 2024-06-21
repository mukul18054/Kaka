package com.work.kaka.repository;

import com.work.kaka.model.Chat;
import com.work.kaka.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChat(Chat chat);
}