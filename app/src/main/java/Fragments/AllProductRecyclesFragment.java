package Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.AllproductAdapter;
import Adapter.DepartmentAdapter;
import Adapter.MoreBuyerAdapter;
import Model.Department;
import Model.MoreBuyerModel;
import Model.RequestsModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import souk.arab.com.soukelarab.ClinetHomePage;
import souk.arab.com.soukelarab.LoginClientActivity;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductRecyclesFragment extends Fragment {
    View view;
    ArrayList<RequestsModel> faqModels;
    ArrayList<Department> departments;
    ArrayList<MoreBuyerModel> moreBuyerModels;
    private RecyclerView proRc;
    private RecyclerView recDept;
    private RecyclerView moreRc;
    private RecyclerView allRecyPro;
    private SharedPreferences preferencesid;
    private String user_id;
    private ACProgressFlower dialog;



    public AllProductRecyclesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_all_product_recycles, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        declairRecy();
        getDepartment();
        getlatest();
        moreBuing();
        allPro();
//        setUpSpacificProduct();
//        setUpDeprtment();
//        setUpMoreBuyer();
        //setUpAll();
        return view;
    }

    public void declairRecy(){
        departments=new ArrayList<>();
        faqModels=new ArrayList<>();
        moreBuyerModels=new ArrayList<>();

        recDept =(RecyclerView)view.findViewById(R.id.recDept);
        proRc =(RecyclerView)view.findViewById(R.id.proRc);
        moreRc =(RecyclerView)view.findViewById(R.id.moreRc);
        allRecyPro =(RecyclerView)view.findViewById(R.id.allRecyPro);
    }
    // الاقسام
public void getDepartment(){
    dialog = new ACProgressFlower.Builder(getActivity())
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(getResources().getColor(R.color.colorAccent))
            .fadeColor(getResources().getColor(R.color.colorAccent)).build();
    dialog.show();
    AndroidNetworking.post(URLS.clientMainHome)
            .addBodyParameter("user_id",user_id)
            .setTag("test")
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
                            JSONArray categores = response.getJSONArray("categores");
                            for (int i=0;i<categores.length();i++){
                                Department department=new Department();
                                JSONObject jsonObject = categores.getJSONObject(i);
                                String cat_id = jsonObject.getString("cat_id");
                                String title = jsonObject.getString("title");
                                String image = jsonObject.getString("image");
                                department.setName(title);
                                department.setCatId(cat_id);
                                department.setImage(image);
                                departments.add(department);
                            }
                            DepartmentAdapter adapter = new DepartmentAdapter(getActivity(),departments);
                            recDept.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            recDept.setAdapter(adapter);
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
    // احدث
    public void getlatest(){
        faqModels.clear();
        AndroidNetworking.post(URLS.clientMainHome)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("lastname", "Shekhar")
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
                                JSONArray categores = response.getJSONArray("last_products");
                                for (int i=0;i<categores.length();i++){
                                    RequestsModel latest=new RequestsModel();
                                    JSONObject jsonObject = categores.getJSONObject(i);
                                    String product_id = jsonObject.getString("product_id");
                                    String title = jsonObject.getString("title");
                                    String description = jsonObject.getString("description");
                                    String def_img = jsonObject.getString("def_img");
                                    String rate = jsonObject.getString("rate");
                                    String price = jsonObject.getString("price");
                                    String trader_id = jsonObject.getString("trader_id");
                                    int fav = jsonObject.getInt("fav");

                                    latest.setProductId(product_id);
                                    latest.setTrader_id(trader_id);
                                    latest.setTitle(title);
                                    latest.setDescription(description);
                                    latest.setDefImg(def_img);
                                    latest.setRate(rate);
                                    latest.setPrice(price);
                                    latest.setFav(fav);

                                    faqModels.add(latest);
                                }
                                AllproductAdapter adapter = new AllproductAdapter(getActivity(),faqModels);
                                proRc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
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
    // الاكثر مبيعا
    public void moreBuing(){

        AndroidNetworking.post(URLS.clientMainHome)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("lastname", "Shekhar")
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
                                JSONArray categores = response.getJSONArray("bast_products");
                                for (int i=0;i<categores.length();i++){
                                    MoreBuyerModel latest=new MoreBuyerModel();
                                    JSONObject jsonObject = categores.getJSONObject(i);
                                    String product_id = jsonObject.getString("product_id");
                                    String title = jsonObject.getString("title");
                                    String description = jsonObject.getString("description");
                                    String def_img = jsonObject.getString("def_img");
                                    String rate = jsonObject.getString("rate");
                                    String price = jsonObject.getString("price");
                                    String trader_id = jsonObject.getString("trader_id");
                                    int fav = jsonObject.getInt("fav");

                                    latest.setTraderId(trader_id);
                                    latest.setProductId(product_id);
                                    latest.setTitle(title);
                                    latest.setDescription(description);
                                    latest.setDefImg(def_img);
                                    latest.setRate(rate);
                                    latest.setPrice(price);
                                    latest.setFav(fav);

                                    moreBuyerModels.add(latest);
                                }
                                MoreBuyerAdapter adapter = new MoreBuyerAdapter(getActivity(),moreBuyerModels);
                                moreRc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                                moreRc.setAdapter(adapter);
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
                                    String trader_id = jsonObject.getString("trader_id");

                                    latest.setProductId(product_id);
                                    latest.setTitle(title);
                                    latest.setDescription(description);
                                    latest.setDefImg(def_img);
                                    latest.setRate(rate);
                                    latest.setPrice(price);
                                    latest.setFav(fav);
                                    latest.setTrader_id(trader_id);

                                    faqModels.add(latest);
                                }
                                AllRecyclePro adapter = new AllRecyclePro(getActivity(),faqModels);
                                allRecyPro.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                allRecyPro.setAdapter(adapter);
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
