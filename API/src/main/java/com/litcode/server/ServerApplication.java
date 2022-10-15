package com.litcode.server;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ServerApplication.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", "3003"));
    app.run(args);
  }
}
