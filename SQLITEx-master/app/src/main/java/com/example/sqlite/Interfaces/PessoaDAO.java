package com.example.sqlite.Interfaces;

import android.database.Cursor;

import com.example.sqlite.Model.Pessoa;

public interface PessoaDAO {

    String cadastrarPessoa(Pessoa p);
    Cursor listarPessoas();
    Cursor listarPorID(int id);
    Cursor listarPorCPF(String cpf);
    String alterarPessoa(int id, Pessoa p);
    void deletarPessoa(int id);

}
