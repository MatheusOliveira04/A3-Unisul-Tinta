package git.matheusoliveira04.api.store.producer;

import git.matheusoliveira04.api.store.models.dtos.requests.UserRequestEvent;
import git.matheusoliveira04.api.store.models.dtos.responses.UserResponseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;

@Service
public class UserRequestProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public UserResponseEvent getUserByEmail(
            String email
    ) {

        Message response = jmsTemplate.sendAndReceive(
                "user.request",
                session -> session.createObjectMessage(
                        new UserRequestEvent(email)
                )
        );

        try {

            if (response instanceof ObjectMessage objectMessage) {

                return (UserResponseEvent)
                        objectMessage.getObject();
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        throw new RuntimeException("User not found");
    }
}
