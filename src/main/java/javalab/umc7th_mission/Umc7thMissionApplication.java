package javalab.umc7th_mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Umc7thMissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc7thMissionApplication.class, args);
	}

}
