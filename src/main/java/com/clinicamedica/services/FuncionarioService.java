package com.clinicamedica.services;

import com.clinicamedica.entities.Funcionario;
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

    public void salvarFuncionario(Funcionario funcionario){
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
        if(tipoFuncionario.equals(null)){
            return funcionarioRepository.findAll();
        }
        return funcionarioRepository.findByTipoFuncionario(tipoFuncionario);
    }

    public void deletarFuncionarioPorId(Long id){
        funcionarioRepository.deleteById(id);
    }
}
