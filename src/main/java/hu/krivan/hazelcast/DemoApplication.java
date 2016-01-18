package hu.krivan.hazelcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(excludeName = {"org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration"})
@ImportResource({"classpath:/context-tx.xml"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
