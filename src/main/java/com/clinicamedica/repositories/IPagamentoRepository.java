package com.clinicamedica.repositories;

import com.clinicamedica.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
}
