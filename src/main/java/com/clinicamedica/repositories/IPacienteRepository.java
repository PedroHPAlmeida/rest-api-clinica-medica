package com.clinicamedica.repositories;

import com.clinicamedica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, String> {
}
