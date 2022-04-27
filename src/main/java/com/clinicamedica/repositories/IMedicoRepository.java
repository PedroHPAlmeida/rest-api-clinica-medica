package com.clinicamedica.repositories;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByEspecialidade(Especialidade especialidade);
}
