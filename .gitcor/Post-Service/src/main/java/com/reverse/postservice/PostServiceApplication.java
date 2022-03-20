package com.reverse.postservice;

import com.reverse.postservice.tools.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@SpringBootApplication
public class PostServiceApplication {

	@Generated
	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

	@PostConstruct
	private static void starting() {
		Log.getLog().info("Post Service Application has started.");
	}

	@PreDestroy
	private static void ending() {
		Log.getLog().info("Post Service Application is shutting down.");
	}

	//@Bean
	//public WebClient webClient(){
	//	return WebClient.create();
	//}
}
