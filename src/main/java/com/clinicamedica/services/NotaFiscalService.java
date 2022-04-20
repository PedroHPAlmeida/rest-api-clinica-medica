package com.clinicamedica.services;

import com.clinicamedica.entities.NotaFiscal;
import com.clinicamedica.repositories.INotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private INotaFiscalRepository notaFiscalRepository;

    public NotaFiscal salvarNotaFiscal(NotaFiscal notaFiscal){
        return notaFiscalRepository.save(notaFiscal);
    }

    public List<NotaFiscal> listarNotasFiscais(){
        return notaFiscalRepository.findAll();
    }

    public Optional<NotaFiscal> buscarNotaFiscalPorId(Long id){
        return notaFiscalRepository.findById(id);
    }
}
