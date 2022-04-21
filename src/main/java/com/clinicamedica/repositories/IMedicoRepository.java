package com.clinicamedica.repositories;

import com.clinicamedica.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByCrm(String crm);

    void deleteByCrm(String crm);
}
