package com.clinicamedica.repositories;

import com.clinicamedica.entities.EntradaSaidaMaterial;
import com.clinicamedica.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEntradaSaidaMaterialRepository extends JpaRepository<EntradaSaidaMaterial, Long> {
    List<EntradaSaidaMaterial> findByMaterial(Material material);
}
