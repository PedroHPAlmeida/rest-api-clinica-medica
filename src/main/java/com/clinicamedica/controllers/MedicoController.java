package com.clinicamedica.controllers;

import com.clinicamedica.entities.Medico;
import com.clinicamedica.services.MedicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medico salvarMedico(@RequestBody Medico medico){
        return medicoService.salvarMedico(medico);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listarMedicos(){
        return medicoService.listarMedicos();
    }

    @GetMapping(path = "/{crm}")
    @ResponseStatus(HttpStatus.OK)
    public Medico buscarMedicoPorCrm(@PathVariable String crm){
        return medicoService.buscarMedicoPorCrm(crm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico não encontrado."));
    }

    @PutMapping(path = "/{crm}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarMedicoPorCrm(@PathVariable String crm, @RequestBody Medico medico){
        medicoService.buscarMedicoPorCrm(crm)
                .map(medicoBase -> {
                    modelMapper.map(medico, medicoBase);
                    medicoService.salvarMedico(medicoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico não encontrado."));
    }
}
