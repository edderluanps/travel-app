package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Cliente;
import com.eluanps.travelapp.repository.ClienteRepository;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //@Autowired
    //private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPassword = newPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));

        clienteRepository.save(cliente);
        //emailService.sendNewPasswordEmail(cliente, newPassword);
    }

    private String newPassword() {
        char[] vector = new char[10];
        for (int i = 0; i < 10; i++) {
            vector[i] = randomChar();
        }
        return new String(vector);
    }

    private char randomChar() {
        int options = random.nextInt(3);
        if (options == 0) {
            return (char) (random.nextInt(10) + 48);
        } else if (options == 0) {
            return (char) (random.nextInt(26) + 65);
        } else {
            return (char) (random.nextInt(26) + 97);
        }
    }

}
