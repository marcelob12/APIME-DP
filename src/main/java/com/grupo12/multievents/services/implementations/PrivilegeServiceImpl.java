package com.grupo12.multievents.services.implementations;

import com.grupo12.multievents.models.entities.Privilege;
import com.grupo12.multievents.repositories.PrivilegeRepository;
import com.grupo12.multievents.services.PrivilegeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void savePrivilege() throws Exception {

    }

    @Override
    public Privilege findPrivilegeById(UUID id) {
        return privilegeRepository.findById(id).orElse(null);
    }

    @Override
    public Privilege findPrivilegeByName(String name) {
        return privilegeRepository.findByName(name);
    }

    @Override
    public List<Privilege> findAllPrivileges() {
        return privilegeRepository.findAll();
    }

    @Override
    public void deletePrivilegeById(String id) throws Exception {

    }
}
