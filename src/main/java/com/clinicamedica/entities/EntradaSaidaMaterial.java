package com.clinicamedica.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "entradaSaidaDeMateriais")
public class EntradaSaidaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntradaSaidaMaterial;

    @Column(name = "data", nullable = false)
    private LocalDate data = LocalDate.now();

    @Column(name = "quantidade", nullable = false)
    private Long quantidade;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne(optional = false)
    private Material material;

    public EntradaSaidaMaterial(Long quantidade, String descricao, Material material){
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.material = material;
    }
}
