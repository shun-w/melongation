package org.assignment.melongation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.assignment.melongation.mapper")
public class MelongationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MelongationApplication.class, args);
    }

}
