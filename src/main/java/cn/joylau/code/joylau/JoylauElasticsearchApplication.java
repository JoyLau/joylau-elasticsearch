package cn.joylau.code.joylau;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@AllArgsConstructor
public class JoylauElasticsearchApplication {
	private RestTemplateBuilder builder;
	public static void main(String[] args) {
		SpringApplication.run(JoylauElasticsearchApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}
}
