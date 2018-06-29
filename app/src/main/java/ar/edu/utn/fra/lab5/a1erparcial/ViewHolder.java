package ar.edu.utn.fra.lab5.a1erparcial;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lkdml on 03/10/2017.
 */

public class ViewHolder  extends RecyclerView.ViewHolder implements  View.OnClickListener{

    TextView nombre;
    TextView apellido;
    TextView telefono;
    ImageView img;
    ItemClickListener listener;

    public ViewHolder(View itemView, ItemClickListener listener) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.vh_nombre);
        apellido = (TextView) itemView.findViewById(R.id.vh_apellido);
        telefono = (TextView) itemView.findViewById(R.id.vh_telefono);
        img = (ImageView) itemView.findViewById(R.id.vh_img);
        this.listener = listener;
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClick(v,getAdapterPosition());
    }
}
