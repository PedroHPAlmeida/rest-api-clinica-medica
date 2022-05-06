package com.clinicamedica.services;

import com.clinicamedica.entities.Clinica;
import com.clinicamedica.repositories.IClinicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaService {

    @Autowired
    private IClinicaRepository clinicaRepository;

    public Clinica salvarClinica(Clinica clinica){
        return clinicaRepository.save(clinica);
    }

    public Optional<Clinica> buscarClinicaPorId(Long id){
        return clinicaRepository.findById(id);
    }

    public List<Clinica> listarClinicas(){
        return clinicaRepository.findAll();
    }
}
