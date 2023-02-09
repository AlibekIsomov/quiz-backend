package com.quiz;

import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class QuizBackendApplication implements ApplicationRunner {

	@Autowired
    UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;	

	public static void main(String[] args) {
		SpringApplication.run(QuizBackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Optional<User> uk = userRepository.findByUsername("admin123");
		if(!uk.isPresent()){
			User u = new User();	
			u.setName("admin");
			u.setSurname("Admin");
			u.setUsername("admin123");
			u.setPassword(passwordEncoder.encode("admin123"));
			u.setActive(true);
			u.setRoles(Set.of(Role.ADMIN,Role.MANAGER,Role.USER));
			userRepository.save(u);
		}
	}


}
