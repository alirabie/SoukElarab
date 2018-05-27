package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

import Fragments.Fragment_Orders;
import Fragments.Fragment_addProduct;

public class StoreActivitrDrawer extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawer;
    private LinearLayout sideMenu;
    Toolbar mToolbar;
    ImageButton ic_menu;
    com.andexert.library.RippleView rel_shops, relprofile, rel_ideal, rel_service;
    android.support.v4.app.Fragment selectedFragment = null;
    android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_activitr_drawer);
        findViewsId();
        mDrawer = (DrawerLayout) findViewById(R.id.sideMenuDrawer);
        sideMenu = (LinearLayout) findViewById(R.id.sideMenu);
       // relprofile =(RippleView)findViewsId(R.id.relprofile) ;
        set_action_bar();
        setSelectedFragment(0);
        relprofile.setOnClickListener(this);
        ic_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.isDrawerOpen(sideMenu))
                    mDrawer.closeDrawer(sideMenu);
                else
                    mDrawer.openDrawer(sideMenu);
            }
        });
    }

    public void replace_fragment(android.support.v4.app.Fragment frag, String frag_tag) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, frag, frag_tag);
        // onRestart();
        ft.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_home:
                /*Intent i = new Intent(NavDrawerActivity.this,addshipmentActivity.class);
                startActivity(i)*/
                ;
                selectedFragment = new Fragment_addProduct();
                selectFragment();
                mDrawer.closeDrawer(sideMenu);
                break;
            case R.id.relprofile:
                startActivity(new Intent(StoreActivitrDrawer.this,AddproductActivity.class));
               /* selectedFragment = new Fragment_Orders();
                selectFragment();*/
                mDrawer.closeDrawer(sideMenu);
                break;
        }
    }

    private void selectFragment() {
        if (selectedFragment != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment).addToBackStack("0").commit();

        }
    }

    private void findViewsId() {
        rel_shops = (com.andexert.library.RippleView) findViewById(R.id.rel_home);
        relprofile = (com.andexert.library.RippleView) findViewById(R.id.relprofile);
        // rel_careers = (com.andexert.library.RippleView) findViewById(R.id.rel_careers);
        rel_ideal = (com.andexert.library.RippleView) findViewById(R.id.rel_ideal);

    }

    public void set_action_bar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //  mToolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c94929")));
        // TextView  sub_title = (TextView) findViewById(R.id.toolbar_title);
        ic_menu = (ImageButton) findViewById(R.id.ic_menu);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setSelectedFragment(int position) {
        switch (position) {
            case 0:
                selectedFragment = new Fragment_addProduct();
                replace_fragment(selectedFragment, "container");
                // sub_title.setText("اخبار الاداره");
                break;
            case 1:
               // selectedFragment = new Fragment_Orders();
               // replace_fragment(selectedFragment, "");
                // sub_title.setText(R.string.AboutAsia);
                break;
          /*  case 2:
                selectedFragment = new Fragment_service();
                replace_fragment(selectedFragment,"");
                // sub_title.setText(R.string.AboutAsia);
                break;
            case 3:
                selectedFragment = new Fragment_blogs();
                replace_fragment(selectedFragment,"");
                // sub_title.setText(R.string.contactus);
                break;
            case 4:
                selectedFragment = new Fragment_contact();
                replace_fragment(selectedFragment,"");
                break;
        }*/
        }
    }
}
