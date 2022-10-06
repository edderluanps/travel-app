package com.eluanps.travelapp.service;

import com.eluanps.travelapp.entity.PgBoleto;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {
    
    public void preencherPGBoleto(PgBoleto pgBoleto, Date dataPedido){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataPedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pgBoleto.setVencimento(calendar.getTime());
    }
    
}
