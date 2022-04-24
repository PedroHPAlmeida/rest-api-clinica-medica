package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncioario;

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

    @Column(name = "crm")
    private String crm;

    @ManyToOne
    private Especialidade especialidade;
}
