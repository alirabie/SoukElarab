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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        setUpVar();
        setUpNavigation();
    }
    public void setUpVar(){
        h1= (LinearLayout) findViewById(R.id.h1);
        h2= (LinearLayout) findViewById(R.id.h2);
        h3= (LinearLayout) findViewById(R.id.h3);
        h4= (LinearLayout) findViewById(R.id.h4);
        h5= (LinearLayout) findViewById(R.id.h5);
        menuu= (ImageView) findViewById(R.id.menu);
        notifcation= (ImageView) findViewById(R.id.notifcation);
        drawer = (DrawerLayout) findViewById(R.id.drawer_lay);
        ripple_addPto = (LinearLayout) findViewById(R.id.ripple_addPto);
        //diloge notification
        fmm = getSupportFragmentManager();
        recycleNotification = new RecycleNotifcation();
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
