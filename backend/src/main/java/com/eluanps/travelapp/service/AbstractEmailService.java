package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Pedido;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;


public abstract class AbstractEmailService implements EmailService {
    
    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido pedido){
        SimpleMailMessage smm = simpleMailMessageFromPedido(pedido);
        sendEmail(smm);
    }

    protected SimpleMailMessage simpleMailMessageFromPedido(Pedido pedido) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(pedido.getCliente().getEmail());
        smm.setFrom(sender);
        smm.setSubject("Pedido Confirmado: Cód: " + pedido.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(pedido.toString());
        return smm;
    }

}
