package com.clinicamedica.repositories;

import com.clinicamedica.entities.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
