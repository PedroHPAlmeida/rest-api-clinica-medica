package com.clinicamedica.repositories;

import com.clinicamedica.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepository extends JpaRepository<Medico, Long> {
}
