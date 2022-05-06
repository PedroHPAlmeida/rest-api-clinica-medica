package com.clinicamedica.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RessarcimentoView {

    private Long idPagamento;
    private Double valor;
    private Integer status;
    private Integer formaDeRessarcimento;
    private String motivoRessarcimento;
}
