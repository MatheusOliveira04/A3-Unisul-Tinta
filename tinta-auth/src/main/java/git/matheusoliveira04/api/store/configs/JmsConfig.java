package git.matheusoliveira04.api.store.configs;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
public class JmsConfig {


    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {

        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory();

        factory.setBrokerURL(brokerUrl);

        factory.setTrustAllPackages(true);

        return new CachingConnectionFactory(factory);
    }
}