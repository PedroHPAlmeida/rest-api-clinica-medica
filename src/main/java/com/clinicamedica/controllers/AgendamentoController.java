package com.clinicamedica.controllers;

import com.clinicamedica.entities.Agendamento;
import com.clinicamedica.services.AgendamentoService;
import com.clinicamedica.views.AgendamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AgendamentoView agendamentoView;

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
    public Set<Agendamento> listarAgendamentosPorCpfEStatus(@RequestParam(required = false) String cpf, @RequestParam(required = false) Integer status){
        return agendamentoService.listarAgendamentosPorCpfEStatus(cpf, status);
    }
}