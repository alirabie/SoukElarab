package Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.orhanobut.dialogplus.DialogPlus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.ClientOrderAdapterrr;
import Adapter.DriverOrderAdapter;
import ConstantClasss.Custom_Textview;
import Model.ClientOrderModel;
import Model.OrderModelDriver;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import souk.arab.com.soukelarab.ClinetHomePage;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.addSupplierPicDelivery;
import souk.arab.com.soukelarab.codeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverOrderd extends Fragment {


    private View view;
    private SharedPreferences preferencesid;
    private String user_id;
    private RecyclerView order_list;
    private ArrayList<OrderModelDriver> faqModels;
    private String lat;
    private String lng;
    private String mager_lat,mager_lng;
    private String or_lat,or_lng,orderId;


    public DriverOrderd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_driver_orderd, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");

        lat = preferencesid.getString("lat", "29.341638");
        lng = preferencesid.getString("lng", "47.656678");
        setUpVar();
        getMyOrder();
        return  view;
    }
    public void setUpVar(){
        order_list= view.findViewById(R.id.order_list);
        faqModels=new ArrayList<>();
    }
    public void getMyOrder(){
        faqModels.clear();
        AndroidNetworking.post(URLS.driverOrder)
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

                                JSONArray orders = response.getJSONArray("orders");
                                if (orders.length()==0){
                                    Toast.makeText(getActivity().getApplicationContext(), "ليس لديك طلبات حالية", Toast.LENGTH_SHORT).show();
                                }else {
                                    for (int i = 0; i < orders.length(); i++) {
                                        OrderModelDriver latest = new OrderModelDriver();
                                        JSONObject jsonObject = orders.getJSONObject(i);
                                        String order_lat = jsonObject.getString("order_lat");
                                        String order_id = jsonObject.getString("order_id");
                                        String order_long = jsonObject.getString("order_long");
                                        String product_name = jsonObject.getString("product_name");
                                        String product_qty = jsonObject.getString("product_qty");
                                        String product_price = jsonObject.getString("product_price");
                                        String matjar_name = jsonObject.getString("matjar_name");
                                        String matjar_lat = jsonObject.getString("matjar_lat");
                                        String matjar_lng = jsonObject.getString("matjar_lng");
                                        String order_check = jsonObject.getString("order_check");


                                        latest.setOrder_lat(order_lat);
                                        latest.setOrder_id(order_id);
                                        latest.setOrder_lng(order_long);
                                        latest.setProduct_name(product_name);
                                        latest.setProduct_qty(product_qty);
                                        latest.setProduct_price(product_price);
                                        latest.setMatgerName(matjar_name);
                                        latest.setMager_lat(matjar_lat);
                                        latest.setMatger_lng(matjar_lng);
                                        latest.setOrder_check(order_check);
                                        //   latest.setFav(fav);
                                        faqModels.add(latest);
                                    }
                                }
                                DriverOrderAdapter adapter = new DriverOrderAdapter(getActivity(),faqModels);
                                order_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                                order_list.setAdapter(adapter);
                                adapter.setOnClickListener(new DriverOrderAdapter.OnItemClickListener() {
                                    @Override
                                    public void onclick(int position) {
                                         mager_lat = faqModels.get(position).getMager_lat();
                                         mager_lng = faqModels.get(position).getMatger_lng();
                                         or_lat = faqModels.get(position).getOrder_lat();
                                        or_lng = faqModels.get(position).getOrder_lng();
                                        orderId = faqModels.get(position).getOrder_id();
                                         setDiloge();
                                    }
                                });
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


    public void setDiloge(){
final LinearLayout ripple_add,ripple_client,cancel,ordercome,yes,no;
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.endorder))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.drawable.lin_shap)
                .setExpanded(true, 800)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();

        ripple_add =(LinearLayout)dialog.findViewById(R.id.ripple_add);
        ripple_client =(LinearLayout)dialog.findViewById(R.id.ripple_client);
        cancel =(LinearLayout)dialog.findViewById(R.id.cancel);
        ordercome =(LinearLayout)dialog.findViewById(R.id.ordercome);
        no =(LinearLayout)dialog.findViewById(R.id.no);
        yes =(LinearLayout)dialog.findViewById(R.id.yes);
        ripple_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
//                startActivity(intent);

                String desLocation = "&daddr=" + Double.toString(Double.valueOf(mager_lat)) + ","
                        + Double.toString(Double.valueOf(mager_lng));
                String currLocation = "saddr=" + Double.toString(Double.valueOf(lat)) + ","
                        + Double.toString(Double.valueOf(lng));
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?" + currLocation
                                + desLocation + "&dirflg=d"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                intent.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");
                startActivity(intent);

            }
        });
        ripple_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desLocation = "&daddr=" + Double.toString(Double.valueOf(or_lat)) + ","
                        + Double.toString(Double.valueOf(or_lng));
                String currLocation = "saddr=" + Double.toString(Double.valueOf(lat)) + ","
                        + Double.toString(Double.valueOf(lng));
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?" + currLocation
                                + desLocation + "&dirflg=d"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                intent.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ordercome.setVisibility(View.VISIBLE);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "لم يتم توصيل الطلب حتى الأن", Toast.LENGTH_SHORT).show();

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endeOrder();
            }
        });
        dialog.show();
    }

    //end order


    public void endeOrder(){

        AndroidNetworking.post("http://market.wildso.com/public/api/driver/end_order")
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("order_id",orderId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        try {
                            String success = response.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(getActivity(), "شكرا لك على انهاء الطلب", Toast.LENGTH_SHORT).show();
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
