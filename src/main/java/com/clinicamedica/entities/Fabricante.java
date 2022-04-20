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
@Entity(name = "fabricantes")
public class Fabricante {

    @Id
    private String cnpj;

    @Column(name = "nomeFabricante", nullable = false)
    private String nomeFabricante;

    @Column(name = "enderecoFabricante", nullable = false)
    private String enderecoFabricante;

    @Column(name = "contatoFabricante")
    private String contatoFabricante;
}
