package com.clinicamedica.entities;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String getCpf() {
        return cpf;
    }

    public Paciente() {
    }

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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
