package Fragments;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import URLS.URLS;
import souk.arab.com.soukelarab.DeliverMain;
import souk.arab.com.soukelarab.DeliveryInfoActivity;
import souk.arab.com.soukelarab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_DriverSetting extends Fragment {
    ImageButton btn_setting, btn_home;
View view;
SwitchCompat switchx;
    private SharedPreferences preferencesid;
    private String user_id;
    private SharedPreferences.Editor edit;

    public Fragment_DriverSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_fragment__driver_setting, container, false);
         preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
         edit = preferencesid.edit();
        user_id = preferencesid.getString("user_id", "00");
        switchx=view.findViewById(R.id.switchx);

        String online = preferencesid.getString("online", "1");
        if (online.equals("1")){
            switchx.setChecked(true);

        }else if (online.equals("0")){
            switchx.setChecked(false);
        }
        setChange();
        // Inflate the layout for this fragment
        return view;
    }
    public void setChange(){
        switchx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    changeStatus(1);
                }
                else {
                    changeStatus(0);
                }
            }
        });
    }
       public void changeStatus(int Online){
        AndroidNetworking.post(URLS.change_type)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("online", String.valueOf(Online))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                int online = response.getInt("online");
                                if (online==0){
                                    edit.putString("online","0");
                                    edit.apply();
                                    Toast.makeText(getActivity(), "تم تغير الحاله الى مشغول سوف يمنع ذلك ظهورك لمقدمى الخدمات", Toast.LENGTH_SHORT).show();
                                }
                                if (online==1){
                                    edit.putString("online","1");
                                    edit.apply();
                                    Toast.makeText(getActivity(), "تم تغير الحاله الى متاح سوف تظهر لمقدمى الخدمات", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } {

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public void getHomeDetailse(int Online){
        AndroidNetworking.post(URLS.change_type)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("online", String.valueOf(Online))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                int online = response.getInt("online");
                                if (online==0){
                                    Toast.makeText(getActivity(), "تم تغير الحاله الى مشغول سوف يمنع ذلك ظهورك لمقدمى الخدمات", Toast.LENGTH_SHORT).show();
                                }
                                if (online==1){
                                    Toast.makeText(getActivity(), "تم تغير الحاله الى متاح سوف تظهر لمقدمى الخدمات", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } {

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


}
