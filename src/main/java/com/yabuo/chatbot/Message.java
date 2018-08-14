package com.yabuo.chatbot;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "yabuo", type = "message")
public class Message {

	@Id
	private String id;
	private String phrase;

	public void Message() {
	}

	public void Message(String id, String phrase) {
		this.id = id;
		this.phrase = phrase;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhrase() {
		return this.phrase;
	}

	public void setPhrase(String phrase) {
		this.id = phrase;
	}

	@Override
	public String toString() {
		return "Message[" +
				"id='" + id + '\'' +
				", phrase='" + phrase + '\'' +
				']';
	}

}
