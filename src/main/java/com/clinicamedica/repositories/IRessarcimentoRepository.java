package com.clinicamedica.repositories;

import com.clinicamedica.entities.Pagamento;
import com.clinicamedica.entities.Ressarcimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRessarcimentoRepository extends JpaRepository<Ressarcimento, Long> {

    Optional<Ressarcimento> findByPagamento(Pagamento pagamento);
}
