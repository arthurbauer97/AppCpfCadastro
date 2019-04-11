package com.example.sqlite.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite.R;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar, btnVisualizar, btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = findViewById(R.id.btnAdd);
        btnVisualizar = findViewById(R.id.btnView);
        btnPesquisar = findViewById(R.id.btnPesquisa);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(it);
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //valida se foi digitado um cpf
                EditText cpf = (EditText) findViewById(R.id.editCPF);
                Intent it = new Intent(MainActivity.this,ViewActivity.class);
                if(cpf.getText().toString().length()>0){
                    it.putExtra("CPF", cpf.getText().toString());
                    startActivity(it);
                }else{
                    Toast.makeText(getApplicationContext(), "Informe um CPF para pesquisar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
