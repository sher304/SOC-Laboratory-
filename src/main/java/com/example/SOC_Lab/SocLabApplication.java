package com.example.SOC_Lab;

import com.example.SOC_Lab.Model.User;
import com.example.SOC_Lab.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class SocLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocLabApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository) {
		return args -> {
			if (userRepository.count() == 0) {
				userRepository.save(new User("admin", "super_secret_password_123", "ADMIN"));
				userRepository.save(new User("test_user", "123456", "USER"));

				String[] firstNames = {"john", "jane", "alex", "emily", "michael", "sarah", "david", "anna", "robert", "lisa", "james", "olivia", "william", "sophia", "joseph"};
				String[] lastNames = {"doe", "smith", "jones", "brown", "green", "white", "black", "clark", "taylor", "davis", "wilson", "miller", "martin", "taylor", "thomas"};
				String[] roles = {"USER", "MANAGER", "DEVELOPER", "ANALYST", "HR", "SUPPORT"};

				Random random = new Random();

				for (int i = 1; i <= 50; i++) {
					String username = firstNames[random.nextInt(firstNames.length)] + "_" + lastNames[random.nextInt(lastNames.length)] + i;
					String password = "pwd_" + (1000 + random.nextInt(9000));
					String role = roles[random.nextInt(roles.length)];
					userRepository.save(new User(username, password, role));
				}
			}
		};
	}
}
