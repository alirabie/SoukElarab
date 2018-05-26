package souk.arab.com.soukelarab;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.ordersdetailsAdapter;
import Model.Orderdetails;
import Model.orders_detailslist;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class orderdetails extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recycle_requestsdetails;
    List<orders_detailslist> faqModels;
    ACProgressFlower dialog;
    TextView txttotal;
    Intent intent;
    int orderid;
    String Products_name, Products_price, Products_qty;
    Double Products_total, lat, lng;
    MapView mMapView;
    int LOCATION_PERMISSION_ID = 100;
    GoogleMap googleMap;
    LatLng lat_long;
    String date,time,name,img;
    RelativeLayout imgdetail;
    TextView txtClientName,txtClientday,txtCliendate;
    de.hdodenhof.circleimageview.CircleImageView imguser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderdetails);
        imguser = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.img_carpic);
        txtCliendate = (TextView)findViewById(R.id.txtCliendate);
        txtClientday = (TextView)findViewById(R.id.txtClientday);
        txtClientName = (TextView)findViewById(R.id.txtClientName);
        intent = getIntent();
        faqModels = new ArrayList<>();
//        imgdetail.setOnClickListener(this);
        orderid = intent.getIntExtra("orderid", 0);
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        name = intent.getStringExtra("name");
        img = intent.getStringExtra("img");
setData();
        if (Build.VERSION.SDK_INT >= 25) {
            if (ActivityCompat.checkSelfPermission(orderdetails.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(orderdetails.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(orderdetails.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(orderdetails.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(orderdetails.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, LOCATION_PERMISSION_ID);
                // return;
            }
        } else {
            mMapView = (MapView) findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume(); // needed to get the map to display immediately
            try {
                MapsInitializer.initialize(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;

                    // For showing a move to my location button
                    if (ActivityCompat.checkSelfPermission(orderdetails.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(orderdetails.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
//                        googleMap.setMyLocationEnabled(true);
                        // getContactData(lang);
                        return;
                    } else {
                        // getContactData(lang);
                    }

                }


            });
        }
    }

    private void setData() {
        txtClientName.setText(name);
        txtClientday.setText(date);
        txtCliendate.setText(time);
       /* if (img.equals("")||img.equals(null)){
            Picasso.with(orderdetails.this)
                    .load(URLS.imageBase+img)
                    .placeholder(R.drawable.image1)
                    .error(R.drawable.image1)
                    .into(imguser);
        }
        else {
            Picasso.with(orderdetails.this).load(URLS.imageBase+img).into(imguser);
        }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgdetail:
                Intent intent  = new Intent(orderdetails.this,Requestdetails.class);
                intent.putExtra("orderid",orderid);
                startActivity(intent);
                break;
        }
    }

}



