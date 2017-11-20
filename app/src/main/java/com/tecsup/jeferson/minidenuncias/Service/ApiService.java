package com.tecsup.jeferson.minidenuncias.Service;

import com.tecsup.jeferson.minidenuncias.Model.Registro;
import com.tecsup.jeferson.minidenuncias.Model.ResponseMessage;
import com.tecsup.jeferson.minidenuncias.Model.Usuario;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Usuario on 13/11/2017.
 */

public interface ApiService {

    String API_BASE_URL = "https://practica-lab13-jeferson-0512.cs50.io";

    @GET("/denuncias/r1/user_listado")
    Call<List<Usuario>> getUsuario();
    @FormUrlEncoded
    @GET("/denuncias/r1/user_login")
    Call<ResponseMessage> loginUsuario(@Field("username") String username,
                                       @Field("password") String password);

    @GET("/denuncias/r1/registro ")
    Call<List<Registro>> getRegistros();

    @FormUrlEncoded
    @POST("/denuncias/r1/registro")
    Call<ResponseMessage> createRegistro(@Field("titulo") String titulo,
                                         @Field("descripcion") String descripcion);
    @Multipart
    @POST("/denuncias/r1/registro")
    Call<ResponseMessage> createRegistroWithImage(
            @Part("titulo") RequestBody titulo,
            @Part("descripcion") RequestBody descripcion,
            @Part MultipartBody.Part imagen
    );

    @DELETE("/denuncias/r1/registro/{id}")
    Call<ResponseMessage> destroyRegistro(@Path("id") Integer id);

    @GET("/denuncias/r1/registro/{id}")
    Call<Registro> showProducto(@Path("id") Integer id);
}
