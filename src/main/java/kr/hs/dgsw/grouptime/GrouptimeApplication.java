package kr.hs.dgsw.grouptime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GrouptimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrouptimeApplication.class, args);
    }

}
