package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.Admin;
import com.eluanps.travelapp.repository.AdminRepository;
import com.eluanps.travelapp.service.exceptions.DataIntegrityException;
import com.eluanps.travelapp.service.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário Admin não encontrado"));
    }

    public Admin salvar(Admin admin) {
        return adminRepository.save(admin);
    }

    public void atualizar(Long id, Admin admin) {
        adminRepository.findById(id).map(obj -> {
            admin.setId(obj.getId());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new ObjectNotFoundException("Usuário Admin não encontrado"));
    }

    public void delete(Long id) {
        findById(id);
        try{
            adminRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Admin: Usuário Ativo.");
        }
    }

}
