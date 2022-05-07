package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClinica;

    @Column
    private String nome;
    @Column
    private String cnpj;
    @Column
    private String endereco;
    @Column
    private String inscricaoMunicipal;
    @Column
    private String atividade;

    public Clinica(String nome, String cnpj, String endereco, String inscricaoMunicipal, String atividade) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.atividade = atividade;
    }
}
