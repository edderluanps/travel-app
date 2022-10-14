package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage smm) {
        LOG.debug("Envio de email fake");
        LOG.info(smm.toString());
        LOG.info("email enviado");
    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
