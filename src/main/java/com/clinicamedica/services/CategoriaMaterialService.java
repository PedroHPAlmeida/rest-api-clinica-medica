package com.clinicamedica.services;

import com.clinicamedica.entities.CategoriaMaterial;
import com.clinicamedica.repositories.ICategoriaMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaMaterialService {

    @Autowired
    private ICategoriaMaterialRepository categoriaMaterialRepository;

    public CategoriaMaterial salvarCategoriaMaterial(CategoriaMaterial categoriaMaterial){
        return categoriaMaterialRepository.save(categoriaMaterial);
    }

    public List<CategoriaMaterial> listarCategoriasMateriais(){
        return categoriaMaterialRepository.findAll();
    }

    public Optional<CategoriaMaterial> buscarCategoriaMaterialPorId(Long id){
        return categoriaMaterialRepository.findById(id);
    }

    public void deletarCategoriaMaterialPorId(Long id){
        categoriaMaterialRepository.deleteById(id);
    }
}
