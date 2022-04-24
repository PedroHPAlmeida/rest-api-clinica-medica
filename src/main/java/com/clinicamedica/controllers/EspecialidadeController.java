package com.clinicamedica.controllers;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidade salvarEspecialidade(@RequestBody Especialidade especialidade){
        return especialidadeService.salvarEspecialidade(especialidade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Especialidade> listarEspecialidades(){
        return especialidadeService.listarEspecialidades();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Especialidade buscarEspecialidadePorId(@PathVariable Long id){
        return especialidadeService.buscarEspecialidadePorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada."));
    }
}
