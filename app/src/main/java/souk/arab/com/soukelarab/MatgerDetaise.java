package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.AllTrader;
import Adapter.BestTrading;
import Adapter.DepartmentAdapter;
import ConstantClasss.Custom_Textview;
import Model.BestTradingAdapter;
import Model.Department;
import Model.RequestsModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MatgerDetaise extends AppCompatActivity {
    private RecyclerView besttader;
    private ArrayList<BestTradingAdapter> tradlist;
    private RecyclerView proRc;
    private String cat_id;
    private SharedPreferences preferencesid;
    private String user_id,name;
    private ArrayList<RequestsModel> faqModels;
LinearLayout back;
    private ACProgressFlower dialog;
Custom_Textview txt_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matger_detaise);
        cat_id="";
        name="";
         cat_id = getIntent().getExtras().getString("cat_id");
        name = getIntent().getExtras().getString("name");
        AndroidNetworking.initialize(MatgerDetaise.this);
        preferencesid =this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        declairRecy();

        gettraderigng();
        allPro();

    }
    public void declairRecy(){
        tradlist=new ArrayList<>();
        faqModels=new ArrayList<>();
        besttader=(RecyclerView)findViewById(R.id.recDept);
        txt_signin=(Custom_Textview) findViewById(R.id.txt_signin);
        proRc =(RecyclerView)findViewById(R.id.proRc);
        back =(LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txt_signin.setText(name);
    }
    // المتاجر
    public void gettraderigng(){
        dialog = new ACProgressFlower.Builder(MatgerDetaise.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.cat_product)
                .addBodyParameter("category_id",cat_id)
                .addBodyParameter("user_id",user_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        dialog.dismiss();
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                JSONArray categores = response.getJSONArray("trader");
                                for (int i=0;i<categores.length();i++){
                                    BestTradingAdapter taderModel=new BestTradingAdapter();
                                    JSONObject jsonObject = categores.getJSONObject(i);
                                    String trader_id = jsonObject.getString("trader_id");
                                    String shope_logo = jsonObject.getString("shope_logo");
                                    String shope_name = jsonObject.getString("shope_name");
                                    String trader_info = jsonObject.getString("trader_info");
                                    taderModel.setTraderId(trader_id);
                                    taderModel.setShopeLogo(shope_logo);
                                    taderModel.setShopeName(shope_name);
                                    taderModel.setTraderInfo(trader_info);
                                    tradlist.add(taderModel);
                                }
                                BestTrading adapter = new BestTrading(MatgerDetaise.this,tradlist);
                                besttader.setLayoutManager(new LinearLayoutManager(MatgerDetaise.this, LinearLayoutManager.HORIZONTAL, false));
                                besttader.setAdapter(adapter);
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
    // كل المنتجات
    public void allPro(){
        faqModels.clear();
        AndroidNetworking.post(URLS.clientMainHome)
                .addBodyParameter("user_id",user_id)
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                JSONArray categores = response.getJSONArray("products");
                                for (int i=0;i<categores.length();i++){
                                    RequestsModel latest=new RequestsModel();
                                    JSONObject jsonObject = categores.getJSONObject(i);
                                    String product_id = jsonObject.getString("product_id");
                                    String title = jsonObject.getString("title");
                                    String description = jsonObject.getString("description");
                                    String def_img = jsonObject.getString("def_img");
                                    String rate = jsonObject.getString("rate");
                                    String price = jsonObject.getString("price");
                                    int fav = jsonObject.getInt("fav");

                                    latest.setProductId(product_id);
                                    latest.setTitle(title);
                                    latest.setDescription(description);
                                    latest.setDefImg(def_img);
                                    latest.setRate(rate);
                                    latest.setPrice(price);
                                    latest.setFav(fav);

                                    faqModels.add(latest);
                                }
                                AllRecyclePro adapter = new AllRecyclePro(MatgerDetaise.this,faqModels);
                                proRc.setLayoutManager(new GridLayoutManager(MatgerDetaise.this,2));
                                proRc.setAdapter(adapter);
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
