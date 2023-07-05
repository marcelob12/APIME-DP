package com.grupo12.multievents.repositories;

import com.grupo12.multievents.models.entities.QrCode;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface QrCodeRepository extends ListCrudRepository<QrCode, UUID> {

}
