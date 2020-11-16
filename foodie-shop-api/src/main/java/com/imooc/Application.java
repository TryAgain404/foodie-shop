package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author TryAgain404
 * @date 2020-10-21 10:22
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.n3r.idworker", "com.imooc"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
}
