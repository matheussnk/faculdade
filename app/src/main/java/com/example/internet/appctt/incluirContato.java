package com.example.internet.appctt;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class incluirContato extends AppCompatActivity {

    EditText nome, eMail;
    Button btnIcluir, btnVoltar;
    SQLController dbcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_contato);

        nome = (EditText) findViewById(R.id.edNomeAtz);
        eMail = (EditText) findViewById(R.id.edEmailAtz);
        btnIcluir = (Button) findViewById(R.id.btnAtualizar);
        btnVoltar = (Button) findViewById(R.id.btVoltar);

        dbcon = new SQLController( this);
        dbcon.open();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnIcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNome = nome.getText().toString();
                String strEmail = eMail.getText().toString();
                dbcon.inserirRegistro(strNome, strEmail);
                Intent m = new Intent(incluirContato.this, Principal.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mensagemExibir ( "Inclusão", "Contato adicionado com sucesso!");
                startActivity(m);
            }
        });
    }

    public void mensagemExibir(String titulo, String texto) {
        AlertDialog.Builder mensagem = new
                AlertDialog.Builder(incluirContato.this);
        mensagem.setTitle(titulo);
        mensagem.setMessage(texto);
        mensagem.setNeutralButton("OK", null);
        mensagem.show();
    }

}
