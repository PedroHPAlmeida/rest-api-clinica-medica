package com.clinicamedica.repositories;

import com.clinicamedica.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicoRepository extends JpaRepository<Servico, Long> {
}
