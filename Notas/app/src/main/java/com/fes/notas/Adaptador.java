package com.fes.notas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fes.notas.BD.Nota;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.NotaViewHolder> {

    private ArrayList<Nota> notas;

    public Adaptador(ArrayList<Nota> notas) {
        this.notas = notas;
    }

    @NonNull
    @Override
    public Adaptador.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nota, parent, false);
        NotaViewHolder notaViewHolder = new NotaViewHolder(v);

        return notaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.NotaViewHolder holder, int position) {
        holder.tituloN.setText(notas.get(position).getTituloN());
        holder.textoN.setText(notas.get(position).getTextoN());
        holder.idN.setText(notas.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public static class NotaViewHolder extends RecyclerView.ViewHolder {

        TextView tituloN;
        TextView textoN;
        TextView idN;

        NotaViewHolder(final View v) {
            super(v);

            tituloN = v.findViewById(R.id.tituloN);
            textoN = v.findViewById(R.id.textoN);
            idN = v.findViewById(R.id.idN);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = idN.getText().toString();

                    Intent detalles = new Intent(v.getContext(), DetalleNota.class);
                    detalles.putExtra("ID", id);
                    v.getContext().startActivity(detalles);
                }
            });
        }

    }
}
