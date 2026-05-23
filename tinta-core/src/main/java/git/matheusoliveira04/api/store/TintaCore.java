package git.matheusoliveira04.api.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)

public class TintaCore {

    public static void main(String[] args) {
        SpringApplication.run(TintaCore.class, args);
    }

}
