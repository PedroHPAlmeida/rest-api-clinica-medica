package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ressarcimentos")
public class Ressarcimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRessarcimento;

    @OneToOne
    private Pagamento pagamento;

    @OneToOne
    private NotaFiscal notaFiscal;

    @Column(nullable = false)
    private LocalDate data = LocalDate.now();

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer formaDeRessarcimento;

    @Column(nullable = false)
    private String motivoRessarcimento;

    public Ressarcimento(Pagamento pagamento, NotaFiscal notaFiscal, Double valor, Integer status, Integer formaDeRessarcimento, String motivoRessarcimento) {
        this.pagamento = pagamento;
        this.notaFiscal = notaFiscal;
        this.valor = valor;
        this.status = status;
        this.formaDeRessarcimento = formaDeRessarcimento;
        this.motivoRessarcimento = motivoRessarcimento;
    }
}
