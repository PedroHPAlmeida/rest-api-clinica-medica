package com.clinicamedica.repositories;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServicoRepository extends JpaRepository<Servico, Long> {
    List<Servico> findByEspecialidade(Especialidade especialidade);
}
