package com.clinicamedica.controllers;

import com.clinicamedica.services.AgendamentoService;
import com.clinicamedica.views.AgendamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}