package com.clinicamedica.controllers;

import com.clinicamedica.entities.Paciente;
import com.clinicamedica.services.PacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente salvarPaciente(@RequestBody Paciente paciente){
        return pacienteService.salvarPaciente(paciente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @GetMapping(path = "/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Paciente buscarPacientePorCpf(@PathVariable String cpf){
        return pacienteService.buscarPacientePorCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
    }

    @PutMapping(path = "/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarPacientePorCpf(@PathVariable String cpf, @RequestBody Paciente paciente){
        pacienteService.buscarPacientePorCpf(cpf)
                .map(pacienteBase -> {
                    modelMapper.map(paciente, pacienteBase);
                    pacienteService.salvarPaciente(pacienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
    }

    @DeleteMapping(path = "/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPacientePorCpf(@PathVariable String cpf){
        pacienteService.buscarPacientePorCpf(cpf)
                .map(paciente -> {
                    pacienteService.deletarPacientePorCpf(cpf);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
    }
}
