package com.fes.notas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fes.notas.BD.BDHelper;
import com.fes.notas.BD.Nota;
import com.fes.notas.BD.NotaRW;

public class DetalleNota extends AppCompatActivity {

    private TextView tituloNota;
    private TextView textoNota;

    BDHelper bdHelper;
    NotaRW notaRW;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_nota);

        ActionBar barra = getSupportActionBar();
        barra.setTitle("Nota");
        barra.setDisplayHomeAsUpEnabled(true);

        tituloNota = findViewById(R.id.tituloNota);
        textoNota = findViewById(R.id.textoNota);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle extra = getIntent().getExtras();
        id = extra.getString("ID");

        bdHelper = new BDHelper(this);
        notaRW = new NotaRW(bdHelper);
        Nota nota = notaRW.leerNota(id);

        tituloNota.setText(nota.getTituloN());
        textoNota.setText(nota.getTextoN());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.editar:
                Intent editar = new Intent(DetalleNota.this, EditarNota.class);
                editar.putExtra("ID", id);
                startActivity(editar);
                break;

            case R.id.eliminar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmar eliminación")
                        .setMessage("¿Quieres eliminar la nota?")
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notaRW.eliminarNota(id);
                                onBackPressed();
                            }
                        });

                builder.create().show();
                break;

            case android.R.id.home:
                onBackPressed();
        }

        return true;
    }
}
