package com.clinicamedica.repositories;

import com.clinicamedica.entities.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFabricanteRepository extends JpaRepository<Fabricante, String> {
}
