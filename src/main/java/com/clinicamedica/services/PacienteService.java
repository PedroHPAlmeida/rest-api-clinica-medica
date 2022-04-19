package com.clinicamedica.services;

import com.clinicamedica.entities.Paciente;
import com.clinicamedica.repositories.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    public Paciente salvarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorCpf(String cpf){
        return pacienteRepository.findById(cpf);
    }

    public void deletarPacientePorCpf(String cpf){
        pacienteRepository.deleteById(cpf);
    }
}
