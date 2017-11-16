package com.tecsup.jeferson.minidenuncias.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.tecsup.jeferson.minidenuncias.Model.Registro;
import com.tecsup.jeferson.minidenuncias.R;
import com.tecsup.jeferson.minidenuncias.Service.ApiService;
import com.tecsup.jeferson.minidenuncias.Service.ApiServiceGenerator;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private Integer id;

    private ImageView fotoImage;
    private TextView nombreText;
    private TextView detallesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        fotoImage = (ImageView)findViewById(R.id.foto_image);
        nombreText = (TextView)findViewById(R.id.nombre_text);
        detallesText = (TextView)findViewById(R.id.detalles_text);

        id = getIntent().getExtras().getInt("ID");
        Log.e(TAG, "id:" + id);

        initialize();
    }

    private void initialize() {

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Registro> call = service.showProducto(id);

        call.enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        Registro registro = response.body();
                        Log.d(TAG, "producto: " + registro);

                        String url = ApiService.API_BASE_URL + "/images/" + registro.getImagen();
                        Picasso.with(DetailActivity.this).load(url).into(fotoImage);

                        nombreText.setText(registro.getTitulo());
                        detallesText.setText(registro.getDescripcion());

                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

}
