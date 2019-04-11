package com.example.sqlite.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.Db.PessoaDAOImpl;
import com.example.sqlite.Db.DbHelper;
import com.example.sqlite.Interfaces.PessoaDAO;
import com.example.sqlite.R;

public class ViewActivity extends AppCompatActivity {

    private Cursor cursor;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final PessoaDAO dao = new PessoaDAOImpl(getBaseContext());

        btnVoltar = findViewById(R.id.btnVoltar);

        cursor = dao.listarPessoas();
        String CPF = this.getIntent().getStringExtra("CPF"); // se na itent exitir um extra cpf ele busca por cpf
        ///senoa lista tudo
        if(CPF == null) {
            if (cursor.getCount() != 0) {
                //monta a listview
                String[] nomeCampos = new String[]{DbHelper.NOME, DbHelper.CPF, DbHelper.EMAIL,DbHelper.FONE,DbHelper.IDADE, DbHelper.ID, DbHelper.DATA_NASCIMENTO};
                int[] idViews = new int[]{R.id.txtNome, R.id.txtCpf, R.id.txtEmail,R.id.txtFone,R.id.txtIdade, R.id.txtID, R.id.textView13};
                final SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.list_activity, cursor, nomeCampos, idViews, 0);
                final ListView lista = (ListView) findViewById(R.id.lista);
                lista.setAdapter(adaptador);

                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ID));
                        Intent intent = new Intent(ViewActivity.this, EditActivity.class);
                        intent.putExtra("codigo", codigo);
                        startActivity(intent);
                        finish();
                    }
                });

                ///com um cliclongo vc remove o lemento da lista
                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ID));
                        dao.deletarPessoa(Integer.valueOf(codigo));
                        Toast.makeText(getApplicationContext(), "Removido!", Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
                //por algum motivo o adaptador nao esta renovando a lista
                adaptador.notifyDataSetChanged();
                } else{
                    Toast.makeText(getApplicationContext(), "Nenhuma pessoa encontrada!", Toast.LENGTH_SHORT).show();
                }
            } else{
                cursor = dao.listarPorCPF(CPF);

            if (cursor.getCount() != 0) {
                String[] nomeCampos = new String[]{DbHelper.NOME, DbHelper.CPF, DbHelper.EMAIL,DbHelper.FONE,DbHelper.IDADE, DbHelper.ID, DbHelper.DATA_NASCIMENTO};
                int[] idViews = new int[]{R.id.txtNome, R.id.txtCpf, R.id.txtEmail,R.id.txtFone,R.id.txtIdade, R.id.txtID, R.id.textView13};
                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.list_activity, cursor, nomeCampos, idViews, 0);
                ListView lista = (ListView) findViewById(R.id.lista);
                lista.setAdapter(adaptador);

                ///com um cliclongo vc remove o lemento da lista
                lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        String codigo;
                        cursor.moveToPosition(position);
                        codigo = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ID));
                        dao.deletarPessoa(Integer.valueOf(codigo));
                        Toast.makeText(getApplicationContext(), "Removido!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    //por algum motivo o adaptador nao esta renovando a lista
                   //     adaptador.notifyDataSetChanged();
                });
            }
            else{
                Toast.makeText(getApplicationContext(), "Nenhuma pessoa encontrada!", Toast.LENGTH_SHORT).show();
            }
        }

        final Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ViewActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
