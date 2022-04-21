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
@Entity(name = "materiais")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterial;

    @Column(name = "nomeMaterial", nullable = false)
    private String nomeMaterial;

    @Column(name = "unidadeDeMedida", nullable = false)
    private String unidadeDeMedida;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(optional = false)
    private CategoriaMaterial categoriaMaterial;

    @ManyToOne(optional = false)
    private Fabricante fabricante;
}
