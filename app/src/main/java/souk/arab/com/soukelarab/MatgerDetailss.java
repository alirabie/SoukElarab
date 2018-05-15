package souk.arab.com.soukelarab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.core.MainThreadExecutor;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.ClientFavAdapter;
import Adapter.ImagesAdapter;
import Model.Model;
import Model.RequestsModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MatgerDetailss extends AppCompatActivity {

    private View view;
    private RecyclerView produt;
    private ArrayList<RequestsModel> faqModels;
    private String matgerId;
    private ACProgressFlower dialog;
    ImageView ques_image;
    TextView t1, loc, des,txt_signin,countpro;
    private SharedPreferences preferencesid;
    private String user_id;
    private String shope_phone;
    ImageView call,chate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matger_detailss);
        AndroidNetworking.initialize(MatgerDetailss.this);
        preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id", "0000");
        matgerId = "";
        matgerId = getIntent().getExtras().getString("matgerId");
        faqModels = new ArrayList<>();
        ques_image = findViewById(R.id.ques_image);
        chate = findViewById(R.id.chate);
        produt = findViewById(R.id.produt);
        t1 = findViewById(R.id.t1);
        txt_signin = findViewById(R.id.txt_signin);
        loc = findViewById(R.id.loc);
        des = findViewById(R.id.des);
        call = findViewById(R.id.call);
        countpro = findViewById(R.id.countpro);
        getDeils();
        setCall();
        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getDeils() {
        dialog = new ACProgressFlower.Builder(MatgerDetailss.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.viewMatger)
                .addBodyParameter("matjar_id", matgerId)
                .addBodyParameter("user_id", user_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        dialog.dismiss();
                        try {
                            String success = response.getString("success");
                            String Count = response.getString("Count");
                            if (success.equals("1")) {
                                JSONObject matjar_info = response.getJSONObject("matjar_info");
                                String trader_id = matjar_info.getString("trader_id");
                                String shope_name = matjar_info.getString("shope_name");
                                String shope_logo = matjar_info.getString("shope_logo");
                                String address = matjar_info.getString("address");
                                shope_phone = matjar_info.getString("shope_phone");
                                String trader_info = matjar_info.getString("trader_info");
                                loc.setText(address);
                                t1.setText(shope_name);
                                des.setText(trader_info);
                                txt_signin.setText(shope_name);
                                countpro.setText(""+Count);
                                Picasso.with(MatgerDetailss.this)
                                        .load(URLS.imageBase+shope_logo)
                                        .placeholder(R.drawable.placehodleer)
                                        .error(R.drawable.placehodleer)
                                        .into(ques_image);

                 //               Picasso.with(MatgerDetailss.this).load(URLS.imageBase + shope_logo).into(ques_image);

                                JSONArray products = response.getJSONArray("Products");
                                for (int i = 0; i < products.length(); i++) {
                                    RequestsModel latest = new RequestsModel();
                                    JSONObject jsonObject = products.getJSONObject(i);
                                    String product_id = jsonObject.getString("product_id");
                                    String title = jsonObject.getString("title");
                                    String description = jsonObject.getString("description");
                                    String def_img = jsonObject.getString("def_img");
                                    String rate = jsonObject.getString("rate");
                                    String price = jsonObject.getString("price");

                                    //  int fav = jsonObject.getInt("fav");

                                    latest.setProductId(product_id);
                                    latest.setTitle(title);
                                    latest.setDescription(description);
                                    latest.setDefImg(def_img);
                                    latest.setRate(rate);
                                    latest.setPrice(price);
                                    latest.setTrader_id(trader_id);

                                    //   latest.setFav(fav);

                                    faqModels.add(latest);
                                }

                                ClientFavAdapter adapter = new ClientFavAdapter(MatgerDetailss.this, faqModels);
                                produt.setLayoutManager(new GridLayoutManager(MatgerDetailss.this, 2));
                                produt.setAdapter(adapter);
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

    public void setCall() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + shope_phone.trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(MatgerDetailss.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });
        chate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MatgerDetailss.this,Chat.class);
                intent.putExtra("userId",user_id);
                intent.putExtra("matgerId",matgerId);
                startActivity(intent);
            }
        });
    }
}
