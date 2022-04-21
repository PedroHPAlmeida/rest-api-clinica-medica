package com.clinicamedica.services;

import com.clinicamedica.entities.EntradaSaidaMaterial;
import com.clinicamedica.repositories.IEntradaSaidaMaterialRepository;
import com.clinicamedica.repositories.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaSaidaMaterialService {

    @Autowired
    private IEntradaSaidaMaterialRepository entradaSaidaMaterialRepository;
    @Autowired
    private IMaterialRepository materialRepository;

    public EntradaSaidaMaterial salvarEntradaSaidaMaterial(EntradaSaidaMaterial entradaSaidaMaterial){
        materialRepository.save(entradaSaidaMaterial.getMaterial());
        return entradaSaidaMaterialRepository.save(entradaSaidaMaterial);
    }

    public List<EntradaSaidaMaterial> listarEntradasSaidasMateriais(){
        return entradaSaidaMaterialRepository.findAll();
    }

    public Optional<EntradaSaidaMaterial> buscarEntradaSaidaMaterialPorId(Long id){
        return entradaSaidaMaterialRepository.findById(id);
    }
}
