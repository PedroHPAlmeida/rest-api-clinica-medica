package com.clinicamedica.controllers;

import com.clinicamedica.entities.Medico;
import com.clinicamedica.exceptions.FuncionarioException;
import com.clinicamedica.services.MedicoService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarMedico(@RequestBody Medico medico){
        try{
            medicoService.salvarMedico(medico);
        }
        catch (FuncionarioException exception){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email já cadastrado, tente outro.");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listarMedicos(){
        return medicoService.listarMedicos();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Medico buscarMedicoPorId(@PathVariable Long id){
        return medicoService.buscarMedicoPorId(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado."));
    }

    @GetMapping(path = "/listar-por-id-especialidade")
    @ResponseStatus(HttpStatus.OK)
    public List<Medico> listarMedicosPorIdEspecialidade(@RequestParam(required = false) Long idEspecialidade){
        return medicoService.listarMedicosPorIdEspecialidade(idEspecialidade);
    }

    @PutMapping(path = "/alterar-medico")
    @ResponseStatus(HttpStatus.OK)
    public void alterarMedico(@RequestBody Medico medico){
        try {
            medicoService.alterarMedico(medico);
        } catch (FuncionarioException exception){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Email já cadastrado, tente outro.");
        }
    }
}
