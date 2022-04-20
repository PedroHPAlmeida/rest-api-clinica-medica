package com.clinicamedica.services;

import com.clinicamedica.entities.Fabricante;
import com.clinicamedica.repositories.IFabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    @Autowired
    private IFabricanteRepository fabricanteRepository;

    public Fabricante salvarFabricante(Fabricante fabricante){
        return fabricanteRepository.save(fabricante);
    }

    public List<Fabricante> listarFabricantes(){
        return fabricanteRepository.findAll();
    }

    public Optional<Fabricante> buscarFabricantePorCnpj(String cnpj){
        return fabricanteRepository.findById(cnpj);
    }

    public void deletarFabricantePorCnpj(String cnpj){
        fabricanteRepository.deleteById(cnpj);
    }
}
