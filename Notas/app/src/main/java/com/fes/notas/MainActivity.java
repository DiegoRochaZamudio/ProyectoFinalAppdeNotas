package com.fes.notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fes.notas.BD.BDHelper;
import com.fes.notas.BD.Nota;
import com.fes.notas.BD.NotaRW;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotas;
    private FloatingActionButton fabNuevo;
    private TextView tvSinNotas;

    private ArrayList<Nota> notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNotas = findViewById(R.id.rvNotas);
        fabNuevo = findViewById(R.id.fabNuevo);
        tvSinNotas = findViewById(R.id.tvSinNotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvNotas.setLayoutManager(linearLayoutManager);
        rvNotas.setHasFixedSize(false);

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevo = new Intent(MainActivity.this, NuevaNota.class);
                startActivity(nuevo);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Obtener notas de BD:
        BDHelper bdHelper = new BDHelper(this);
        NotaRW notaRW = new NotaRW(bdHelper);

        notas = notaRW.leerNotas();

        if (notas.size() > 0) {
            tvSinNotas.setVisibility(View.GONE);
            Adaptador adaptador = new Adaptador(notas);
            rvNotas.setAdapter(adaptador);
        } else {
            tvSinNotas.setVisibility(View.VISIBLE);
        }
    }
}
