package souk.arab.com.soukelarab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andexert.library.RippleView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.FavouritAdapter;


import Adapter.spin_adaptership;
import Model.Allcategories;
import Model.Categore;
import Model.Drivers;
import Presenter.ConstantMethods;
import Presenter.Constanturl;
import Presenter.Idealinterface;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductToStore extends AppCompatActivity  implements View.OnClickListener{
LinearLayout ripple_addcarpic;
    String path1,encoded;
    File file1;
    private static final int INTENT_REQUEST_GET_photo = 200;
    Bitmap bmImg;
    LinearLayout ripple_addprodct;
    de.hdodenhof.circleimageview.CircleImageView img_carpic;
    EditText input_storename,input_storeprice,input_storedetails;

    ConstantMethods constantMethods;
    RippleView ripple_addprodctstore;
   Spinner spcategories;
Config config;
    List<Categore> all_cats;
    ACProgressFlower dialog;
    String id;
   public static   Categore  all_catss ;
    android.support.design.widget.TextInputLayout input_layout_storename,input_layout_storeprice,input_layout_storedetlis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_store);
        setUpVar();
        setUpclick();
        all_cats = new ArrayList<>();
        constantMethods = new ConstantMethods(AddProductToStore.this);
        getCategories();

        config = new Config();

    }
    public void setUpVar(){
        ripple_addprodct= (LinearLayout) findViewById(R.id.ripple_addprodct);
        img_carpic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.img_carpic);
        ripple_addcarpic = (LinearLayout)findViewById(R.id.ripple_addcarpic);
        input_storename = (EditText)findViewById(R.id.input_storename);
        input_storedetails =(EditText)findViewById(R.id.input_storedetails);
        input_storeprice = (EditText)findViewById(R.id.input_storeprice);
        input_layout_storename =(android.support.design.widget.TextInputLayout)findViewById(R.id.input_layout_storename);
        input_layout_storeprice =(android.support.design.widget.TextInputLayout)findViewById(R.id.input_layout_storeprice);
        input_layout_storedetlis =(android.support.design.widget.TextInputLayout)findViewById(R.id.input_layout_storedetlis);
       // input_storedetails = (EditText)findViewById(R.id.input_storedetails);
        spcategories = (Spinner) findViewById(R.id.spcategories);
        ripple_addcarpic.setOnClickListener(this);
        ripple_addprodct.setOnClickListener(this);


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==INTENT_REQUEST_GET_photo &&resultCode == Activity.RESULT_OK)

        {
//
            // imgselected.setImageURI(imageUri)
            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for (int i = 0; i < image_uris.size(); i++) {
                Uri uri = image_uris.get(i);
                path1 = uri.getPath();
                file1 = new File(path1);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmImg = BitmapFactory.decodeFile(path1);
                bmImg.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                encoded = Base64.encodeToString(b, Base64.DEFAULT);
                img_carpic.setImageBitmap(bmImg);
                // img_addpic.add(encoded);
                Log.e("arraygallery", encoded + "");
                //
               /* bmImg.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                 encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                 Log.e("ssss",encoded);
                img_addpic.setImageBitmap(bmImg)*/
                ;

            }
        }
    }
    public void setUpclick(){
        ripple_addprodct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all_catss = (Categore) spcategories.getSelectedItem();
                 id = String.format(String.valueOf(all_catss.getCatId()));

                    addProductstore(input_storename.getText().toString(),input_storedetails.getText().toString(),input_storeprice.getText().toString(),100,Integer.parseInt(id),encoded);


                //setDiloge();
            }
        });
    }


    private void choosePhoto() {
        ImagePickerActivity.setConfig(config);
        Intent intent = new Intent(AddProductToStore.this, ImagePickerActivity.class);
        startActivityForResult(intent, INTENT_REQUEST_GET_photo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_addcarpic:
                choosePhoto();
                break;
        }
    }
  public void   getCategories(){
      if (dialog == null) {
          dialog =  new ACProgressFlower.Builder(AddProductToStore.this)
                  .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                  .themeColor(Color.WHITE)
                  .fadeColor(Color.DKGRAY).build();
      }
      dialog.show();


      final HashMap input=new HashMap();
      // Log.e("inpp", input + "");
      Constanturl.createService(Idealinterface.class).getCategory(input).enqueue(new Callback<Allcategories>() {
          @Override
          public void onResponse(Call<Allcategories> call, Response<Allcategories> response) {
              if (dialog.isShowing())
                  dialog.dismiss();
              if (response.isSuccessful()) {
                  all_cats = response.body().getCategores();
                  if (all_cats.size()==0){

                  }else {
                      spin_adaptership adapter = new spin_adaptership(AddProductToStore.this,all_cats);
                      spcategories.setAdapter(adapter);
                  }


              } else {

              }
          }

          @Override
          public void onFailure(Call<Allcategories> call, Throwable t) {
              if (dialog.isShowing())
                  dialog.dismiss();
              // Log.e("ERROrss", "ASMAA");
          }

      });
  }
    public void addProductstore(String title,String description,String price,int owner_id,int category_id,String def_img){
        if (input_storename.getText().toString().equals("")){
            input_storename.setError(getResources().getString(R.string.required));
        }else if (input_storeprice.getText().toString().equals("")) {
            input_storeprice.setError(getResources().getString(R.string.required));
        }else {
        /*dialog = new ACProgressFlower.Builder(AddProductToStore.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();*/
            AndroidNetworking.post(URLS.addproductstore)
                    .addBodyParameter("title", title)
                    .addBodyParameter("description", description)
                    .addBodyParameter("price", price)
                    .addBodyParameter("owner_id", String.valueOf(owner_id))
                    .addBodyParameter("category_id", String.valueOf(category_id))
                    .addBodyParameter("def_img", def_img)

                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response
                            // dialog.dismiss();
                            try {
                                String success = response.getString("success");
                                if (success.equals("1")) {

                                    setDiloge();
                                } else if (success.equals("0")) {
                                    setDiloge();
                                }
                            } catch (JSONException e) {

                            }

                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });
        }
    }
    public void setDiloge(){
        LinearLayout ripple_home;
        DialogPlus dialog = DialogPlus.newDialog(AddProductToStore.this)
                .setContentHolder(new ViewHolder(R.layout.addprosucc))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.drawable.lin_shap)
                .setExpanded(true, 850)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();
        LinearLayout ok = (LinearLayout) dialog.findViewById(R.id.ripple_home);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProductToStore.this,StoreHome.class));
            }
        });
        dialog.show();
    }
}
