package souk.arab.com.soukelarab;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

import Fragments.DriverOrderd;
import Fragments.Fragment_DriverSetting;
import Fragments.Fragment_Driverhome;
import Fragments.Settings;

public class DeliverMain extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {
    ImageView btn_setting, btn_home;
    LinearLayout ripple_home, ripple_setting,ripple_logout;
    Fragment selectedFragment = null;
    private LinearLayout driverOrer;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    SharedPreferences.Editor editid;
    private android.content.SharedPreferences preferencesid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_deliver_main);


        preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        btn_home = (ImageView) findViewById(R.id.btn_home);
        btn_setting = (ImageView) findViewById(R.id.btn_setting);

        ripple_setting = (LinearLayout) findViewById(R.id.ripple_setting);
        ripple_home = (LinearLayout) findViewById(R.id.ripple_home);
        driverOrer = (LinearLayout) findViewById(R.id.driverOrer);
        ripple_logout = (LinearLayout) findViewById(R.id.ripple_logout);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment_DriverSetting()).commit();
        ripple_home.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
        ripple_setting.setBackgroundColor(getResources().getColor(R.color.notactove));
        driverOrer.setBackgroundColor(getResources().getColor(R.color.notactove));
        setfragments();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

    }

    public void setfragments(){
        ripple_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment_DriverSetting()).commit();
                ripple_home.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                ripple_setting.setBackgroundColor(getResources().getColor(R.color.notactove));
                driverOrer.setBackgroundColor(getResources().getColor(R.color.notactove));
            }
        });
        ripple_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Settings()).commit();
                ripple_home.setBackgroundColor(getResources().getColor(R.color.notactove));
                ripple_setting.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                driverOrer.setBackgroundColor(getResources().getColor(R.color.notactove));
            }
        });
        driverOrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new DriverOrderd()).commit();
                ripple_home.setBackgroundColor(getResources().getColor(R.color.notactove));
                ripple_setting.setBackgroundColor(getResources().getColor(R.color.notactove));
                driverOrer.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
            }
        });

        ripple_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogExis(DeliverMain.this);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            //  showAlert();

            //setUpGPS();
            //   gpOpen();
            //    Toast.makeText(this, "يرجى فتح بيانات الموقع للحصول على افضل النتائج", Toast.LENGTH_SHORT).show();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();

            editid.putString("lat", String.valueOf(currentLatitude));
            editid.putString("lng", String.valueOf(currentLongitude));
            editid.apply();


            Geocoder gcd = new Geocoder(DeliverMain.this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(currentLatitude, currentLongitude, 1);

            } catch (Exception e) {

            }

//                }
            try {
                if (addresses.size() > 0) {
                    // String toastMsg = String.format("Place: %s", addresses.get(0).getLocality() + " - " + addresses.get(0).getCountryName() + " - " + addresses.get(0).getCountryCode());
                    String addressLine = addresses.get(0).getAddressLine(1);
                    String cityy = addresses.get(0).getAddressLine(2);


                }
            } catch (Exception e) {
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);

            } catch (IntentSender.SendIntentException e) {
                // Log the error

            }
        } else {

            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        editid.putString("lat", String.valueOf(currentLatitude));
        editid.putString("lng", String.valueOf(currentLongitude));
        editid.apply();

        Geocoder gcd = new Geocoder(DeliverMain.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(currentLatitude, currentLongitude, 1);

        } catch (Exception e) {

        }
        try {
            if (addresses.size() > 0) {
                // String toastMsg = String.format("Place: %s", addresses.get(0).getLocality() + " - " + addresses.get(0).getCountryName() + " - " + addresses.get(0).getCountryCode());
                String countryName = addresses.get(0).getCountryName();

                String addressLine = addresses.get(0).getAddressLine(1);
                String cityy = addresses.get(0).getAddressLine(2);
                //     Toast.makeText(this, addressLine, Toast.LENGTH_SHORT).show();


            }
        } catch (Exception e) {

        }
        Log.v("lat", String.valueOf(currentLatitude));
        //    Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {

        showDialogExis(DeliverMain.this);
    }


//    public void showDialogExis(Activity activity) {
//        final Dialog dialog = new Dialog(activity);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setContentView(R.layout.custom_dialogbox_otp);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        TextView text = (TextView) dialog.findViewById(R.id.txt_file_path);
//        TextView mass = (TextView) dialog.findViewById(R.id.masage);
//
//        Button dialogBtn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
//        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//
//        Button dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_okay);
//
//
//
//        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor edit = preferencesid.edit();
//                edit.remove("role");
//                edit.apply();
//                Intent i=new Intent(DeliverMain.this, RegisterActivity.class);
//                startActivity(i);
//            }
//        });
//        dialog.show();
//    }


    public void showDialogExis(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.ticket_pup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        LinearLayout dialogBtn_cancel = (LinearLayout) dialog.findViewById(R.id.btn_cancel);
        dialogBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"Cancel" ,Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        LinearLayout dialogBtn_okay = (LinearLayout) dialog.findViewById(R.id.okLin);


        dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
