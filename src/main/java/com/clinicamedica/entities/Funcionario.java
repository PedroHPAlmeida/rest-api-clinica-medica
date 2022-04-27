package com.clinicamedica.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "funcionarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    @Column(name = "nomeFuncionario", nullable = false)
    private String nomeFuncionario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "setor", nullable = false)
    private String setor;

    @Column(name = "tipoFuncionario", nullable = false)
    private String tipoFuncionario;

    @Column(name = "statusFuncionario", nullable = false)
    private int statusFuncionario;

    public Funcionario() {
    }

    public Funcionario(String nomeFuncionario, String email, String senha, String setor, String tipoFuncionario, int statusFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
        this.email = email;
        this.senha = senha;
        this.setor = setor;
        this.tipoFuncionario = tipoFuncionario;
        this.statusFuncionario = statusFuncionario;
    }
}
