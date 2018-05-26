package Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.DriverAdapter;
import Adapter.RequestsAdapter;
import ConstantClasss.Constanturl;

import Model.Allorders;
import Model.Drivers;
import Model.Order;
import Model.RequestsModel;
import Presenter.Idealinterface;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import souk.arab.com.soukelarab.R;

public class ClientsRequestFragment extends Fragment {
RecyclerView recycle_clientRequests;
View view;
    List<Order> faqModels;
    ACProgressFlower dialog;
    public ClientsRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_clients_request, container, false);
        recycle_clientRequests =(RecyclerView)view.findViewById(R.id.recycle_clientRequests);
        getAllOrders(1);
        // Inflate the layout for this fragment
        return view;
    }
    public void getAllOrders(int id) {
        if (dialog == null) {
            dialog =  new ACProgressFlower.Builder(getActivity())
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .fadeColor(Color.DKGRAY).build();
        }
        dialog.show();


        HashMap input=new HashMap();
        input.put("matjar_id", id);
        // Log.e("inpp", input + "");


        Constanturl.createService(Idealinterface.class).GetAllOrders(input).enqueue(new Callback<Allorders>() {
            @Override
            public void onResponse(Call<Allorders> call, Response<Allorders> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                    faqModels = response.body().getOrder();
                    if (faqModels.size()==0){

                    }else {
                        RequestsAdapter adapter = new RequestsAdapter(getActivity(),faqModels);
                        recycle_clientRequests.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recycle_clientRequests.setAdapter(adapter);
                    }


                } else {

                }
            }

            @Override
            public void onFailure(Call<Allorders> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                // Log.e("ERROrss", "ASMAA");
            }

        });
    }

}
