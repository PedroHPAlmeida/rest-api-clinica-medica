package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @OneToOne
    private Agendamento agendamento;

    @Column(nullable = false)
    private LocalDate data = LocalDate.now();

    @Column
    private String observacoes;

    @Column(nullable = false)
    private String diagnostico;
}
