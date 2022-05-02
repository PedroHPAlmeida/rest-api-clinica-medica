package com.clinicamedica.repositories;

import com.clinicamedica.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
