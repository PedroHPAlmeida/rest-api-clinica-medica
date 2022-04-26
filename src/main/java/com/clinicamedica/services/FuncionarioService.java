package com.clinicamedica.services;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.entities.Login;
import com.clinicamedica.repositories.IEspecialidadeRepository;
import com.clinicamedica.repositories.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    @Autowired
    private EspecialidadeService especialidadeService;

    public void salvarFuncionario(Funcionario funcionario){
        if(funcionario.getEspecialidade() != null){
            especialidadeService.salvarEspecialidade(funcionario.getEspecialidade());
        }
        Optional<Funcionario> optionalFuncionario = buscarFuncionarioPorEmail(funcionario.getEmail());
        if(optionalFuncionario.isEmpty()){
            funcionarioRepository.save(funcionario);
        }
    }

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id){
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> buscarFuncionarioPorEmail(String email){
        return funcionarioRepository.findByEmail(email);
    }

    public String buscarSenhaPeloEmail(String email){
        return funcionarioRepository.getSenhaByEmail(email);
    }

    public List<Funcionario> listarFuncionariosPorTipo(String tipoFuncionario){
        System.out.println(tipoFuncionario);
        if(tipoFuncionario.equals(null)){
            return funcionarioRepository.findAll();
        }
        return funcionarioRepository.findByTipoFuncionario(tipoFuncionario);
    }

    public void deletarFuncionarioPorId(Long id){
        funcionarioRepository.deleteById(id);
    }

    public List<Funcionario> listarMedicosPorIdEspecialidade(Long idEspecialidade) {
         Optional<Especialidade> especialidadeOptional = especialidadeService.buscarEspecialidadePorId(idEspecialidade);
         if(!especialidadeOptional.isEmpty()){
             return funcionarioRepository.findByEspecialidade(especialidadeOptional.get());
         }
         return Collections.emptyList();
    }
}
