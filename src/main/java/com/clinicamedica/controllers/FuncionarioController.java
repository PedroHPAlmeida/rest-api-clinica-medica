package com.clinicamedica.controllers;

import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.views.Login;
import com.clinicamedica.exceptions.FuncionarioException;
import com.clinicamedica.services.FuncionarioService;
import com.clinicamedica.services.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarFuncionario(@RequestBody Funcionario funcionario){
        try{
            funcionarioService.salvarFuncionario(funcionario);
        }
        catch (FuncionarioException exception){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email já cadastrado, tente outro.");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> listaFuncionarios(){
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Funcionario buscarFuncionarioPorId(@PathVariable Long id){
        return funcionarioService.buscarFuncionarioPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado."));
    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Funcionario verificarLogin(@RequestBody Login login){
        return loginService.verificarLogin(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Senha ou email incorreto."));
    }

    @GetMapping(path = "/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Funcionario buscarFuncionarioPorEmail(@PathVariable String email){
        return funcionarioService.buscarFuncionarioPorEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado."));
    }

    @GetMapping(path = "/tipofuncionario")
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> buscarFuncionarioPorTipo(@RequestParam(required = false) int tipoFuncionario){
        return funcionarioService.listarFuncionariosPorTipo(tipoFuncionario);
    }

    @PutMapping(path = "/alterar-funcionario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarFuncionario(@RequestBody Funcionario funcionario){
        try {
            funcionarioService.alterarFuncionario(funcionario);
        } catch (FuncionarioException exception){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email já cadastrado, tente outro.");
        }
    }
}
