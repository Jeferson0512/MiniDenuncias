package com.tecsup.jeferson.minidenuncias.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tecsup.jeferson.minidenuncias.Model.Registro;
import com.tecsup.jeferson.minidenuncias.R;
import com.tecsup.jeferson.minidenuncias.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 13/11/2017.
 */

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.ViewHolder>{

    private List<Registro> registros;

    public RegistroAdapter(){
        this.registros = new ArrayList<>();
    }

    public void setRegistros(List<Registro> registros){
        this.registros = registros;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView tituloText;
        public TextView nombreText;
        public TextView ubicacion;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = (ImageView) itemView.findViewById(R.id.foto_image);
            tituloText = (TextView) itemView.findViewById(R.id.titulo_text);
            nombreText = (TextView) itemView.findViewById(R.id.nombre_text);
            ubicacion = (TextView) itemView.findViewById(R.id.ubicacion);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Registro registro = this.registros.get(position);

        viewHolder.tituloText.setText(registro.getTitulo());
        viewHolder.nombreText.setText(registro.getTitulo());
        viewHolder.ubicacion.setText(registro.getTitulo());

        String url = ApiService.API_BASE_URL + "/images/" + registro.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

    }

    @Override
    public int getItemCount() {
        return this.registros.size();
    }
}