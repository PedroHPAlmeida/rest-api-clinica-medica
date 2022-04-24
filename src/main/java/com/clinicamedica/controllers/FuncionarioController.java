package com.clinicamedica.controllers;

import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.entities.Login;
import com.clinicamedica.services.FuncionarioService;
import com.clinicamedica.services.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        funcionarioService.salvarFuncionario(funcionario);
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

    @GetMapping(path = "/login")
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

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarFuncionarioPorId(@PathVariable Long id, @RequestBody Funcionario funcionario){
        funcionarioService.buscarFuncionarioPorId(id)
                .map(funcionarioBase -> {
                    modelMapper.map(funcionario, funcionarioBase);
                    funcionarioService.salvarFuncionario(funcionarioBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado."));
    }

}
