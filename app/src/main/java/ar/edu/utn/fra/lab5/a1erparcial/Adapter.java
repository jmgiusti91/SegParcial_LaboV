package ar.edu.utn.fra.lab5.a1erparcial;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lkdml on 03/10/2017.
 */


public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    LayoutInflater mLInflater;
    List<Modelo> contactos;
    ItemClickListener listener;

    public Adapter(Context context, List<Modelo> modelo, ItemClickListener listener) {
        this.mLInflater = LayoutInflater.from(context);
        this.contactos = modelo;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLInflater.inflate(R.layout.holder,parent,false);
        ViewHolder vh = new ViewHolder(v, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(contactos.get(position).getNombre());
        holder.apellido.setText(contactos.get(position).getApellido());
        holder.telefono.setText(contactos.get(position).getTelefono());


    }

    @Override
    public int getItemCount() {
        return this.contactos.size();
    }

    public Modelo getModeloPosition(int position) {
        return this.contactos.get(position);
    }
}
