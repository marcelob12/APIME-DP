package com.grupo12.multievents.services;


import com.grupo12.multievents.models.entities.QrCode;

import java.util.List;

public interface QrCodeService {

    void createQrCode() throws Exception;

    QrCode findQrCodeById(String id);

    List<QrCode> findAllQrCode();

    void deleteQrCodeById(String id) throws Exception;

    void shareQrCode();

}
