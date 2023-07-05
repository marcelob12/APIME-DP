package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.entities.Avatar;
import com.grupo12.multievents.repositories.AvatarRepository;
import com.grupo12.multievents.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    AvatarRepository avatarRepository;

    @Override
    public List<Avatar> findAllAvatars() {
        return avatarRepository.findAll();
    }

    @Override
    public Avatar findAvatarByUrl(String url) {
        return avatarRepository.findByUrl(url);
    }
}
