package TMT.Ranking;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
@EnableScheduling
public class TmtRankServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmtRankServerApplication.class, args);
	}

}
