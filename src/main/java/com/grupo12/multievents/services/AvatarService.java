package com.grupo12.multievents.services;

import com.grupo12.multievents.models.entities.Avatar;

import java.util.List;

public interface AvatarService {
    List<Avatar> findAllAvatars();
    Avatar findAvatarByUrl(String url);
}
