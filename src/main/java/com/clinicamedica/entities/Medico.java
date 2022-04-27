package com.clinicamedica.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "medicos")
public class Medico extends Funcionario{
    @Column(name = "crm", nullable = false)
    private String crm;
    @ManyToOne(optional = false)
    private Especialidade especialidade;

    public Medico(){
    }

    public Medico(String nomeFuncionario, String email, String senha, String setor, String tipoFuncionario, String crm, Especialidade especialidade) {
        super(nomeFuncionario, email, senha, setor, tipoFuncionario);
        this.crm = crm;
        this.especialidade = especialidade;
    }
}
