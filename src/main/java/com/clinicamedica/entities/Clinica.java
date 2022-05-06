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
    private String municipio;
    @Column
    private String uf;

    public Clinica(String nome, String cnpj, String endereco, String municipio, String uf) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.municipio = municipio;
        this.uf = uf;
    }
}
