package cn.joylau.code.joylau;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class JoylauElasticsearchApplication {
	private static final Logger LOGGER = Logger.getLogger(JoylauElasticsearchApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JoylauElasticsearchApplication.class, args);
		for (int i = 0; i < 10; i++) {
			LOGGER.error("Info log [" + i + "].");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
