package git.matheusoliveira04.api.store.configs;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
public class JmsConfig {

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {

        ActiveMQConnectionFactory factory =
                new ActiveMQConnectionFactory();

        factory.setBrokerURL("tcp://localhost:61616");

        // ADICIONE ISSO
        factory.setTrustAllPackages(true);

        return new CachingConnectionFactory(factory);
    }
}