package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.web.client.RestTemplate;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean(name = "topicConnectionFactory")
	public DefaultJmsListenerContainerFactory topicConnectionFactory() {
		DefaultJmsListenerContainerFactory f = new DefaultJmsListenerContainerFactory();
		f.setConnectionFactory(connectionFactory);
		f.setSessionTransacted(true);
		f.setPubSubDomain(true);
		return f;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
