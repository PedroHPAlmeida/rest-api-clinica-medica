package com.clinicamedica.controllers;

import com.clinicamedica.entities.Fabricante;
import com.clinicamedica.services.FabricanteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fabricante salvarFabricante(@RequestBody Fabricante fabricante){
        return fabricanteService.salvarFabricante(fabricante);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Fabricante> listarFabricantes(){
        return fabricanteService.listarFabricantes();
    }

    @GetMapping(path = "/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    public Fabricante buscarFabricantePorCnpj(@PathVariable String cnpj){
        return fabricanteService.buscarFabricantePorCnpj(cnpj)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fabricante não encontrado."));
    }

    @PutMapping(path = "/{cnpj}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarFabricantePorCnpj(@PathVariable String cnpj, @RequestBody Fabricante fabricante){
        fabricanteService.buscarFabricantePorCnpj(cnpj)
                .map(fabricanteBase -> {
                    modelMapper.map(fabricante, fabricanteBase);
                    fabricanteService.salvarFabricante(fabricanteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fabricante não encontrado."));
    }

    @DeleteMapping(path = "/{cnpj}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarFabricantePorCnpj(@PathVariable String cnpj){
       fabricanteService.buscarFabricantePorCnpj(cnpj)
                .map(fabricante -> {
                    fabricanteService.deletarFabricantePorCnpj(cnpj);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fabricante não encontrado."));
    }
}
