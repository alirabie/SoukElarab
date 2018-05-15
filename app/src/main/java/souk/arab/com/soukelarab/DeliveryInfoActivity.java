package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Adapter.CarAdapter;
import Adapter.CityAdapterArray;
import Model.LookupModel;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class DeliveryInfoActivity extends AppCompatActivity implements View.OnClickListener ,GoogleApiClient.OnConnectionFailedListener  {
    LinearLayout ripple_register, ripple_home;
    NiftyDialogBuilder dialogBuilder;
    TextView txt_signin, txt_confirm;


    EditText input_Fname,input_secname,input_work,input_color,input_num,input_address;
    private EditText input_car;
String name,phone,password,rosa,skel,car;
    private SharedPreferences.Editor editid;
    private static final int PLACE_PICKER_REQUESTT3 = 150;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private ACProgressFlower dialog;
    ArrayList<LookupModel> lookupModels;
    private String id;
    private String car_id;

    RelativeLayout type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_info);
        id="0";
        car_id="0";
            /*
       new place picker
    */

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        SharedPreferences preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
         name = getIntent().getExtras().getString("name");
         phone = getIntent().getExtras().getString("phone");
         password = getIntent().getExtras().getString("password");
         rosa = getIntent().getExtras().getString("rosa");
         skel = getIntent().getExtras().getString("skel");
         car = getIntent().getExtras().getString("car");
lookupModels=new ArrayList<>();

        ripple_register = (LinearLayout) findViewById(R.id.ripple_register);
        txt_signin = (TextView) findViewById(R.id.txt_signin);


        input_Fname = (EditText) findViewById(R.id.input_Fname);
        type = (RelativeLayout) findViewById(R.id.type);
        input_secname = (EditText) findViewById(R.id.input_secname);
        input_work = (EditText) findViewById(R.id.input_work);
        input_color = (EditText) findViewById(R.id.input_color);
        input_num = (EditText) findViewById(R.id.input_num);
        input_car = (EditText) findViewById(R.id.input_car);
        input_address = (EditText) findViewById(R.id.input_address);
        // Spinner element
      //   sp_carType = (Spinner) findViewById(R.id.sp_carType);
        ripple_register.setOnClickListener(this);

        input_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(DeliveryInfoActivity.this), PLACE_PICKER_REQUESTT3);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {

                }
            }
        });
        setClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ripple_register:
                if (validation()){

                    registerdr();
                }

                break;
        }
    }

    public void registerdr(){

        dialog = new ACProgressFlower.Builder(DeliveryInfoActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.register)
                .addBodyParameter("role","driver")
                .addBodyParameter("username",name)
                .addBodyParameter("phone",phone)
                .addBodyParameter("password",password)
                .addBodyParameter("dev_type","android")
                .addBodyParameter("dev_token","android")
                .addBodyParameter("driving_license_image",rosa)
                .addBodyParameter("form_image",skel)
                .addBodyParameter("car_image",car)
                .addBodyParameter("fname",input_Fname.getText().toString())
                .addBodyParameter("lname",input_secname.getText().toString())
                .addBodyParameter("employment_type",input_work.getText().toString())
                .addBodyParameter("car_type",car_id)
                .addBodyParameter("car_color",input_color.getText().toString())
                .addBodyParameter("car_number",input_num.getText().toString())
                .addBodyParameter("address",input_address.getText().toString())


                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            dialog.dismiss();
                            String success = response.getString("success");
                            if(success.equals("1")){
                                String user_id = response.getString("user_id");
                                editid.putString("user_id",user_id);
                                editid.apply();
                                Intent intent=new Intent(DeliveryInfoActivity.this,codeDelivertActivity.class);
                                startActivity(intent);

                            }
                            else if(success.equals("0")){

                                Toast.makeText(DeliveryInfoActivity.this, "من فضلك اختار نوع السيارة", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.show();
                        // handle error
                    }
                });
    }

    //
    private boolean validation() {


        boolean check = true;
        if (input_Fname.getText().toString().equals("")&&input_secname.getText().toString().equals("")&&input_car.getText().toString().equals("")
                &&input_work.getText().toString().equals("")  &&input_color.getText().toString().equals("")  &&input_num.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();

            check =false;
        }
        else if(input_Fname.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_secname.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_work.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }  else if(input_car.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_color.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }  else if(input_num.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }else if(input_address.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        return check;




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

                Place place = PlacePicker.getPlace(data, DeliveryInfoActivity.this);
                StringBuilder stBuilder = new StringBuilder();
                String placename = String.format("%s", place.getName());
                String addressTxt = String.format("%s", place.getAddress());
                stBuilder.append("Name: ");
//                stBuilder.append(placename);
//                stBuilder.append("\n");
                stBuilder.append("Latitude: ");

                stBuilder.append("\n");
                stBuilder.append("Logitude: ");


                Toast.makeText(DeliveryInfoActivity.this,"تم تحديد الموقع بنجاح", Toast.LENGTH_SHORT).show();
                Geocoder geocoder = new Geocoder(DeliveryInfoActivity.this, Locale.getDefault());
                List<Address> addresses = null;

                input_address.setText(addressTxt);
                Address result;

                if (addresses != null && !addresses.isEmpty()) {
                    //address.setText(addresses.get(0).getCountryName());

                    input_address.setText(addressTxt);
                }



                //  address.setText(addressTxt);

            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


      public void getCarType(){
          AndroidNetworking.post("http://market.wildso.com/public/api/CarTypes")
                  .setPriority(Priority.HIGH)
                  .build()
                  .getAsJSONObject(new JSONObjectRequestListener() {
                      @Override
                      public void onResponse(JSONObject response) {
                          // do anything with response

                          try {
                              String success = response.getString("success");
                              if(success.equals("0")){

                                  JSONArray cars =response.getJSONArray("cars");
                                  for (int i=0;i<cars.length();i++) {
                                      LookupModel lookupModel=new LookupModel("0","نوع السيارة");

                                      JSONObject jsonObject = cars.getJSONObject(i);
                                      String car_id = jsonObject.getString("car_id");
                                      String name = jsonObject.getString("name");

                                      lookupModel.setCar_id(car_id);
                                      lookupModel.setName(name);
                                      lookupModels.add(lookupModel);
                                      carTupe();
                                  }




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




    public void carTupe() {

//        AreasModel selectedItem = (AreasModel) sp_distric.getSelectedItem();
//      //  districtId = selectedItem.getID();
//        districtId = selectedItem.getCityID();
//        Integer id =selectedItem.getID();
//        String nameAr = selectedItem.getNameAr();

        LinearLayout ripple_home;
        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.car_typ_layout))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.color.bacground)
                .setExpanded(true, 800)
                .setGravity(Gravity.CENTER)
                .setMargin(10,10,10,10)
                .create();
            RecyclerView carRec = (RecyclerView) dialog.findViewById(R.id.carRec);
        CarAdapter carAdapter=new CarAdapter(DeliveryInfoActivity.this,lookupModels);
        carRec.setLayoutManager(new LinearLayoutManager(DeliveryInfoActivity.this, LinearLayoutManager.VERTICAL, false));
        carRec.setAdapter(carAdapter);
        carAdapter.setOnClickListener(new CarAdapter.OnItemClickListener() {
            @Override
            public void onclick(int position) {
                 car_id = lookupModels.get(position).getCar_id();
                input_car.setText(lookupModels.get(position).getName());
                dialog.dismiss();
            }
        });


        dialog.show();
    }


public void setClick(){
    input_car.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getCarType();
        }
    });

    txt_signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
}
}
