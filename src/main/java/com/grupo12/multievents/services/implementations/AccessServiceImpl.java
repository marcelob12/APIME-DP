package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.entities.Access;
import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.models.entities.User;
import com.grupo12.multievents.repositories.AccessRepository;
import com.grupo12.multievents.repositories.PrivilegeRepository;
import com.grupo12.multievents.services.AccessService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Autowired
    AccessRepository accessRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveAccess(User user, Privilege privilege) throws Exception {
        Access newAccess = new Access(user, privilege);
        accessRepository.save(newAccess);
    }

    @Override
    public List<Access> findAccessByUser(UUID id) {
        return accessRepository.findByUserIdUser(id);
    }

    @Override
    public Access findAccessById(String id) {
        return null;
    }

    @Override
    public List<Access> findAllAccess() {
        return accessRepository.findAll();
    }

    @Override
    public void deleteAccessById(String id) throws Exception {

    }
}
