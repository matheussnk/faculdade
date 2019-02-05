package com.example.internet.appctt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //Informações Tabela

    public static final String TABELA = "contato";
    public static final String CONTATO_ID = "_id";
    public static final String CONTATO_NOME = "nome";
    public static final String CONTATO_EMAIL = "email";

    //Informações do Banco de Dados
    static final String DB_NAME = "CONTATO.DB";
    static final int DB_VERSION = 1;

    //String de Criação da Tabela Contato;

    private static final String CREATE_TABLE = "create table "
            + TABELA + " ("
            + CONTATO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CONTATO_NOME + " TEXT NOT NULL, "
            + CONTATO_EMAIL + " TEXT NOT NULL);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,  DB_NAME, factory, DB_VERSION);
    }

    public DBHelper(Context context){
        super(context,  DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
