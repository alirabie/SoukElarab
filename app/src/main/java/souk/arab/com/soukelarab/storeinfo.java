package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

import URLS.URLS;

public class storeinfo extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener  {
    LinearLayout ripple_register;
    NiftyDialogBuilder dialogBuilder;
    TextView txt_registerd,txt_confirm;
    EditText input_storename,input_suppliername,input_id,input_phon,input_city,input_address,input_map;
    Intent intent;
    private SharedPreferences.Editor editid;
    String name,phone,password,rosa,skel,car,lat,lng;
    private static final int PLACE_PICKER_REQUESTT3 = 150;
    android.support.design.widget.TextInputLayout input_layout_Map;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);
        findViewid();
        intent = getIntent();
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("password");
        rosa = intent.getStringExtra("rosa");
        skel = intent.getStringExtra("skel");
        car = intent.getStringExtra("car");
        //
        SharedPreferences preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        //
        input_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(storeinfo.this), PLACE_PICKER_REQUESTT3);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {

                }
            }
        });
    }

    private void findViewid() {
        ripple_register =(LinearLayout)findViewById(R.id.ripple_register);
        ripple_register.setOnClickListener(this);
        input_storename = (EditText)findViewById(R.id.input_storenametrader);
        input_suppliername = (EditText)findViewById(R.id.input_suppliername);
        input_id = (EditText)findViewById(R.id.input_id);
        input_phon = (EditText)findViewById(R.id.input_phon);
        input_city = (EditText)findViewById(R.id.input_city);
        input_address = (EditText)findViewById(R.id.input_address);
        input_map = (EditText)findViewById(R.id.input_maptrader);
        input_layout_Map =( android.support.design.widget.TextInputLayout)findViewById(R.id.input_layout_Map);
    }
    //new plce picker
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUESTT3) {
            if (resultCode == RESULT_OK) {


                //      Toast.makeText(getActivity(),R.string.cholocation, Toast.LENGTH_SHORT).show();

                Place place = PlacePicker.getPlace(data, storeinfo.this);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String addressTxt = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
//                stBuilder.append(placename);
//                stBuilder.append("\n");
                stBuilder.append("Latitude: ");

                stBuilder.append("\n");
                stBuilder.append("Logitude: ");

                lat=place.getLatLng().latitude+"";
                lng=place.getLatLng().longitude+"";

                Toast.makeText(storeinfo.this,"تم تحديد الموقع بنجاح", Toast.LENGTH_SHORT).show();
                Geocoder geocoder = new Geocoder(storeinfo.this, Locale.getDefault());
                List<Address> addresses = null;

                input_map.setText(addressTxt);
                Address result;

                if (addresses != null && !addresses.isEmpty()) {
                    //address.setText(addresses.get(0).getCountryName());

                    input_map.setText(addressTxt);
                }



                //  address.setText(addressTxt);

            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_register:
                if(input_storename.getText().toString().equals("")){
                    input_storename.setError(getResources().getString(R.string.required));
                }else if (input_suppliername.getText().toString().equals("")){
                    input_suppliername.setError(getResources().getString(R.string.required));
                }else if (input_id.getText().toString().equals("")){
                    input_id.setError(getResources().getString(R.string.required));
                }else if (input_phon.getText().toString().equals("")){
                    input_phon.setError(getResources().getString(R.string.required));
                }else if (input_map.getText().toString().equals("")){
                    input_map.setError(getResources().getString(R.string.required));
                }else {
                    registerdr();
                }

                break;
//            case R.id.input_maptrader:
//                break;
        }
    }


    public void setDiloge(){
        LinearLayout ripple_home;
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.done))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.drawable.lin_shap)
                .setExpanded(true, 850)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();
        LinearLayout ok = (LinearLayout) dialog.findViewById(R.id.ripple_home);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(storeinfo.this,StoreHome.class));
            }
        });
        dialog.show();
    }
    public void registerdr(){


        AndroidNetworking.post(URLS.register)
                .addBodyParameter("role","trader")
                .addBodyParameter("username",name)
                .addBodyParameter("phone",phone)
                .addBodyParameter("password",password)
                .addBodyParameter("shope_image",rosa)
                .addBodyParameter("shope_logo",car)
                .addBodyParameter("segel_togarey_image",skel)
                .addBodyParameter("shope_name",input_storename.getText().toString())
                .addBodyParameter("trader_name",input_suppliername.getText().toString())
                .addBodyParameter("address",input_map.getText().toString()+"")
                .addBodyParameter("shope_phone",input_phon.getText().toString())
                .addBodyParameter("identity_num",input_id.getText().toString())
                .addBodyParameter("dev_type","android")
                .addBodyParameter("dev_token","android")
                .addBodyParameter("lat",lat)
                .addBodyParameter("lng",lng)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if(success.equals("1")){
                                String user_id = response.getString("user_id");
                                Log.e("userId : ",user_id + response.toString());
                                editid.putString("user_id",user_id+"");
                                editid.apply();
                                startActivity(new Intent(storeinfo.this,codeActivity.class).putExtra("type",2));
                            }
                            else if(success.equals("0")){
                                Toast.makeText(storeinfo.this, response.getString("errors"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {

                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

}
