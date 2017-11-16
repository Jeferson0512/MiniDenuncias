package com.tecsup.jeferson.minidenuncias.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tecsup.jeferson.minidenuncias.Activity.DetailActivity;
import com.tecsup.jeferson.minidenuncias.Model.Registro;
import com.tecsup.jeferson.minidenuncias.Model.ResponseMessage;
import com.tecsup.jeferson.minidenuncias.R;
import com.tecsup.jeferson.minidenuncias.Service.ApiService;
import com.tecsup.jeferson.minidenuncias.Service.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Usuario on 13/11/2017.
 */

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.ViewHolder>{

    private static final String TAG = RegistroAdapter.class.getSimpleName();

    private List<Registro> registros;

    private Activity activity;

    public RegistroAdapter(Activity activity){
        this.registros = new ArrayList<>();
        this.activity = activity;
    }

    public void setRegistros(List<Registro> registros){
        this.registros = registros;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoImage;
        public TextView tituloText;
        public TextView nombreText;
        public TextView ubicacion;
        public ImageButton menuButton;



        public ViewHolder(View itemView) {
            super(itemView);
            fotoImage = (ImageView) itemView.findViewById(R.id.foto_image);
            tituloText = (TextView) itemView.findViewById(R.id.titulo_text);
            nombreText = (TextView) itemView.findViewById(R.id.nombre_text);
            ubicacion = (TextView) itemView.findViewById(R.id.ubicacion);
            menuButton = (ImageButton) itemView.findViewById(R.id.menu_button);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final Registro registro = this.registros.get(position);

        viewHolder.tituloText.setText(registro.getTitulo());
        viewHolder.nombreText.setText(registro.getTitulo());
        viewHolder.ubicacion.setText(registro.getTitulo());

        String url = ApiService.API_BASE_URL + "/images/" + registro.getImagen();
        Picasso.with(viewHolder.itemView.getContext()).load(url).into(viewHolder.fotoImage);

        viewHolder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove_button:

                                ApiService service = ApiServiceGenerator.createService(ApiService.class);

                                Call<ResponseMessage> call = service.destroyRegistro(registro.getId());

                                call.enqueue(new Callback<ResponseMessage>() {
                                    @Override
                                    public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                        try {

                                            int statusCode = response.code();
                                            Log.d(TAG, "HTTP status code: " + statusCode);

                                            if (response.isSuccessful()) {

                                                ResponseMessage responseMessage = response.body();
                                                Log.d(TAG, "responseMessage: " + responseMessage);

                                                // Eliminar item del recyclerView y notificar cambios
                                                registros.remove(position);
                                                notifyItemRemoved(position);
                                                notifyItemRangeChanged(position, registros.size());

                                                Toast.makeText(v.getContext(), responseMessage.getMessage(), Toast.LENGTH_LONG).show();

                                            } else {
                                                Log.e(TAG, "onError: " + response.errorBody().string());
                                                throw new Exception("Error en el servicio");
                                            }

                                        } catch (Throwable t) {
                                            try {
                                                Log.e(TAG, "onThrowable: " + t.toString(), t);
                                                Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                            }catch (Throwable x){}
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                        Log.e(TAG, "onFailure: " + t.toString());
                                        Toast.makeText(v.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    }

                                });

                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("ID", registro.getId());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.registros.size();
    }
}