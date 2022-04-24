package com.clinicamedica.services;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.repositories.IEspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired
    private IEspecialidadeRepository especialidadeRepository;

    public Especialidade salvarEspecialidade(Especialidade especialidade){
        return especialidadeRepository.save(especialidade);
    }

    public List<Especialidade> listarEspecialidades(){
        return especialidadeRepository.findAll();
    }

    public Optional<Especialidade> buscarEspecialidadePorId(Long id){
        return especialidadeRepository.findById(id);
    }
}
