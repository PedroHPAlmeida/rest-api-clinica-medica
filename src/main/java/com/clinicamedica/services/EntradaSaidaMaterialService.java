package com.clinicamedica.services;

import com.clinicamedica.entities.EntradaSaidaMaterial;
import com.clinicamedica.entities.Material;
import com.clinicamedica.repositories.IEntradaSaidaMaterialRepository;
import com.clinicamedica.repositories.IMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaSaidaMaterialService {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private IEntradaSaidaMaterialRepository entradaSaidaMaterialRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    public EntradaSaidaMaterial salvarEntradaSaidaMaterial(EntradaSaidaMaterial entradaSaidaMaterial){
        if(entradaSaidaMaterial.getMaterial().getIdMaterial() == null){
            materialService.salvarMaterial(entradaSaidaMaterial.getMaterial());
        }
        if(entradaSaidaMaterial.getFuncionario().getIdFuncionario() == null){
            funcionarioService.salvarFuncionario(entradaSaidaMaterial.getFuncionario());
        }
        return entradaSaidaMaterialRepository.save(entradaSaidaMaterial);
    }

    public List<EntradaSaidaMaterial> listarEntradasSaidasMateriais(){
        return entradaSaidaMaterialRepository.findAll();
    }

    public Optional<EntradaSaidaMaterial> buscarEntradaSaidaMaterialPorId(Long id){
        return entradaSaidaMaterialRepository.findById(id);
    }

    public List<EntradaSaidaMaterial> buscarEntradaSaidaMaterialPorIdMaterial(Long idMaterial) {
        Optional<Material> optionalMaterial = materialService.buscarMaterialPorId(idMaterial);
        if(!optionalMaterial.isEmpty()){
            return entradaSaidaMaterialRepository.findByMaterial(optionalMaterial.get());
        }
        return Collections.emptyList();
    }
}
