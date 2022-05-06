package com.clinicamedica.repositories;

import com.clinicamedica.entities.Agendamento;
import com.clinicamedica.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByAgendamento(Agendamento agendamento);
}
