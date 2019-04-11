package com.example.sqlite.Db;

/**
 *
 * Classe para manipular registros no BD
 *
 *
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.Model.Pessoa;
import com.example.sqlite.Interfaces.PessoaDAO;


public class PessoaDAOImpl implements PessoaDAO {
    private SQLiteDatabase db;
    private DbHelper banco;

    public PessoaDAOImpl(Context context){
        banco = new DbHelper(context);
    }

    public PessoaDAOImpl() { }

    public String cadastrarPessoa(Pessoa p){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DbHelper.NOME, p.getNome());
        valores.put(DbHelper.CPF, p.getCpf());
        valores.put(DbHelper.IDADE, p.getIdade());
        valores.put(DbHelper.EMAIL,p.getEmail());
        valores.put(DbHelper.FONE,p.getFone());
        valores.put(DbHelper.DATA_NASCIMENTO, p.getDataNascimento());
        resultado = db.insert(DbHelper.TABELA, null, valores);
        db.close();
        if(resultado == -1)
            return "Erro ao cadastrar pessoa, talvez o cpf já tenha sido cadastrado";
        else
            return "Pessoa cadastrada com sucesso";
    }


    public Cursor listarPessoas(){
        Cursor cursor;
        String[] campos = {DbHelper.ID, DbHelper.NOME, DbHelper.CPF, DbHelper.IDADE, DbHelper.EMAIL, DbHelper.FONE, DbHelper.DATA_NASCIMENTO};
        db = banco.getReadableDatabase();
        cursor = db.query(DbHelper.TABELA, campos, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor listarPorID(int id){
        Cursor cursor;
        String[] campos = {DbHelper.ID, DbHelper.NOME, DbHelper.CPF, DbHelper.IDADE, DbHelper.EMAIL, DbHelper.FONE, DbHelper.DATA_NASCIMENTO};
        String where = DbHelper.ID + "=" +id;
        db = banco.getReadableDatabase();
        cursor = db.query(DbHelper.TABELA,campos,where,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //uso para buscar uma pessoa informando o CPF
    public Cursor listarPorCPF(String cpf){
        Cursor cursor;
        String[] campos = {DbHelper.ID, DbHelper.NOME, DbHelper.CPF, DbHelper.IDADE, DbHelper.EMAIL, DbHelper.FONE, DbHelper.DATA_NASCIMENTO};
        String where = DbHelper.CPF + "=" + cpf;
        db = banco.getReadableDatabase();
        cursor = db.query(DbHelper.TABELA,campos,where,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    /// infelizmentente nao deu tempo de utilizar o ALTERAR
    public String alterarPessoa(int codigo, Pessoa p){
        ContentValues valores;
        long resultado;
        String where;
        db = banco.getWritableDatabase();
        where = DbHelper.ID + "=" + codigo;
        valores = new ContentValues();
        valores.put(DbHelper.NOME, p.getNome());
        valores.put(DbHelper.CPF, p.getCpf());
        valores.put(DbHelper.IDADE, p.getIdade());
        valores.put(DbHelper.EMAIL,p.getEmail());
        valores.put(DbHelper.FONE,p.getFone());
        valores.put(DbHelper.DATA_NASCIMENTO,p.getDataNascimento());
        resultado = db.update(DbHelper.TABELA,valores,where,null);
        db.close();
        if(resultado == -1)
            return "Erro ao alterar pessoa, talvez o cpf já tenha sido cadastrado";
        else
            return "Pessoa alterada com sucesso";
    }


    ///metodo que remove a pessoa

    public void deletarPessoa(int id){
        String where = DbHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(DbHelper.TABELA,where,null);
        db.close();
    }

}