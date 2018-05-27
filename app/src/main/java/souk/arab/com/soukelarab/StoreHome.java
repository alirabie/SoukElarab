package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import Fragments.ClientOrderList;
import Fragments.Fragment_Orders;
import Fragments.NavigationDrawer;
import Fragments.NavigationStore;

public class StoreHome extends AppCompatActivity {
    private ImageView menuu;
    LinearLayout h1,h2,h3,h4,h5;
    private DrawerLayout drawer;
    LinearLayout ripple_addPto;
    private RecycleNotifcation recycleNotification;
    private FragmentManager fmm;
    ImageView notifcation;
    com.andexert.library.RippleView addProduct, customersRequestes, deleviryClint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        setUpVar();
        setUpNavigation();
    }
    public void setUpVar(){


        addProduct= ( com.andexert.library.RippleView) findViewById(R.id.rel_home);
        customersRequestes= ( com.andexert.library.RippleView) findViewById(R.id.relprofile);
        deleviryClint= ( com.andexert.library.RippleView) findViewById(R.id.rel_ideal);



        h1= (LinearLayout) findViewById(R.id.h1);
        h2= (LinearLayout) findViewById(R.id.h2);
        h3= (LinearLayout) findViewById(R.id.h3);
        h4= (LinearLayout) findViewById(R.id.h4);
        h5= (LinearLayout) findViewById(R.id.h5);





        menuu= (ImageView) findViewById(R.id.menu);
        notifcation= (ImageView) findViewById(R.id.notifcation);
        drawer = (DrawerLayout) findViewById(R.id.drawer_lay);
        ripple_addPto = (LinearLayout) findViewById(R.id.ripple_addPto);





    }



    public void setUpNavigation() {

        menuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationStore drawerFragment = (NavigationStore)
                getSupportFragmentManager().findFragmentById(R.id.nv_fragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_lay));

        ripple_addPto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreHome.this,AddProductToStore.class));
            }
        });


        //Add product
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(StoreHome.this,AddProductToStore.class));
               drawer.closeDrawers();
            }
        });

        //Customers requests
        customersRequestes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager3 = (StoreHome.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.container, new ClientOrderList());
                fragmentTransaction3.commit();
                drawer.closeDrawers();

            }
        });

        //Delivery Agent
        deleviryClint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentManager fragmentManager3 = (StoreHome.this).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.container, new Fragment_Orders());
                fragmentTransaction3.commit();
                drawer.closeDrawers();

            }
        });















        notifcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleNotification.show(fmm, "Dilog");

//                Intent i=new Intent(HomeActivity.this,TeamDetaisls.class);
//                startActivity(i);
            }
        });
    }
}
