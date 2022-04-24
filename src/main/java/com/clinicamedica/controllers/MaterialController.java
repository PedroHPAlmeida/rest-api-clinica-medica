package com.clinicamedica.controllers;

import com.clinicamedica.entities.CategoriaMaterial;
import com.clinicamedica.entities.Fabricante;
import com.clinicamedica.entities.Material;
import com.clinicamedica.services.CategoriaMaterialService;
import com.clinicamedica.services.FabricanteService;
import com.clinicamedica.services.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/materiais")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarMaterial(@RequestBody Material material){
        materialService.salvarMaterial(material);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Material> listarMateriais(){
        return materialService.listarMateriais();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Material buscarMaterialPorId(@PathVariable Long id){
        return materialService.buscarMaterialPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado."));
    }

    @GetMapping(path = "/idcategoria")
    @ResponseStatus(HttpStatus.OK)
    public List<Material> listarMateriaisPorCategoria(@RequestParam(required = false) Long idCategoria){
        return materialService.listarMateriaisPorCategoria(idCategoria);
    }

    @PutMapping(path = "/alterarmaterial")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarMaterialPorId(@RequestBody Material material){
        materialService.buscarMaterialPorId(material.getIdMaterial())
                .map(materialBase -> {
                    modelMapper.map(material, materialBase);
                    materialService.salvarMaterial(materialBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado."));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMaterialPorId(@PathVariable Long id){
        materialService.buscarMaterialPorId(id)
                .map(material -> {
                    materialService.deletarMaterialPorId(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado."));
    }
}
