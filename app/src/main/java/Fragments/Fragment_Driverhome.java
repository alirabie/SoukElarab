package Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import souk.arab.com.soukelarab.AddproductActivity;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.StoreActivitrDrawer;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Driverhome extends Fragment {

View view;
TextView store;
    public Fragment_Driverhome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_fragment__driver_home, container, false);

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddproductActivity.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
