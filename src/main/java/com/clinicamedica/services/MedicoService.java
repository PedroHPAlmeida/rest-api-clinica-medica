package com.clinicamedica.services;

import com.clinicamedica.entities.Medico;
import com.clinicamedica.repositories.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private IMedicoRepository medicoRepository;

    public Medico salvarMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    public List<Medico> listarMedicos(){
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarMedicoPorCrm(String crm){
        return medicoRepository.findByCrm(crm);
    }

    public void deletarMedicoPorCrm(String crm){
        medicoRepository.deleteByCrm(crm);
    }
}
