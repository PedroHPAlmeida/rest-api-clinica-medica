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
@Entity(name = "pacientes")
public class Paciente {

    @Id
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "sexo", nullable = false)
    private Integer sexo;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "contato")
    private String contato;

}
