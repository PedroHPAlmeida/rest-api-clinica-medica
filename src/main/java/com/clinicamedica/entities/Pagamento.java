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
@Entity(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagamento;

    @OneToOne
    private Agendamento agendamento;

    @OneToOne
    private NotaFiscal notaFiscal;

    @Column(nullable = false)
    private LocalDate data = LocalDate.now();

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer formaDePagamento;

    @Column()
    private Double desconto;

    public Pagamento(Agendamento agendamento, NotaFiscal notaFiscal, Double valor, Integer status, Integer formaDePagamento, Double desconto) {
        this.agendamento = agendamento;
        this.notaFiscal = notaFiscal;
        this.valor = valor;
        this.status = status;
        this.formaDePagamento = formaDePagamento;
        this.desconto = desconto;
    }
}
