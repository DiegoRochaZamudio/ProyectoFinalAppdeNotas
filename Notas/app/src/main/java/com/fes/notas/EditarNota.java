package com.fes.notas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fes.notas.BD.BDHelper;
import com.fes.notas.BD.Nota;
import com.fes.notas.BD.NotaRW;
import com.google.android.material.snackbar.Snackbar;

public class EditarNota extends AppCompatActivity {

    private EditText tituloNota;
    private EditText textoNota;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nota);

        ActionBar barra = getSupportActionBar();
        barra.setTitle("Editar nota");
        barra.setDisplayHomeAsUpEnabled(true);

        tituloNota = findViewById(R.id.tituloNota);
        textoNota = findViewById(R.id.textoNota);
        btnGuardar = findViewById(R.id.btnGuardar);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("ID");

        BDHelper bdHelper = new BDHelper(this);
        final NotaRW notaRW = new NotaRW(bdHelper);

        Nota nota = notaRW.leerNota(id);
        tituloNota.setText(nota.getTituloN());
        textoNota.setText(nota.getTextoN());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = tituloNota.getText().toString();
                String texto = textoNota.getText().toString();

                if(titulo.isEmpty() || texto.isEmpty()){
                    Snackbar.make(btnGuardar, "No puede dejar campos vacíos", Snackbar.LENGTH_LONG).show();
                }else{
                    Nota nota = new Nota();
                    nota.setTituloN(titulo);
                    nota.setTextoN(texto);
                    nota.setId(id);

                    notaRW.editarNota(nota);

                    Intent detalle = new Intent(EditarNota.this, DetalleNota.class);
                    detalle.putExtra("ID", id);
                    detalle.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(detalle);
                }
            }
        });
    }
}
