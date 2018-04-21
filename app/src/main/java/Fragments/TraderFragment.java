package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.AllTrader;
import Adapter.AllproductAdapter;
import Adapter.BestTrading;
import Model.RequestsModel;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraderFragment extends Fragment {


    private View view;
    private RecyclerView besttader;
    private ArrayList<RequestsModel> faqModels;
    private RecyclerView proRc;


    public TraderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_trader, container, false);
        getBestTrader();
        setUpAll();
        return view;
    }
    public void getBestTrader(){
         besttader=(RecyclerView)view.findViewById(R.id.recDept);
        BestTrading adapter = new BestTrading(getActivity(),faqModels);
        besttader.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        besttader.setAdapter(adapter);
    }

    public void setUpAll(){
        proRc =(RecyclerView)view.findViewById(R.id.proRc);
        AllTrader adapter = new AllTrader(getActivity(),faqModels);
        proRc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        proRc.setAdapter(adapter);
    }
}
