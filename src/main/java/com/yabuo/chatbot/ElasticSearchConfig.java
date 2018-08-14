package com.yabuo.chatbot;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.yabuo.chatbot.repository")
public class ElasticSearchConfig {
	@Value("${elasticsearch.host}")
	private String esHost;

	@Value("${elasticsearch.port}")
	private int esPort;

	@Value("${elasticsearch.clustername}")
	private String esClusterName;

	@Bean
	public Client client() throws Exception {
		Settings esSettings = Settings.builder()
				.put("cluster.name", esClusterName)
				.build();

		return new PreBuiltTransportClient(esSettings).addTransportAddress(
				new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort));
	}

	@Bean
	public ElasticsearchOperations esTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
}
