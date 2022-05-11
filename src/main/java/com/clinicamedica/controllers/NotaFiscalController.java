package com.clinicamedica.controllers;

import com.clinicamedica.entities.NotaFiscal;
import com.clinicamedica.entities.Paciente;
import com.clinicamedica.services.NotaFiscalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/notasfiscais")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaFiscal salvarNotaFiscal(@RequestBody NotaFiscal notaFiscal){
        return notaFiscalService.salvarNotaFiscal(notaFiscal);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotaFiscal> listarNotasFiscais(){
        return notaFiscalService.listarNotasFiscais();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotaFiscal buscarNotaFiscalPorId(@PathVariable Long id){
        return notaFiscalService.buscarNotaFiscalPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota Fiscal não encontrada."));
    }

    @GetMapping(path = "/buscar-paciente-por-nota-fiscal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Paciente buscarPacientePorIdNotaFiscal(@PathVariable Long id){
        return notaFiscalService.buscarPacientePorIdNotaFiscal(id);
    }

    @GetMapping(path = "/listar-por-periodo")
    @ResponseStatus(HttpStatus.OK)
    public List<NotaFiscal> listarNotasPorPeriodo(@RequestParam(required = false) Integer dias){
        return notaFiscalService.listarNotasPorPeriodo(dias);
    }
}
