package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity(name = "medicos")
public class Medico extends Funcionario{

    @Column(name = "crm", nullable = false)
    private String crm;

    @Column(name = "especialidade", nullable = false)
    private String especialidade;
}
