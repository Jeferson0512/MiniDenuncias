package com.tecsup.jeferson.minidenuncias.Service;

import com.tecsup.jeferson.minidenuncias.Model.Registro;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Usuario on 13/11/2017.
 */

public interface ApiService {

    String API_BASE_URL = "https://test-lab13-jeferson-0512.cs50.io";

    @GET("api/v1/registro")
    Call<List<Registro>> getRegistros();
}
