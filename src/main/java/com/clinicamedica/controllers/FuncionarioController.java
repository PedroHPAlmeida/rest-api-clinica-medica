package com.clinicamedica.controllers;

import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.services.FuncionarioService;
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
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario salvarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.salvarFuncionario(funcionario);
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

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFuncionarioPorId(@PathVariable Long id){
        funcionarioService.buscarFuncionarioPorId(id)
                .map(funcionario -> {
                    funcionarioService.deletarFuncionarioPorId(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado."));
    }
}
