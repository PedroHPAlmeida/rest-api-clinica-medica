package com.clinicamedica.controllers;

import com.clinicamedica.entities.CategoriaMaterial;
import com.clinicamedica.services.CategoriaMaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categoriasmateriais")
public class CategoriaMaterialController {

    @Autowired
    private CategoriaMaterialService categoriaMaterialService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaMaterial salvarCategoriaMaterial(@RequestBody CategoriaMaterial categoriaMaterial){
        return categoriaMaterialService.salvarCategoriaMaterial(categoriaMaterial);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaMaterial> listarCategoriasMateriais(){
        return categoriaMaterialService.listarCategoriasMateriais();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaMaterial buscarCategoriaMaterialPorid(@PathVariable Long id){
        return categoriaMaterialService.buscarCategoriaMaterialPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de material não encontrada."));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarCategoriaMaterialPorid(@PathVariable Long id, @RequestBody CategoriaMaterial categoriaMaterial){
        categoriaMaterialService.buscarCategoriaMaterialPorId(id)
                .map(categoriaMaterialBase -> {
                    modelMapper.map(categoriaMaterial, categoriaMaterialBase);
                    categoriaMaterialService.salvarCategoriaMaterial(categoriaMaterialBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de material não encontrada."));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCategoriaMaterialPorid(@PathVariable Long id){
        categoriaMaterialService.buscarCategoriaMaterialPorId(id)
                .map(categoriaMaterial -> {
                    categoriaMaterialService.deletarCategoriaMaterialPorId(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria de material não encontrada."));
    }
}
