package com.example.coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * @author guan
 */
@EnableSwagger2WebMvc
@SpringBootApplication
public class WebSocket2 {
    public static void main(String[] args) {
        SpringApplication.run(WebSocket2.class, args);
    }

}
