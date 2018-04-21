package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import souk.arab.com.soukelarab.AddproductActivity;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_homedriver extends Fragment implements View.OnClickListener {

View view;
Button btn_add;
    public Fragment_homedriver() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_fragment_homedriver, container, false);
        btn_add =(Button)view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                startActivity(new Intent(getActivity(),AddproductActivity.class));
                break;
        }
    }
}
