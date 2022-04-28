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
    private CategoriaMaterialService categoriaMaterialService;

    @Autowired
    private FabricanteService fabricanteService;

    public Material salvarMaterial(Material material){
        if(material.getIdMaterial() == null){
            categoriaMaterialService.salvarCategoriaMaterial(material.getCategoriaMaterial());
        }
        if(material.getFabricante().getCnpj() == null){
            fabricanteService.salvarFabricante(material.getFabricante());
        }
        return materialRepository.save(material);
    }

    public List<Material> listarMateriais(){
        return materialRepository.findAll();
    }

    public List<Material> listarMateriaisPorCategoria(Long idCategoria) {
        if(idCategoria == null){
            return materialRepository.findAll();
        }
        Optional<CategoriaMaterial> categoriaMaterial = categoriaMaterialService.buscarCategoriaMaterialPorId(idCategoria);
        return materialRepository.findByCategoriaMaterial(categoriaMaterial.get());
    }

    public Optional<Material> buscarMaterialPorId(Long id){
        return materialRepository.findById(id);
    }

    public void deletarMaterialPorId(Long id){
        materialRepository.deleteById(id);
    }

}
