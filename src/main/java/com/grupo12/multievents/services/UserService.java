package com.grupo12.multievents.services;


import com.grupo12.multievents.models.dtos.AccountDTO;
import com.grupo12.multievents.models.dtos.SaveUserDTO;
import com.grupo12.multievents.models.entities.Avatar;
import com.grupo12.multievents.models.entities.Token;
import com.grupo12.multievents.models.entities.User;

import java.util.List;

public interface UserService {
    void register(SaveUserDTO data, Avatar avatar, String confirmationCode) throws Exception;

    User findUserById(String id);

    User findUserByEmail(String email);

    User findUserByIdentifier(String identifier);

    User findUserByConfirmationCode(String confirmationCode);

    void confirmUser(User user) throws Exception;

    Boolean isUserConfirmed(User user);

    Boolean comparePassword(String toCompare, String current);

    List<User> findAllUsers();

    void deleteUserById(String id);

    User findUserAuthenticated();

    Token registerToken(User user) throws Exception;

    Boolean isTokenValid(User user, String token);

    void cleanTokens(User user) throws Exception;

    String verifyIDToken(String idToken);
}
