package com.clinicamedica.services;

import com.clinicamedica.entities.CategoriaMaterial;
import com.clinicamedica.entities.Fabricante;
import com.clinicamedica.entities.Material;
import com.clinicamedica.repositories.ICategoriaMaterialRepository;
import com.clinicamedica.repositories.IFabricanteRepository;
import com.clinicamedica.repositories.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private ICategoriaMaterialRepository categoriaMaterialRepository;

    @Autowired
    private IFabricanteRepository fabricanteRepository;

    public Material salvarMaterial(Material material){
        categoriaMaterialRepository.save(material.getCategoriaMaterial());
        fabricanteRepository.save(material.getFabricante());
        return materialRepository.save(material);
    }

    public List<Material> listarMateriais(){
        return materialRepository.findAll();
    }

    public Optional<Material> buscarMaterialPorId(Long id){
        return materialRepository.findById(id);
    }

    public void deletarMaterialPorId(Long id){
        materialRepository.deleteById(id);
    }
}
