package com.grupo12.multievents.services.implementations;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.grupo12.multievents.models.dtos.AccountDTO;
import com.grupo12.multievents.models.dtos.SaveUserDTO;
import com.grupo12.multievents.models.entities.Avatar;
import com.grupo12.multievents.models.entities.Token;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.repositories.TokenRepository;
import com.grupo12.multievents.repositories.UserRepository;
import com.grupo12.multievents.services.UserService;
import com.grupo12.multievents.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private TokenRepository tokenRepository;

    private final GoogleIdTokenVerifier verifier;

    public UserServiceImpl(@Value("${spring.security.oauth2.client.registration.google.client-id}") String clientId) {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier(transport, jsonFactory);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void register(SaveUserDTO data, Avatar avatar, String confirmationCode) throws Exception {
        User newUser = new User(data.getEmail(), data.getUsername(), passwordEncoder.encode(data.getPassword()), avatar, confirmationCode);
        userRepository.save(newUser);
    }

    @Override
    public User findUserById(String id) {

        UUID code = UUID.fromString(id);
        return userRepository.findById(code).orElse(null);

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByIdentifier(String identifier) {
        return userRepository.findByEmailOrUsername(identifier, identifier);
    }

    @Override
    public User findUserByConfirmationCode(String confirmationCode) {
        return userRepository.findByConfirmationCode(confirmationCode);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void confirmUser(User user) throws Exception {
        user.setConfirmed(true);
        userRepository.save(user);
    }

    @Override
    public Boolean isUserConfirmed(User user) {
        if (user.getConfirmed())
            return true;
        else
            return false;
    }

    @Override
    public Boolean comparePassword(String toCompare, String current) {
        return passwordEncoder.matches(toCompare, current);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(String id) {

    }

    @Override
    public User findUserAuthenticated() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmailOrUsername(username, username);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });

    }

    @Override
    public String verifyIDToken(String idToken) {
        GoogleIdToken idTokenObject = null;

        try {
            idTokenObject = verifier.verify(idToken);

            if (idToken == null)
                return null;

            GoogleIdToken.Payload payload = idTokenObject.getPayload();
            String email = payload.getEmail();

            return email;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
