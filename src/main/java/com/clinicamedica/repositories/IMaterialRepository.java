package com.clinicamedica.repositories;

import com.clinicamedica.entities.CategoriaMaterial;
import com.clinicamedica.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCategoriaMaterial(CategoriaMaterial idCategoria);
}
