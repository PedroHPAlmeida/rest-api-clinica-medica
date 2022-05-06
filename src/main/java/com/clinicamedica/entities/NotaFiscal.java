package com.clinicamedica.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notasFiscais")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotaFiscal;

    @ManyToOne
    private Clinica clinica;

    @Column(name = "valorNota", nullable = false)
    private Double valorNota;

    @Column(name = "dataEmissao", nullable = false)
    private LocalDate dataEmissao;

    @Column
    private Double impostos = 0D;

    public NotaFiscal(Clinica clinica, Double valorNota, LocalDate dataEmissao) {
        this.clinica = clinica;
        this.valorNota = valorNota;
        this.dataEmissao = dataEmissao;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "idNotaFiscal=" + idNotaFiscal +
                ", clinica=" + clinica +
                ", valorNota=" + valorNota +
                ", dataEmissao=" + dataEmissao +
                ", impostos=" + impostos +
                '}';
    }
}
