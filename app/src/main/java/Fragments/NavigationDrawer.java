package Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import souk.arab.com.soukelarab.LoginActivity;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.RegisterActivity;


public class NavigationDrawer extends Fragment {
    private ActionBarDrawerToggle mDrawerToggel;
    private DrawerLayout mDrawerLayout;
    LinearLayout  trader, massages ,department,logout,myOrder;
    private View view;
    private SharedPreferences preferencesid;
    private TextView tv;


    public NavigationDrawer() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

        declare();
        replaceFragment();
        return  view;
    }

    public void setUp(final DrawerLayout drawerLayout) {
        tv = getActivity().findViewById(R.id.txt_signin);
        mDrawerLayout = drawerLayout;
        mDrawerToggel = new ActionBarDrawerToggle(getActivity(), drawerLayout, null, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggel);
    }
    public void declare(){
         trader = view.findViewById(R.id.trader);
        massages = view.findViewById(R.id.massages);
        department = view.findViewById(R.id.department);
        logout = view.findViewById(R.id.logout);
        myOrder = view.findViewById(R.id.myOrder);
    }
    public void replaceFragment(){
        trader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new TraderFragment()).commit();
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        });
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new AllProductRecyclesFragment()).commit();
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        });
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new ClientOrderList()).commit();
                mDrawerLayout.closeDrawer(Gravity.START);
                tv.setText("طلباتى");
            }
        });
        massages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new  AllChateFragemt()).commit();
                mDrawerLayout.closeDrawer(Gravity.START);
                tv.setText("الرسائل");
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = preferencesid.edit();
                edit.remove("role");
                edit.apply();
                Intent i=new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
