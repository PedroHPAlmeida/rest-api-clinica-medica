package com.clinicamedica.repositories;

import com.clinicamedica.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialRepository extends JpaRepository<Material, Long> {
}
