package com.clinicamedica.controllers;

import com.clinicamedica.entities.Medico;
import com.clinicamedica.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarMedico(@RequestBody Medico medico){
        medicoService.salvarMedico(medico);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listarMedicos(){
        return medicoService.listarMedicos();
    }

    @GetMapping(path = "/listar-por-id-especialidade")
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listarMedicosPorIdEspecialidade(@RequestParam(required = false) Long idEspecialidade){
        return medicoService.listarMedicosPorIdEspecialidade(idEspecialidade);
    }
}
