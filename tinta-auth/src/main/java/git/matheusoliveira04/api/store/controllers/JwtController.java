package git.matheusoliveira04.api.store.controllers;

import git.matheusoliveira04.api.store.configs.jwt.JwtUtil;
import git.matheusoliveira04.api.store.models.User;
import git.matheusoliveira04.api.store.models.dtos.requests.LoginRequest;
import git.matheusoliveira04.api.store.models.dtos.responses.LoginResponse;
import git.matheusoliveira04.api.store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class JwtController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private UserService userService;

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody @Valid LoginRequest login) {
        Authentication authentication = auth.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        if (authentication.isAuthenticated()) {
            Optional<User> user = userService.findByEmail(login.email());
            return ResponseEntity.ok(new LoginResponse(user.get().getId() ,jwtUtil.generateToken(login.email())));
        } else {
            throw new UsernameNotFoundException("Invalid user");
        }

    }
}
