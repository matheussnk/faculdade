package com.example.internet.appctt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ModificarContato extends AppCompatActivity {

    EditText tNome, tEmail;
    TextView tID;
    Button btAtualizar, btExcluir, btVoltar;


    long id;

    SQLController dbcon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_contato);
        try {
            dbcon = new SQLController(this);
            dbcon.open();

            tNome = (EditText) findViewById(R.id.edNomeAtz);
            tEmail = (EditText) findViewById(R.id.edEmailAtz);
            tID = (TextView) findViewById(R.id.tvID);

            btAtualizar = (Button) findViewById(R.id.btnAtualizar);
            btExcluir = (Button) findViewById(R.id.btnEExcluir);
            btVoltar = (Button) findViewById(R.id.btVoltar);

            Intent i= getIntent();

            String memberID = i.getStringExtra("memberID");
            String memberName = i.getStringExtra("memberName");
            final String memberEmail = i.getStringExtra("memberEmail");

            id = Long.parseLong(memberID);

            tID.setText(memberID);
            tNome.setText(memberName);
            tEmail.setText(memberEmail);

            btExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbcon.excluirRegistro(id);
                    voltarPrincipal();
                }
            });

            btAtualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String memName_upd = tNome.getText().toString();
                    String memEmail_upd = tEmail.getText().toString();
                    dbcon.atualizarRegistro(id, memName_upd, memEmail_upd);
                    voltarPrincipal();
                }
            });

            btVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });

        } catch (Exception e)  {
            Log.e("ModificarContato", e.toString());
        }
    }

   public void voltarPrincipal() {

        Intent pi = new Intent(getApplicationContext(),
                Principal.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(pi);
    }
}
