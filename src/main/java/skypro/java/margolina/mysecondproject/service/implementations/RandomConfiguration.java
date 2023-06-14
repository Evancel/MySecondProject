package skypro.java.margolina.mysecondproject.service.implementations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Random;

@Configuration
public class RandomConfiguration {

    @Bean
    public Random defaultRandom(){
        return new Random();
    }

}
