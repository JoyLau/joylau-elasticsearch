package cn.joylau.code.joylau;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class JoylauElasticsearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(JoylauElasticsearchApplication.class, args);
	}
}
