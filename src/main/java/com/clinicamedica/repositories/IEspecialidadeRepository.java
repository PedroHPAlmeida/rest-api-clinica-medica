package com.clinicamedica.repositories;

import com.clinicamedica.entities.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
