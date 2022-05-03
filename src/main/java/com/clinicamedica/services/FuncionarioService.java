package com.clinicamedica.services;

import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.exceptions.FuncionarioException;
import com.clinicamedica.repositories.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    public void salvarFuncionario(Funcionario funcionario){
        boolean emailJaExiste = this.contarEmailsRepetidos(funcionario.getEmail()) > 0 ? true : false;
        if(!emailJaExiste){
            funcionarioRepository.save(funcionario);
        } else {
            System.err.println("Email já cadastrado no sistema, tente outro.");
            throw new FuncionarioException("Email já cadastrado no sistema, tente outro.");
        }
    }

    public void alterarFuncionario(Funcionario funcionarioAlterado){
        Funcionario funcionario = this.buscarFuncionarioPorId(funcionarioAlterado.getIdFuncionario()).get();
        boolean emailFoiAlterado = !funcionario.getEmail().equals(funcionarioAlterado.getEmail());
        if(emailFoiAlterado){
            boolean emailJaExiste = this.contarEmailsRepetidos(funcionarioAlterado.getEmail()) > 0 ? true : false;
            if(emailJaExiste){
                System.err.println("Email já cadastrado no sistema, tente outro.");
                throw new FuncionarioException("Email já cadastrado no sistema, tente outro.");
            } else {
                funcionarioRepository.save(funcionarioAlterado);
            }
        } else {
            funcionarioRepository.save(funcionarioAlterado);
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

    public Integer contarEmailsRepetidos(String email){
        return funcionarioRepository.countByEmail(email);
    }

    public List<Funcionario> listarFuncionariosPorTipo(int tipoFuncionario){
        if(tipoFuncionario == 0){
               return funcionarioRepository.findAll();
        }
        return funcionarioRepository.findByTipoFuncionario(tipoFuncionario);
    }

    public void deletarFuncionarioPorId(Long id){
        funcionarioRepository.deleteById(id);
    }
}
