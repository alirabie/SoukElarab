package Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

import Adapter.CardAdapter;
import Adapter.ClientOrderAdapterrr;
import Model.ClientOrderModel;
import Model.RequestsModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClientOrderList extends Fragment {

ArrayList<ClientOrderModel>faqModels;
    private View view;
    private RecyclerView order_list;
    private SharedPreferences preferencesid;
    private String user_id;
    private ACProgressFlower dialog;


    public ClientOrderList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_client_order_list, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        setUpVar();
        getMyOrder();
        return view;
    }
    public void setUpVar(){
        order_list= view.findViewById(R.id.order_list);
        faqModels=new ArrayList<>();
    }
    public void getMyOrder(){
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        faqModels.clear();
        AndroidNetworking.post(URLS.myOrder)
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
                                JSONArray orders = response.getJSONArray("orders");
                                if (orders.length()==0){
                                    Toast.makeText(getActivity(), "لأ يوجد منتجات حالية", Toast.LENGTH_SHORT).show();
                                }else {
                                    for (int i = 0; i < orders.length(); i++) {
                                    ClientOrderModel latest = new ClientOrderModel();
                                        JSONObject jsonObject = orders.getJSONObject(i);
                                        String order_qty = jsonObject.getString("order_qty");
                                        String product_name = jsonObject.getString("product_name");
                                        String product_image = jsonObject.getString("product_image");
                                        String product_description = jsonObject.getString("product_description");
                                        String product_rate = jsonObject.getString("product_rate");
                                        String product_price = jsonObject.getString("product_price");
                                        String order_status = jsonObject.getString("order_status");

                                        //  int fav = jsonObject.getInt("fav");

                                        latest.setNumber(order_qty);
                                        latest.setOrder_status(order_status);
                                        latest.setName(product_name);
                                        latest.setImage(product_image);
                                        latest.setPrice(product_price);
                                        latest.setDes(product_description);
                                        latest.setRating(product_rate);

                                        //   latest.setFav(fav);
                                        faqModels.add(latest);
                                    }
                                }
                                ClientOrderAdapterrr adapter = new ClientOrderAdapterrr(getActivity(),faqModels);
                                order_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                order_list.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
