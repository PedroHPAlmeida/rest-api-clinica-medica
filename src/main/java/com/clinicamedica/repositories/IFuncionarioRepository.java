package com.clinicamedica.repositories;

import com.clinicamedica.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
