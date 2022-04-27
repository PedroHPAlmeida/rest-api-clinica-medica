package com.clinicamedica.services;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.entities.Medico;
import com.clinicamedica.repositories.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService{
    @Autowired
    private IMedicoRepository medicoRepository;
    @Autowired
    private EspecialidadeService especialidadeService;
    @Autowired
    private FuncionarioService funcionarioService;

    public void salvarMedico(Medico medico){
        Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorEmail(medico.getEmail());
        if(funcionario.isEmpty()) {
            if (medico.getEspecialidade().getIdEspecialidade() == null) {
                especialidadeService.salvarEspecialidade(medico.getEspecialidade());
            }
            medicoRepository.save(medico);
        }
    }

    public List<Medico> listarMedicos(){
        return medicoRepository.findAll();
    }

    public List<Medico> listarMedicosPorIdEspecialidade(Long idEspecialidade) {
        if(idEspecialidade == null){
            return this.listarMedicos();
        }
        Optional<Especialidade> especialidadeOptional = especialidadeService.buscarEspecialidadePorId(idEspecialidade);
        if(!especialidadeOptional.isEmpty()){
         return medicoRepository.findByEspecialidade(especialidadeOptional.get());
        }
        return Collections.emptyList();
    }
}
