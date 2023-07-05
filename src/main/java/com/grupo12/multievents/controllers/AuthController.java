package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.*;
import com.grupo12.multievents.models.entities.Avatar;
import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.Token;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.services.AccessService;
import com.grupo12.multievents.services.AvatarService;
import com.grupo12.multievents.services.PrivilegeService;
import com.grupo12.multievents.services.UserService;
import com.grupo12.multievents.utils.CodeGenerator;
import com.grupo12.multievents.utils.EmailService;
import com.grupo12.multievents.utils.RequestErrorHandler;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    AvatarService avatarService;

    @Autowired
    UserService userService;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    AccessService accessService;

    @Autowired
    RequestErrorHandler requestErrorHandler;

    @Autowired
    EmailService emailService;

    @Autowired
    CodeGenerator codeGenerator;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/google-auth")
    public ResponseEntity<?> googleAuth(@RequestBody GoogleAuthDTO data, BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        String email = userService.verifyIDToken(data.getIdToken());
        User user = userService.findUserByEmail(email);

        // User is not registered
        if (user == null) {
            return
                    register(new SaveUserDTO(
                            email,
                            email.split("@")[0],
                            "xd",
                            "https://asset.cloudinary.com/dtsasaxji/883a93428e688a138fd98a92ff90cbfc"
                    ));
        }

        if (!user.getConfirmed())
            return new ResponseEntity<>(new MessageDTO("Debes confirmar tu cuenta para acceder"), HttpStatus.UNAUTHORIZED);

        return sendToken(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO info, BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(requestErrorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findUserByEmail(info.getEmail());

        if (user == null)
            return new ResponseEntity<>(new MessageDTO("Usuario no encontrado"), HttpStatus.NOT_FOUND);

        if (!user.getConfirmed())
            return new ResponseEntity<>(new MessageDTO("Debes confirmar tu cuenta para acceder"), HttpStatus.UNAUTHORIZED);

        return sendToken(user);
    }

    public ResponseEntity<?> sendToken(User user) {
        // Compare password in db with user password
        if (!userService.comparePassword("xd", user.getPassword()))
            return new ResponseEntity<>(new MessageDTO("Unauthorized"), HttpStatus.UNAUTHORIZED);

        try {
            Token token = userService.registerToken(user);
            return new ResponseEntity<>(new LoginResponseDTO(user.getUsername(), user.getEmail(), token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> register(SaveUserDTO data) {
        Avatar avatar = avatarService.findAvatarByUrl(data.getAvatar());
        User user = userService.findUserByEmail(data.getEmail());
        Privilege privilege = privilegeService.findPrivilegeByName("Client");
        String confirmationCode = codeGenerator.generate();

        if (avatar == null)
            return new ResponseEntity<>(new MessageDTO("Avatar not found"), HttpStatus.NOT_FOUND);

        if (user != null)
            return new ResponseEntity<>(new MessageDTO("User already exists"), HttpStatus.BAD_REQUEST);

        try {
            userService.register(data, avatar, confirmationCode);

            // Save access (default: User)
            user = userService.findUserByEmail(data.getEmail());
            accessService.saveAccess(user, privilege);

            try {
                emailService.send("${spring.mail.username}",
                        data.getEmail(),
                        "MultiEvents - Confirmación de cuenta",
                        "<div style=\"background-color: black; padding-top: 7px; padding-bottom: 7px;\">\n" +
                                "        <h1 style=\"color: #febc14; text-align: center;\">MultiEvents</h1>\n" +
                                "    </div>\n" +
                                "    <div style=\"padding: 10px; margin-bottom: 50px; text-align: center; \">\n" +
                                "        <p style=\"text-align: center;\">Bienvenido, " + data.getUsername() + ". Para continuar con el registro de usuario debes ingresar al enlace que se encuentra abajo y confirmar tu cuenta. </p>\n" +
                                "        <a href=\"http://localhost:3000/confirm-account/" + confirmationCode + "\" style=\"color: #febc14;\">Verificar cuenta</a>\n" +
                                "    </div>\n" +
                                "    <div style=\"background-color: black; padding: 5px;\">\n" +
                                "        <p style=\"text-align: center; color: white;\">En un dado caso que tu no hayas intentado crear una cuenta has caso omiso de este correo.</p>\n" +
                                "    </div>"
                );
            } catch (MessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>(new MessageDTO("Email failed"), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new MessageDTO("Usuario creado exitosamente. Revise su correo para confirmar la cuenta"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
