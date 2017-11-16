package com.tecsup.jeferson.minidenuncias.Service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Usuario on 13/11/2017.
 */

public final class ApiServiceGenerator {
    private static OkHttpClient.Builder httpclient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ApiService.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit;

    private ApiServiceGenerator(){

    }

    public static <S> S createService(Class<S> serviceClass){
        if(retrofit == null){
            retrofit = builder.client(httpclient.build()).build();
        }
        return retrofit.create(serviceClass);
    }

}
