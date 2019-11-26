package com.fes.notas.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    private static final String sentencia = "CREATE TABLE notas (_id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, texto TEXT)";
    private static final String nombreBD = "nota.sqlite";
    private static final int versionBD = 1;

    public BDHelper(@Nullable Context context) {
        super(context, nombreBD, null, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sentencia);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE notas");

        onCreate(sqLiteDatabase);
    }
}
