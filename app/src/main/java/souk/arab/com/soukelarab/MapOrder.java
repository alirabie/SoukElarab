package souk.arab.com.soukelarab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import URLS.URLS;


public class MapOrder extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {
    private SharedPreferences preferencesid;
    private String lat, lng;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private SupportMapFragment mapFragment;
    private LinearLayout ripple_confirm;
    private LinearLayout ripple_confirmactivity;
    private String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_order);
        ripple_confirmactivity = (LinearLayout) findViewById(R.id.ripple_confirm);
        preferencesid = getSharedPreferences("pref", Context.MODE_PRIVATE);
        lat = preferencesid.getString("lat", "29.341638");
        user_id = preferencesid.getString("user_id","0000");
        lng = preferencesid.getString("lng", "47.656678");
        Log.v("lattt", lat);
        Log.v("lnggag", lng);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

        } else {

        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        setUpClicked();
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.connect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(MapOrder.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapOrder.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
//
//        buildGoogleApiClient();
        //LatLng UCA = new LatLng(-34, 151);

//        mMap.addMarker(new MarkerOptions().position(UCA).title("")).showInfoWindow();
//
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA,17));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(MapOrder.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MapOrder.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapOrder.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            Location userCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (userCurrentLocation != null) {
                MarkerOptions currentUserLocation = new MarkerOptions();
//                LatLng currentUserLatLang = new LatLng(userCurrentLocation.getLatitude(), userCurrentLocation.getLongitude());
                LatLng currentUserLatLang = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                currentUserLocation.position(currentUserLatLang);
                mMap.addMarker(currentUserLocation);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUserLatLang, 16));

            }
        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    public void setDiloge() {

        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.completee_item))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300

                .setExpanded(true, 800)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10, 0, 10, 10)
                .create();

        ripple_confirm = (LinearLayout) dialog.findViewById(R.id.ripple_confirm);
        ripple_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapOrder.this, ClinetHomePage.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public void setUpClicked() {
        ripple_confirmactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOrder();
                setDiloge();

            }
        });
    }
    public void sendOrder() {

        AndroidNetworking.post(URLS.sendeOrder)
                .addBodyParameter("client_id",user_id)
                .addBodyParameter("lat",lat)
                .addBodyParameter("long",lng)

                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}