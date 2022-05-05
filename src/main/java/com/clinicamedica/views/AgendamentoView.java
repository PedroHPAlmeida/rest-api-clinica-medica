package com.clinicamedica.views;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoView {

    private Long recepcionistaId;
    private String pacienteCpf;
    private Long medicoId;
    private LocalDate dataAgendada;
    private LocalTime horaAgendada;
    private Long servicoId;
    private Integer status;
}
