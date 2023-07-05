package com.grupo12.multievents.controllers;

import com.grupo12.multievents.models.dtos.AvatarDTO;
import com.grupo12.multievents.models.dtos.MessageDTO;
import com.grupo12.multievents.models.entities.Avatar;
import com.grupo12.multievents.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avatar")
@CrossOrigin("*")
public class AvatarController {

    @Autowired
    AvatarService avatarService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllAvatars() {
        List<Avatar> avatarList = avatarService.findAllAvatars();
        return new ResponseEntity<>(avatarList, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAvatar(@RequestBody AvatarDTO info) {
        Avatar avatar = avatarService.findAvatarByUrl(info.getUrl());

        if (avatar == null)
            return new ResponseEntity<>(new MessageDTO("Avatar not found"), HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }
}
