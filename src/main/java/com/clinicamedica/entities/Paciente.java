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
@Entity(name = "pacientes")
public class Paciente {

    @Id
    private String cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "sexo", nullable = false)
    private char sexo;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "contato")
    private String contato;

    public Paciente(String cpf, String nome, String dataNascimento, char sexo, String endereco, String contato) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        this.contato = contato;
        try{
            this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
        } catch (ParseException e) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Paciente(String cpf, String nome, String dataNascimento, char sexo, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.endereco = endereco;
        try{
            this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
        } catch (ParseException e) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
