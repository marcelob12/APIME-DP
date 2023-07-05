package com.grupo12.multievents.services;

import com.grupo12.multievents.models.entities.Transfer;

import java.util.List;

public interface TransferService {

    void saveTransfer() throws Exception;

    Transfer findTransferById(String id);

    List<Transfer> findAllTransfers();

    void deleteTransferById() throws Exception;
}
