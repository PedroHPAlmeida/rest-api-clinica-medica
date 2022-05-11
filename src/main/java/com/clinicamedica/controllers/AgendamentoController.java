package com.clinicamedica.controllers;

import com.clinicamedica.entities.Agendamento;
import com.clinicamedica.services.AgendamentoService;
import com.clinicamedica.views.AgendamentoView;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AgendamentoView agendamentoView;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarAgendamento(@RequestBody AgendamentoView agendamentoView){
        agendamentoService.salvarAgendamento(agendamentoView);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Agendamento> listarAgendamentos(){
        return agendamentoService.listarAgendamentos();
    }

    @GetMapping(path = "/listar-por-cpf-e-status")
    @ResponseStatus(HttpStatus.OK)
    public List<Agendamento> listarAgendamentosPorCpfEStatus(@RequestParam(required = false) String cpf, @RequestParam(required = false) Integer status){
        return agendamentoService.listarAgendamentosPorCpfEStatus(cpf, status);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void alterarAgendamento(@RequestBody Agendamento agendamento){
        agendamentoService.buscarAgendamentoPorId(agendamento.getIdAgendamento()).
                map(agendamentoBase -> {
                    modelMapper.map(agendamento, agendamentoBase);
                    agendamentoService.salvarAgendamento(agendamentoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agendamento buscarAgendamentoPorId(@PathVariable Long id){
        return agendamentoService.buscarAgendamentoPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
    }
}