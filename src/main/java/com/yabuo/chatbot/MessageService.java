package com.yabuo.chatbot;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MessageService {
	Message save(Message message);

	void delete(Message message);

	Message find(String id);

	Iterable<Message> findAll();

	Page<Message> findByPhrase(String phrase, PageRequest pageRequest);
}
