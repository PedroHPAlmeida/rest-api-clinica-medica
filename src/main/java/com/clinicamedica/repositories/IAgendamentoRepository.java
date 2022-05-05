package com.clinicamedica.repositories;

import com.clinicamedica.entities.Agendamento;
import com.clinicamedica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPaciente(Paciente paciente);
    List<Agendamento> findByStatus(Integer status);
}
