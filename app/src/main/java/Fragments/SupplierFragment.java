package Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Adapter.FavouritAdapter;
import Model.Driver;
import Model.RequestsModel;
import souk.arab.com.soukelarab.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SupplierFragment extends Fragment {
    View view;
RecyclerView recycle_supplier;
    ArrayList<Driver> faqModels;

    public SupplierFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_supplier, container, false);
        // Inflate the layout for this fragment
        recycle_supplier =(RecyclerView)view.findViewById(R.id.recycle_supplier);
        FavouritAdapter adapter = new FavouritAdapter(getActivity(),faqModels);
        recycle_supplier.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_supplier.setAdapter(adapter);
        return view;
    }

}
