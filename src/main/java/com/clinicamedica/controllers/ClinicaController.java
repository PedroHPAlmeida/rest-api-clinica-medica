package com.clinicamedica.controllers;

import com.clinicamedica.entities.Clinica;
import com.clinicamedica.services.ClinicaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarClinica(@RequestBody Clinica clinica){
        clinicaService.salvarClinica(clinica);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Clinica> listarClinicas(){
        return clinicaService.listarClinicas();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Clinica buscarClinicaPorId(@PathVariable Long id){
        return clinicaService.buscarClinicaPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada."));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarClinica(@RequestBody Clinica clinica){
        clinicaService.buscarClinicaPorId(clinica.getIdClinica())
                .map(clinicaBase -> {
                    modelMapper.map(clinica, clinicaBase);
                    clinicaService.salvarClinica(clinicaBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clínica não encontrada."));
    }
}
