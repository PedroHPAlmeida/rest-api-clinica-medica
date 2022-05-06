package com.clinicamedica.repositories;

import com.clinicamedica.entities.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClinicaRepository extends JpaRepository<Clinica, Long> {
}
