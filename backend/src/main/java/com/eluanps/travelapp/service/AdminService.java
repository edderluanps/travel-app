package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Admin;
import com.eluanps.travelapp.repository.AdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Admin não encontrado!"));
    }

    public Admin salvar(Admin admin) {
        return adminRepository.save(admin);
    }

    public void atualizar(Long id, Admin admin) {
        adminRepository.findById(id).map(obj -> {
            admin.setId(obj.getId());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Admin não encontrado!"));
    }

    public void delete(Long id) {
        adminRepository.findById(id).map(obj -> {
            adminRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário Admin não encontrado!"));
    }

}
