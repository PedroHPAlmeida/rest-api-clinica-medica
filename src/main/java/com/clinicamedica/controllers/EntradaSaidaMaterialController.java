package com.clinicamedica.controllers;

import com.clinicamedica.entities.EntradaSaidaMaterial;
import com.clinicamedica.services.EntradaSaidaMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/entradasaidamateriais")
public class EntradaSaidaMaterialController {

    @Autowired
    private EntradaSaidaMaterialService entradaSaidaMaterialService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarEntradaSaidaMaterial(@RequestBody EntradaSaidaMaterial entradaSaidaMaterial){
        entradaSaidaMaterialService.salvarEntradaSaidaMaterial(entradaSaidaMaterial);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaSaidaMaterial> listarEntradasSaidasMateriais(){
        return entradaSaidaMaterialService.listarEntradasSaidasMateriais();
    }

    @GetMapping(path = "/buscarporidmaterial/{idMaterial}")
    @ResponseStatus(HttpStatus.OK)
    public List<EntradaSaidaMaterial> listarEntradasSaidasMateriaisPorIdMaterial(@PathVariable Long idMaterial){
        return entradaSaidaMaterialService.buscarEntradaSaidaMaterialPorIdMaterial(idMaterial);
    }
}
