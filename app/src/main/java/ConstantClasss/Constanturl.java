package ConstantClasss;

import android.app.ProgressDialog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by asmaa.mostafa on 13/03/2017.
 */
public class Constanturl {
    //Web service URL
   /* private static Retrofit retrofit = null;
    private static final String API_URL = "https://idealhealthdiet.com.sa/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    ProgressDialog mProgressDialog;

 *//*   private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
        //}*//*

   public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
           .connectTimeout(400000, TimeUnit.SECONDS)
           .readTimeout(400000, TimeUnit.SECONDS).writeTimeout(200000,TimeUnit.SECONDS).build();

    private static Retrofit.Builder builder =
            new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
*/
    }

