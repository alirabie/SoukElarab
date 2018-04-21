package Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_DriverSetting extends Fragment {
    ImageButton btn_setting, btn_home;
View view;
    public Fragment_DriverSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_fragment__driver_setting, container, false);

        // Inflate the layout for this fragment
        return view;
    }

}
