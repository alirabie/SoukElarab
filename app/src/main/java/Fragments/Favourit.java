package Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.ClientFavAdapter;
import Model.RequestsModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.codeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favourit extends Fragment {


    private View view;
    private RecyclerView fav;
    private ArrayList<RequestsModel> faqModels;
    private String user_id;
    private SharedPreferences preferencesid;
    private ACProgressFlower dialog;

    public Favourit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_favourit, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        faqModels=new ArrayList<>();
        setUpAll();
        allPro();
        return view;
    }
    public void setUpAll(){
       fav =(RecyclerView)view.findViewById(R.id.fav);
//        ClientFavAdapter adapter = new ClientFavAdapter(getActivity(),faqModels);
//        fav.setLayoutManager(new GridLayoutManager(getActivity(),2));
//        fav.setAdapter(adapter);
    }


    // كل المنتجات
    public void allPro(){
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        faqModels.clear();
        AndroidNetworking.post(URLS.getFav)
                .addBodyParameter("user_id",user_id)

                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.dismiss();
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                JSONArray categores = response.getJSONArray("products");
                                if (categores.length()==0){
                                    Toast.makeText(getActivity(), "لأ يوجد منتجات فى المفضلة", Toast.LENGTH_SHORT).show();
                                }else {
                                    for (int i = 0; i < categores.length(); i++) {
                                        RequestsModel latest = new RequestsModel();
                                        JSONObject jsonObject = categores.getJSONObject(i);
                                        String product_id = jsonObject.getString("product_id");
                                        String title = jsonObject.getString("title");
                                        String description = jsonObject.getString("description");
                                        String def_img = jsonObject.getString("def_img");
                                        String rate = jsonObject.getString("rate");
                                        String price = jsonObject.getString("price");
                                        String owner_id = jsonObject.getString("trader_id");
                                      //  int fav = jsonObject.getInt("fav");

                                        latest.setProductId(product_id);
                                        latest.setTitle(title);
                                        latest.setDescription(description);
                                        latest.setDefImg(def_img);
                                        latest.setRate(rate);
                                        latest.setPrice(price);
                                        latest.setTrader_id(owner_id);
                                        //   latest.setFav(fav);

                                        faqModels.add(latest);
                                    }
                                }
                                ClientFavAdapter adapter = new ClientFavAdapter(getActivity(),faqModels);
                                fav.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                fav.setAdapter(adapter);
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
