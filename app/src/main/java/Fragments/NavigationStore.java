package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationStore extends Fragment {

    private ActionBarDrawerToggle mDrawerToggel;
    private DrawerLayout mDrawerLayout;
    public NavigationStore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_store, container, false);
    }
    public void setUp(final DrawerLayout drawerLayout) {


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
}
