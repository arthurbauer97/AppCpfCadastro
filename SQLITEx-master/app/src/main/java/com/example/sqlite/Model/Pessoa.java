package com.example.sqlite.Model;

import java.io.Serializable;

/**
 */

///ClaSSE "BEANS" de pessoas

public class Pessoa implements Serializable {
    private   int _id;
    private String nome;
    private String cpf;
    private  int idade;
    private String email;
    private String fone;
    private String dataNascimento;

//usei apenas esse construtor
    public Pessoa(String nome, String cpf, String idade, String email, String fone, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = Integer.valueOf(idade);;
        this.email = email;
        this.fone = fone;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(int _id) {
        this._id = _id;
    }

    public Pessoa(int _id, String nome, String cpf, String idade, String email, String fone, String dataNascimento) {
        this._id = _id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = Integer.valueOf(idade);
        this.email = email;
        this.fone = fone;
        this.dataNascimento = dataNascimento;
    }


    //metodos gets e sets
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
