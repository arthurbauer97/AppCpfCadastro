package com.example.sqlite.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.Model.Pessoa;
import com.example.sqlite.Db.PessoaDAOImpl;
import com.example.sqlite.Db.DbHelper;
import com.example.sqlite.Interfaces.PessoaDAO;
import com.example.sqlite.R;

public class EditActivity extends AppCompatActivity {

    Cursor cursor;
    String codigo;
    EditText edtNome;
    EditText edtCpf;
    EditText edtIdade;
    EditText edtFone;
    EditText edtEmail;
    EditText edtDataNascimento;
    String resultado;
    Button btnAlterar, btnVoltar;

    @Override
    // ao ser criado a activity edit faz !
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final PessoaDAO dao = new PessoaDAOImpl(getBaseContext());

        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtIdade = findViewById(R.id.edtIdade);
        edtFone = findViewById(R.id.edtFone);
        edtEmail = findViewById(R.id.edtEmail);
        edtDataNascimento = findViewById(R.id.edtDataNascimento);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnVoltar = findViewById(R.id.btnVoltar);

        codigo = this.getIntent().getStringExtra("codigo");
        cursor = dao.listarPorID(Integer.parseInt(codigo));

        edtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.NOME)));
        edtCpf.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.CPF)));
        edtIdade.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.IDADE)));
        edtFone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.FONE)));
        edtEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.EMAIL)));
        edtDataNascimento.setText(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.DATA_NASCIMENTO)));

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterar(dao);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EditActivity.this,ViewActivity.class);
                startActivity(it);
            }
        });

    }


    // metodo alterar
    public void alterar(PessoaDAO dao) {
        if(edtNome.getText().toString().trim().length() != 0) {
            if (edtCpf.getText().toString().length() == 11) {
                if(edtIdade.getText().toString().length() != 0) {

                    Pessoa p = new Pessoa(edtNome.getText().toString(),
                            edtCpf.getText().toString(),
                            edtIdade.getText().toString(),
                            edtEmail.getText().toString(),
                            edtFone.getText().toString(),
                            edtDataNascimento.getText().toString());

                    resultado = dao.alterarPessoa(Integer.parseInt(codigo), p);
                    Intent it = new Intent(EditActivity.this,ViewActivity.class);
                    startActivity(it);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Digite uma idade válida", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Digite um cpf válido!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite um nome válido!", Toast.LENGTH_SHORT).show();
        }
    }

}
