package com.clinicamedica.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;

    @ManyToOne(optional = false)
    private Funcionario recepcionista;

    @ManyToOne(optional = false)
    private Paciente paciente;

    @ManyToOne(optional = false)
    private Medico medico;

    @Column(name = "data")
    private LocalDate data = LocalDate.now();

    @Column(name = "dataAgendada", nullable = false)
    private LocalDate dataAgendada;

    @Column(name = "horaAgendada", nullable = false)
    private LocalTime horaAgendada;

    @ManyToOne(optional = false)
    private Servico servico;

    @Column(name = "status")
    private int status;

    public Agendamento() {
    }
    public Agendamento(Funcionario recepcionista, Paciente paciente, Medico medico, LocalDate dataAgendada, LocalTime horaAgendada, Servico servico, int status) {
        this.recepcionista = recepcionista;
        this.paciente = paciente;
        this.medico = medico;
        this.dataAgendada = dataAgendada;
        this.horaAgendada = horaAgendada;
        this.servico = servico;
        this.status = status;
    }
}
