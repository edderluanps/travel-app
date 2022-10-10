package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);

    void sendEmail(SimpleMailMessage smm);

}
