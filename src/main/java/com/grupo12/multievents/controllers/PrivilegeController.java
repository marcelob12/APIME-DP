package com.grupo12.multievents.controllers;

import com.grupo12.multievents.services.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    PrivilegeService privilegeService;

    @GetMapping("/")
    public ResponseEntity<?> getAllPrivileges() {
        return new ResponseEntity<>(privilegeService.findAllPrivileges(), HttpStatus.OK);
    }

}
