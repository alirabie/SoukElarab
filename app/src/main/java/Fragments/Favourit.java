package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Model.RequestsModel;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favourit extends Fragment {


    private View view;
    private RecyclerView fav;
    private ArrayList<RequestsModel> faqModels;

    public Favourit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_favourit, container, false);
        faqModels=new ArrayList<>();
        setUpAll();
        return view;
    }
    public void setUpAll(){
        fav =(RecyclerView)view.findViewById(R.id.fav);
        AllRecyclePro adapter = new AllRecyclePro(getActivity(),faqModels);
        fav.setLayoutManager(new GridLayoutManager(getActivity(),2));
        fav.setAdapter(adapter);
    }
}
