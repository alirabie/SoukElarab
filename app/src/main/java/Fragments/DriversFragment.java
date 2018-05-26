package Fragments;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.DriverAdapter;
import Adapter.FavouritAdapter;
import ConstantClasss.Constanturl;
import Model.Driver;
import Model.Drivers;
import Model.RequestsModel;
import Presenter.Idealinterface;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import souk.arab.com.soukelarab.R;


public class DriversFragment extends Fragment {
RecyclerView recycle_clientRequests;
    ACProgressFlower dialog;
    List<Driver> driverslist;
View view;
    ArrayList<RequestsModel> faqModels;
    public DriversFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_favourit, container, false);
        recycle_clientRequests =(RecyclerView)view.findViewById(R.id.fav);
        driverslist= new ArrayList<>();
        getDrivers(100);
        // Inflate the layout for this fragment
        return view;
    }
    public void getDrivers(int id) {
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
        Constanturl.createService(Idealinterface.class).GetDrivers(input).enqueue(new Callback<Drivers>() {
            @Override
            public void onResponse(Call<Drivers> call, Response<Drivers> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                    driverslist = response.body().getDriver();
                    if (driverslist.size()==0){

                    }else {
                        DriverAdapter adapter = new DriverAdapter(getActivity(),driverslist);
                        recycle_clientRequests.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recycle_clientRequests.setAdapter(adapter);
                    }


                } else {

                }
        }

            @Override
            public void onFailure(Call<Drivers> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                // Log.e("ERROrss", "ASMAA");
            }

        });
    }
}
