package git.matheusoliveira04.api.store.consumers;

import git.matheusoliveira04.api.store.models.User;
import git.matheusoliveira04.api.store.models.dtos.requests.UserRequestEvent;
import git.matheusoliveira04.api.store.models.dtos.responses.UserResponseEvent;
import git.matheusoliveira04.api.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConsumer {

    @Autowired
    private UserRepository repository;

    @JmsListener(destination = "user.request")
    @SendTo("user.response")
    public UserResponseEvent receive(
            UserRequestEvent event
    ) {

        User user = repository
                .findByEmail(event.email())
                .orElseThrow();

        return new UserResponseEvent(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
