package com.clinicamedica.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagamentoView {

    private Long idAgendamento;
    private Double valor;
    private Integer status;
    private Integer formaDePagamento;
    private Double desconto;
}
