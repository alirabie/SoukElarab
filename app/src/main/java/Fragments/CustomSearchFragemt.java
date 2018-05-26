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
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomSearchFragemt extends Fragment {

    View view;
    private ArrayList<RequestsModel> faqModels;
    private SharedPreferences preferencesid;
    private String user_id;
    private String price="DESC";
    private String rate="DESC";
    private RecyclerView allRecyPro;

    public CustomSearchFragemt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_custom_search_fragemt, container, false);
        setVar();
        Bundle bundle = this.getArguments();
        price = bundle.getString("price");
        rate = bundle.getString("rate");

        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        searchResult();
        return view;
    }
    public void setVar(){
        allRecyPro=view.findViewById(R.id.allRecyPro);
        faqModels=new ArrayList<>();

    }
    // كل المنتجات
    public void searchResult(){
        faqModels.clear();
        AndroidNetworking.post(URLS.search)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("search_by","option")
                .addBodyParameter("price",price)
                .addBodyParameter("rate",rate)
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
                                if (categores.length()==0){
                                    Toast.makeText(getActivity(), "لأ يتوافر هذا المنتج ", Toast.LENGTH_SHORT).show();
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
