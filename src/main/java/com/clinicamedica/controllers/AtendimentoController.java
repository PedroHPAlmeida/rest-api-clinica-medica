package com.clinicamedica.controllers;

import com.clinicamedica.entities.Atendimento;
import com.clinicamedica.services.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarAtendimento(@RequestBody Atendimento atendimento){
        atendimentoService.salvarAtendimento(atendimento);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Atendimento> listarAtendimentos(){
        return atendimentoService.listarAtendimentos();
    }
}
