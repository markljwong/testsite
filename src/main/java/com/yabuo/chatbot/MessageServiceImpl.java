package com.yabuo.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService{
	private MessageRepository messageRepository;

	@Autowired
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public Message save(Message message) {
		return messageRepository.save(message);
	}

	public void delete(Message message) {
		messageRepository.delete(message);
	}

	public Message find(String id) {
		return messageRepository.find(id);
	}

	public Iterable<Message> findAll() {
		return messageRepository.findAll();
	}

	public Page<Message> findByPhrase(String phrase, PageRequest pageRequest) {
		return messageRepository.findByPhrase(phrase, pageRequest);
	}
}
