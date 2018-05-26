package Presenter;


import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import java.util.HashMap;

import Model.Addfavouritmodel;
import Model.Addproductmodel;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asmaa on 02/05/2018.
 */

public class ConstantMethods {
    Context context;
    public ACProgressFlower dialog;
    public ConstantMethods(Context context){
        this.context = context;
    }
    public void addProduct(String title,String description,String price,int owner_id,int category_id,String def_img){
        dialog = new ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        HashMap input = new HashMap();
        input.put("title",title);
        input.put("description",description);
        input.put("price",price);
        input.put("owner_id",title);
        input.put("category_id",category_id);
        input.put("def_img",def_img);
        ConstantClasss.Constanturl.createService(Idealinterface.class).addProduct(input).enqueue(new Callback<Addproductmodel>() {

            @Override
            public void onResponse(Call<Addproductmodel> call, Response<Addproductmodel> response) {
                if (response.isSuccessful()){


                }
            }

            @Override
            public void onFailure(Call<Addproductmodel> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                // Log.e("ERROrss", "ASMAA");
            }

        });
    }

    public void addfavouritsupplier(int matgerid,int driverid){
        dialog = new ACProgressFlower.Builder(context)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        HashMap input = new HashMap();
        input.put("title",matgerid);
        input.put("description",driverid);
        ConstantClasss.Constanturl.createService(Idealinterface.class).addfavourit(input).enqueue(new Callback<Addfavouritmodel>() {

            @Override
            public void onResponse(Call<Addfavouritmodel> call, Response<Addfavouritmodel> response) {
                if (response.isSuccessful()){
                    if (response.body().getSuccess()==1){
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }else if (response.body().getSuccess()==2){
                        Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_LONG).show();

                    }


                }
            }

            @Override
            public void onFailure(Call<Addfavouritmodel> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                // Log.e("ERROrss", "ASMAA");
            }

        });
    }

}
