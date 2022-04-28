package com.clinicamedica.repositories;


import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByEmail(String email);
    String getSenhaByEmail(String email);
    List<Funcionario> findByTipoFuncionario(String tipoFuncionario);
    Integer countByEmail(String email);
}
