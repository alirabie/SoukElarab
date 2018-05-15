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
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.AllTrader;
import Adapter.AllproductAdapter;
import Adapter.BestTrading;
import Model.BestTradingAdapter;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.MatgerDetaise;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraderFragment extends Fragment {


    private View view;
    private RecyclerView besttader;
    private ArrayList<BestTradingAdapter> faqModels;
    private RecyclerView proRc;
    private ArrayList<BestTradingAdapter> tradlist;
    private SharedPreferences preferencesid;
    private String user_id;


    public TraderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=  inflater.inflate(R.layout.fragment_trader, container, false);
    //    getBestTrader();
        AndroidNetworking.initialize(getActivity());
        preferencesid =getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");

        declairRecy();
        getAlltraderigng();
        getBest();
        return view;
    }
    public void getBestTrader(){
//         besttader=(RecyclerView)view.findViewById(R.id.recDept);
//        BestTrading adapter = new BestTrading(getActivity(),faqModels);
//        besttader.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        besttader.setAdapter(adapter);
    }
    public void declairRecy(){
        tradlist=new ArrayList<>();
        faqModels=new ArrayList<>();
        besttader=view.findViewById(R.id.recDept);
        proRc =(RecyclerView)view.findViewById(R.id.proRc);

    }

    // المتاجر
    public void getAlltraderigng(){
        faqModels.clear();
        AndroidNetworking.post(URLS.stores)
                .addBodyParameter("user_id",user_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
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
                                    String address = jsonObject.getString("address");
                                    String product = jsonObject.getString("product");
                                    taderModel.setTraderId(trader_id);
                                    taderModel.setShopeLogo(shope_logo);
                                    taderModel.setShopeName(shope_name);
                                    taderModel.setTraderInfo(trader_info);
                                    taderModel.setAddress(address);
                                    taderModel.setProduct(product);
                                    faqModels.add(taderModel);
                                }
                                AllTrader adapter = new AllTrader(getActivity(),faqModels);
                                proRc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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


    // المتاجر
    public void getBest(){

        AndroidNetworking.post(URLS.stores)

                .addBodyParameter("user_id",user_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
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
                                BestTrading adapter = new BestTrading(getActivity(),tradlist);
                                besttader.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
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

}
