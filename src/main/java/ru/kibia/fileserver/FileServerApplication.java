package ru.kibia.fileserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "ru.kibia.fileserver.repository")
@SpringBootApplication
@ComponentScan("ru.kibia.fileserver")
public class FileServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FileServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(FileServerApplication.class, args);
	}
}
