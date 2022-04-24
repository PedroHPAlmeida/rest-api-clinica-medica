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
@Entity(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServico;

    @Column(name = "nomeServico", nullable = false)
    private String nomeServico;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "descricaoServico")
    private String descricaoServico;

    @ManyToOne(optional = false)
    private Especialidade especialidade;
}
