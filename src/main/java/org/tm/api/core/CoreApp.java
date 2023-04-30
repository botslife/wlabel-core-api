package org.tm.api.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

//TODO Using generated security password: ASAP needs fixing before going to next environment
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class})
public class CoreApp {

  public static void main(String[] args) {
    SpringApplication.run(CoreApp.class, args);
  }

}
