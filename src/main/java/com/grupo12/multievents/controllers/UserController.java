package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.MessageDTO;
import com.grupo12.multievents.models.dtos.SaveAccessDTO;
import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.services.AccessService;
import com.grupo12.multievents.services.PrivilegeService;
import com.grupo12.multievents.services.UserService;
import com.grupo12.multievents.utils.RequestErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AccessService accessService;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    RequestErrorHandler requestErrorHandler;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/confirm/{confirmationCode}")
    public ResponseEntity<?> confirmUser(@PathVariable("confirmationCode") String confirmationCode) {
        User user = userService.findUserByConfirmationCode(confirmationCode);

        if (user == null)
            return new ResponseEntity<>(new MessageDTO("El código de confirmación es inválido"), HttpStatus.NOT_FOUND);

        if (user.getConfirmed())
            return new ResponseEntity<>(new MessageDTO("El usuario ya ha sido confirmado previamente"), HttpStatus.OK);

        try {
            userService.confirmUser(user);
            return new ResponseEntity<>(new MessageDTO("El usuario ha sido confirmado"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/my-profile")
    public ResponseEntity<?> getProfile() {
        User user = userService.findUserAuthenticated();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id") String id) {
        User user = userService.findUserById(id);


        if (user == null)
            return new ResponseEntity<>(new MessageDTO("El id del usuario es inválido"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(new MessageDTO("El email deL usuario es inválido"), HttpStatus.NOT_FOUND);
        }

        try {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/access")
    public ResponseEntity<?> saveAccess(@RequestBody SaveAccessDTO info, BindingResult validations) {
        if (validations.hasErrors())
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);

        User user = userService.findUserByIdentifier(info.getIdentifier());
        Privilege privilege = privilegeService.findPrivilegeByName(info.getPrivilegeName());

        if (user == null)
            return new ResponseEntity<>(new MessageDTO("Este usuario no existe"), HttpStatus.OK);

        if (privilege == null)
            return new ResponseEntity<>(new MessageDTO("Este privilegio no existe"), HttpStatus.OK);

        try {
            accessService.saveAccess(user, privilege);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new MessageDTO("Acceso permitido"), HttpStatus.OK);
    }


}
