package com.example.sqlite.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 */

public class DbHelper extends SQLiteOpenHelper {

    //classe para manipular a criação do Banco

    public static final String NOME_BANCO = "ifSertao.db";
    public static final String TABELA = "pessoas";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String CPF = "cpf";
    public static final String IDADE = "idade";
    public static final String EMAIL = "email";
    public static final String FONE = "fone";
    public static final String DATA_NASCIMENTO = "dataNasciento";

    public static final int VERSAO = 6;

    public DbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("+ID+" integer primary key autoincrement," + NOME +" text not null," + CPF +" integer(11) not null unique,"+ IDADE +" integer(11) not null," + EMAIL + " text not null, "+ FONE +" double(9) not null,"+ DATA_NASCIMENTO +" text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
