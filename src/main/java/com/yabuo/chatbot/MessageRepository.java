package com.yabuo.chatbot;

import com.yabuo.chatbot.Message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MessageRepository extends ElasticsearchRepository<Message, String> {
	Page<Message> findByPhrase(String phrase, Pageable pageable);

	@Query("{\"bool\": {\"must\": [{\"match\": {\"phrase\": \"?0\"}}]}}")
	Page<Message> findByPhraseUsingCustomQuery(String phrase, Pageable pageable);
}
