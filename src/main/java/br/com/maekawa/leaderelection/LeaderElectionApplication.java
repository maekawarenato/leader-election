package br.com.maekawa.leaderelection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeaderElectionApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(LeaderElectionApplication.class, args);
	}

}
