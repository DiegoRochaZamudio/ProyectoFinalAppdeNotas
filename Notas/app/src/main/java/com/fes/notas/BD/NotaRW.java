package com.fes.notas.BD;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NotaRW {

    private BDHelper bdHelper;

    public NotaRW(BDHelper bdHelper) {
        this.bdHelper = bdHelper;
    }

    public ArrayList<Nota> leerNotas(){

        ArrayList<Nota> notas = new ArrayList<Nota>();
        String sentencia = "SELECT * FROM notas";
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        Cursor cursor = bd.rawQuery(sentencia, null);

        if(cursor.moveToFirst()){

            do{
                Nota nota = new Nota();
                nota.setId(cursor.getString(0));
                nota.setTituloN(cursor.getString(1));
                nota.setTextoN(cursor.getString(2));

                notas.add(nota);

            }while (cursor.moveToNext());
        }

        bd.close();

        return notas;
    }

    public Nota leerNota(String id){

        Nota nota = new Nota();
        String sentencia = "SELECT * FROM notas WHERE _id=" + id;
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        Cursor cursor = bd.rawQuery(sentencia, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            nota.setId(cursor.getString(0));
            nota.setTituloN(cursor.getString(1));
            nota.setTextoN(cursor.getString(2));

        }

        bd.close();

        return nota;
    }

    public Nota nuevaNota(Nota nota){

        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("titulo", nota.getTituloN());
        cv.put("texto", nota.getTextoN());

        bd.insert("notas", null, cv);
        bd.close();

        return nota;
    }

    public Nota editarNota(Nota nota){

        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        String sentencia = "UPDATE notas SET titulo=\'" + nota.getTituloN() + "\', texto=\'" + nota.getTextoN() +
                "\' WHERE _id=" + nota.getId();

        bd.execSQL(sentencia);

        bd.close();

        return nota;

    }

    public void eliminarNota(String id) {
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        String sentencia = "DELETE FROM notas WHERE _id=" + id;

        bd.execSQL(sentencia);
        bd.close();
    }

}





