package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "notasFiscais")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "valorNota", nullable = false)
    private Double valorNota;

    @Column(name = "dataEmissao", nullable = false)
    private Date dataEmissao;

    public NotaFiscal(Long id, Double valorNota, String dataEmissao){
        this.id = id;
        this.valorNota = valorNota;
        try{
            this.dataEmissao = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmissao);
        } catch (ParseException e) {
            Logger.getLogger(NotaFiscal.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
