package souk.arab.com.soukelarab;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Adapter.AllRecyclePro;
import Adapter.AllproductAdapter;
import Adapter.DepartmentAdapter;
import Adapter.FavouritAdapter;
import Adapter.MoreBuyerAdapter;
import ConstantClasss.CustomRadio;
import Fragments.AllProductRecyclesFragment;
import Fragments.CardFragment;
import Fragments.CustomSearchFragemt;
import Fragments.Favourit;
import Fragments.NavigationDrawer;
import Fragments.SearchFragemt;
import Fragments.Settings;
import Fragments.TraderFragment;
import Model.Department;
import Model.MoreBuyerModel;
import Model.RequestsModel;

public class ClinetHomePage extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    LinearLayout h1, h2, h3, h4, h5,filter;
    TextView txt_signin;
    EditText editSearch;
    private ImageView menuu;
    private DrawerLayout drawer;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    private SharedPreferences.Editor editid;
    private SharedPreferences preferencesid;

    String price="DESC";
    String rate="DESC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinet_home_page);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        setUpVar();
        setUpNavigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AllProductRecyclesFragment()).commit();
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
        setUpClicked();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.h1:
                txt_signin.setText("الصفحة الرئسية");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new AllProductRecyclesFragment()).commit();
                h1.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h2:
                txt_signin.setText("التجار");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new TraderFragment()).commit();
                h2.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h3:

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CardFragment()).commit();
                txt_signin.setText("السلة");
                h3.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Favourit()).commit();
                txt_signin.setText("المفضلة");
                h4.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                h5.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
            case R.id.h5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new Settings()).commit();
                txt_signin.setText("الإعدادت");
                h5.setBackgroundColor(getResources().getColor(R.color.coloreActiv));
                h2.setBackgroundColor(getResources().getColor(R.color.notactove));
                h3.setBackgroundColor(getResources().getColor(R.color.notactove));
                h4.setBackgroundColor(getResources().getColor(R.color.notactove));
                h1.setBackgroundColor(getResources().getColor(R.color.notactove));
                break;
        }
    }

    public void setUpVar() {
        h1 = (LinearLayout) findViewById(R.id.h1);
        filter = (LinearLayout) findViewById(R.id.filter);
        h2 = (LinearLayout) findViewById(R.id.h2);
        h3 = (LinearLayout) findViewById(R.id.h3);
        h4 = (LinearLayout) findViewById(R.id.h4);
        h5 = (LinearLayout) findViewById(R.id.h5);
        txt_signin = (TextView) findViewById(R.id.txt_signin);
        editSearch = (EditText) findViewById(R.id.editSearch);
        menuu = (ImageView) findViewById(R.id.menu);
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


        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editSearch.getText().toString().equals("")){
                    Bundle bundle = new Bundle();
                    bundle.putString("message", editSearch.getText().toString() );
                    SearchFragemt  fragInfo = new SearchFragemt();
                    fragInfo.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragInfo).commit();

                }
                else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new AllProductRecyclesFragment()).commit();
                }
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


            Geocoder gcd = new Geocoder(ClinetHomePage.this, Locale.getDefault());
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

        Geocoder gcd = new Geocoder(ClinetHomePage.this, Locale.getDefault());
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

    @Override
    public void onBackPressed() {

        showDialogExis(ClinetHomePage.this);
    }


    //filter
    public void setDiloge() {

        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.filter_layput))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300

                .setExpanded(true, 800)
                .setGravity(Gravity.CENTER)
                .setMargin(10, 0, 10, 10)
                .create();

        RelativeLayout searchEngin = (RelativeLayout) dialog.findViewById(R.id.searchEngin);
        CustomRadio pricelaw = (CustomRadio) dialog.findViewById(R.id.pricelaw);
        CustomRadio priceHigh = (CustomRadio) dialog.findViewById(R.id.priceHigh);
        CustomRadio rateHigh = (CustomRadio) dialog.findViewById(R.id.rateHigh);
        CustomRadio ratelaw = (CustomRadio) dialog.findViewById(R.id.ratelaw);

        pricelaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price="ASC";
            //    Toast.makeText(ClinetHomePage.this, price, Toast.LENGTH_SHORT).show();
            }
        });
        priceHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                price="DESC";

          //      Toast.makeText(ClinetHomePage.this, price, Toast.LENGTH_SHORT).show();
            }
        });
        ratelaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate="ASC";
            //    Toast.makeText(ClinetHomePage.this, price, Toast.LENGTH_SHORT).show();
            }
        });
        rateHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rate="DESC";
          //      Toast.makeText(ClinetHomePage.this, price, Toast.LENGTH_SHORT).show();
            }
        });
        searchEngin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("price",price);
                bundle.putString("rate",rate);
                CustomSearchFragemt fragInfo = new CustomSearchFragemt();
                fragInfo.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragInfo).commit();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void setUpClicked() {
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDiloge();

            }
        });
    }
}
