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
import Adapter.AllproductAdapter;
import Adapter.DepartmentAdapter;
import Adapter.MoreBuyerAdapter;
import Model.Department;
import Model.MoreBuyerModel;
import Model.RequestsModel;
import souk.arab.com.soukelarab.ClinetHomePage;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllProductRecyclesFragment extends Fragment {
    View view;
    ArrayList<RequestsModel> faqModels;
    ArrayList<Department> departments;
    ArrayList<MoreBuyerModel> moreBuyerModels;
    private RecyclerView proRc;
    private RecyclerView recDept;
    private RecyclerView moreRc;
    private RecyclerView allRecyPro;


    public AllProductRecyclesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_all_product_recycles, container, false);
        setUpSpacificProduct();
        setUpDeprtment();
        setUpMoreBuyer();
        setUpAll();
        return view;
    }

    public void setUpSpacificProduct(){
        proRc =(RecyclerView)view.findViewById(R.id.proRc);
        AllproductAdapter adapter = new AllproductAdapter(getActivity(),faqModels);
        proRc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        proRc.setAdapter(adapter);
    }
    public void setUpDeprtment(){
        recDept =(RecyclerView)view.findViewById(R.id.recDept);
        DepartmentAdapter adapter = new DepartmentAdapter(getActivity(),departments);
        recDept.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recDept.setAdapter(adapter);
    }    public void setUpMoreBuyer(){
        moreRc =(RecyclerView)view.findViewById(R.id.moreRc);
        MoreBuyerAdapter adapter = new MoreBuyerAdapter(getActivity(),moreBuyerModels);
        moreRc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        moreRc.setAdapter(adapter);
    }

    public void setUpAll(){
        allRecyPro =(RecyclerView)view.findViewById(R.id.allRecyPro);
        AllRecyclePro adapter = new AllRecyclePro(getActivity(),faqModels);
        allRecyPro.setLayoutManager(new GridLayoutManager(getActivity(),2));
        allRecyPro.setAdapter(adapter);
    }
}
