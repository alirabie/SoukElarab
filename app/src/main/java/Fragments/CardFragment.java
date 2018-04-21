package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import Adapter.AllTrader;
import Adapter.CardAdapter;
import Model.RequestsModel;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {
LinearLayout ripple_confirm;

    private View view;
    private ArrayList<RequestsModel> faqModels;
    private RecyclerView cart;


    public CardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_card, container, false);
        setUpAll();
        return view;
    }

    public void setUpAll(){
        cart =(RecyclerView)view.findViewById(R.id.cart);
        ripple_confirm =(LinearLayout) view.findViewById(R.id.ripple_confirm);
        CardAdapter adapter = new CardAdapter(getActivity(),faqModels);
        cart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cart.setAdapter(adapter);
    }

}
