package com.example.internet.appctt;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    Button btnAdicionar;
    Button btnSair;
    ListView lv;
    SQLController dbcon;
    TextView memID_tv, memName_tv, memEmail_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        dbcon = new SQLController( this);
        dbcon.open();

        lv = (ListView) findViewById(R.id.lvContato);

        Button btAdd = (Button) findViewById(R.id.btAddContato);
        Button btSair = (Button) findViewById(R.id.btSair);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Principal.this, incluirContato.class);
                startActivity(it);
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finalizar();
            }
        });

        Cursor cursor = dbcon.carregarDados();
        String[] from = new String[] {
                DBHelper.CONTATO_ID,
                DBHelper.CONTATO_NOME,
                DBHelper.CONTATO_EMAIL

        };

        int [] to = new int[] {R.id.idID, R.id.idNome, R.id.idEmail};

        @SuppressWarnings("deprecation")
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                Principal.this, R.layout.activity_visualizar_contato,
                cursor, from, to);

        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {

                    memID_tv = (TextView) view.findViewById(R.id.idID);
                    memName_tv = (TextView) view.findViewById(R.id.idNome);
                    memEmail_tv = (TextView) view.findViewById(R.id.idEmail);

                    String memberID_val = memID_tv.getText().toString();
                    String memberName_val = memName_tv.getText().toString();
                    String memberEmail_val = memEmail_tv.getText().toString();


                    Intent modif = new Intent(Principal.this, ModificarContato.class);

                    modif.putExtra("memberID", memberID_val);
                    modif.putExtra("memberName", memberName_val);
                    modif.putExtra("memberEmail", memberEmail_val);

                    startActivity(modif);
                } catch (Exception e) {
                    Log.e("Principal", e.toString());
                }

            }
        });

    }

    public  void finalizar(){
        this.finish();
    }
}
