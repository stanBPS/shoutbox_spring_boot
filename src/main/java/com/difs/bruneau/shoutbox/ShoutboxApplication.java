package com.difs.bruneau.shoutbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import com.difs.bruneau.shoutbox.model.Message;
import com.difs.bruneau.shoutbox.model.Report;
import com.difs.bruneau.shoutbox.model.Role;
import com.difs.bruneau.shoutbox.model.User;

/**
 * 
 * @author stanb
 *
 */
@SpringBootApplication
public class ShoutboxApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShoutboxApplication.class);
    }
	
	public static void main(String[] args) {	
		SpringApplication.run(ShoutboxApplication.class, args);
		
	}
	
	@Component
	class SpringDataRestCustomization implements RepositoryRestConfigurer {

		/**
		 * Pour le RestController permet l'affichage des id de chaque model(les id sont normalement caché")
		 */
		@Override
		public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
			config.exposeIdsFor(Message.class, Report.class,User.class,Role.class);
		}
	}

}
