package ru.kibia.fileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.kibia.fileserver.mvc.model.entity.SharedFile;
import ru.kibia.fileserver.mvc.model.entity.User;
import ru.kibia.fileserver.repository.file.SharedFileRepository;
import ru.kibia.fileserver.repository.user.UserRepository;

import java.io.File;

@EnableJpaRepositories(basePackages = "ru.kibia.fileserver.repository")
@SpringBootApplication
@ComponentScan("ru.kibia.fileserver")
public class FileServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FileServerApplication.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FileServerApplication.class, args);
		UserRepository userRepository = (UserRepository) ctx.getBean("userRepository", UserRepository.class);
		SharedFileRepository sharedFileRepository = (SharedFileRepository) ctx.getBean("sharedFileRepository", SharedFileRepository.class);

		//userRepository.saveAndFlush(new User("xxz", "123", "Денис", "Владимирович", "Костин", "user"));
		User user = new User("vasya", "123456", "Вася", "Давыдович", "Пупкин", "user");
		userRepository.saveAndFlush(user);

		sharedFileRepository.saveAndFlush(new SharedFile(new File("I:/2.png"), user));
	}
}
