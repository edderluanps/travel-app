package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SMTPEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;

    private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage smm) {
        LOG.debug("Enviando email...");
        mailSender.send(smm);
        LOG.info("email enviado");

    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
