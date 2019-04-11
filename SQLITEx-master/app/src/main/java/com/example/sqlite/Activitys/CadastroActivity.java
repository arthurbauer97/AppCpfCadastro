package com.example.sqlite.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.Model.Pessoa;
import com.example.sqlite.Db.PessoaDAOImpl;
import com.example.sqlite.Interfaces.PessoaDAO;
import com.example.sqlite.R;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastro;
    private Button btnVoltar;
    private EditText campoNome;
    private EditText campoCPF;
    private EditText campoIdade;
    private EditText campoEmail;
    private EditText campoFone;
    private EditText campoDNasc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final PessoaDAO dao = new PessoaDAOImpl(getBaseContext());

        campoNome = findViewById(R.id.txtNome);
        campoCPF = findViewById(R.id.txtCpf);
        campoIdade = findViewById(R.id.txtIdade);
        campoEmail = findViewById(R.id.txtID);
        campoFone = findViewById(R.id.txtFone);
        campoDNasc = findViewById(R.id.txtDNasc);


        btnCadastro = findViewById(R.id.btnSalvar);
        btnVoltar = findViewById(R.id.btnVoltar);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar(dao);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }


    // cadastro de pessoa
    public void cadastrar(PessoaDAO dao) {
        String resultado;
        if (campoNome.getText().toString().trim().length() != 0) {
            if (campoCPF.getText().toString().length() == 11) {
                if (campoIdade.getText().toString().length() != 0) {

                    Pessoa p = new Pessoa(campoNome.getText().toString(), campoCPF.getText().toString(), campoIdade.getText().toString(), campoEmail.getText().toString(), campoFone.getText().toString(), campoDNasc.getText().toString());

                    resultado = dao.cadastrarPessoa(p);
                    Intent it = new Intent(CadastroActivity.this, ViewActivity.class);
                    startActivity(it);

                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Digite uma idade válida", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Digite um cpf válido!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Digite um nome válido!", Toast.LENGTH_SHORT).show();
        }


    }
}
