package com.clinicamedica.services;

import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.views.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    FuncionarioService funcionarioService;

    public Optional<Funcionario> verificarLogin(Login login){
        Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorEmail(login.getEmail());
        if(!funcionario.isEmpty()){
            String senhaCorreta = funcionario.get().getSenha();
            if(senhaCorreta.equals(login.getSenha())) {
                return funcionario;
            }
        }
        return Optional.empty();
    }
}
