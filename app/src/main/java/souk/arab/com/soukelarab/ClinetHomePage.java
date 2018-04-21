package souk.arab.com.soukelarab;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Adapter.AllRecyclePro;
import Adapter.AllproductAdapter;
import Adapter.DepartmentAdapter;
import Adapter.FavouritAdapter;
import Adapter.MoreBuyerAdapter;
import Fragments.AllProductRecyclesFragment;
import Fragments.CardFragment;
import Fragments.Favourit;
import Fragments.NavigationDrawer;
import Fragments.TraderFragment;
import Model.Department;
import Model.MoreBuyerModel;
import Model.RequestsModel;

public class ClinetHomePage extends AppCompatActivity implements View.OnClickListener {

LinearLayout h1,h2,h3,h4,h5;
TextView txt_signin;
    private ImageView menuu;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinet_home_page);
        setUpVar();
        setUpNavigation();
getSupportFragmentManager().beginTransaction().replace(R.id.container,new AllProductRecyclesFragment()).commit();
h1.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
h2.setBackgroundColor(getResources().getColor(R.color.notactove));
h3.setBackgroundColor(getResources().getColor(R.color.notactove));
h4.setBackgroundColor(getResources().getColor(R.color.notactove));
h5.setBackgroundColor(getResources().getColor(R.color.notactove));

        h1.setOnClickListener(this);
        h2.setOnClickListener(this);
        h3.setOnClickListener(this);
        h4.setOnClickListener(this);
        h5.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.h1:
                txt_signin.setText("الصفحة الرئسية");
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new AllProductRecyclesFragment()).commit();
                h1.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h2:
                txt_signin.setText("التجار");
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new TraderFragment()).commit();
                h2.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h3:

                getSupportFragmentManager().beginTransaction().replace(R.id.container,new CardFragment()).commit();
                txt_signin.setText("السلة");
                h3.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new Favourit()).commit();
                txt_signin.setText("المفضلة");
                h4.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h5:
                txt_signin.setText("الإعدادت");
                h5.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
        }
    }
    public void setUpVar(){
        h1= (LinearLayout) findViewById(R.id.h1);
        h2= (LinearLayout) findViewById(R.id.h2);
        h3= (LinearLayout) findViewById(R.id.h3);
        h4= (LinearLayout) findViewById(R.id.h4);
        h5= (LinearLayout) findViewById(R.id.h5);
        txt_signin= (TextView) findViewById(R.id.txt_signin);
        menuu= (ImageView) findViewById(R.id.menu);
        drawer = (DrawerLayout) findViewById(R.id.drawer_lay);
    }

    public void setUpNavigation() {

        menuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(GravityCompat.START);
            }
        });
        NavigationDrawer drawerFragment = (NavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.nv_fragment);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_lay));
    }
}
