package io.github.jr.deveopers.startserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.instrument.web.TraceWebFilter;
import org.springframework.cloud.sleuth.instrument.web.client.TraceRequestHttpHeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = {"brave", "io.github.jr"})
@RestController
@Log4j2 //    private static final Logger log = LogManager.getLogger(StartserverApplication.class); 생성
public class StartserverApplication {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(StartserverApplication.class, args);
    }

    @GetMapping
    public Object retrieveByClubName() throws JsonProcessingException {
        log.info("[startserver] start");
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "Hello");
        map.put("name", "World");
        Object object = restTemplate.postForObject("http://localhost:8080/user", map, Object.class);
        log.info("[startserver] end : " + new ObjectMapper().writeValueAsString(object));
        return object;
    }
}
