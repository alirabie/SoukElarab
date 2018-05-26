package Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import ConstantClasss.Custom_EditText;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import souk.arab.com.soukelarab.LoginClientActivity;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.codeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {


    private View view;
    private ACProgressFlower dialog;
    private SharedPreferences preferencesid;
    private String user_id;
    Custom_EditText input_email,input_phone,input_password;
    private LinearLayout ripple_lgin;

    public Settings() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_settings, container, false);
        AndroidNetworking.initialize(getActivity());
        preferencesid = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        input_email=(Custom_EditText) view.findViewById(R.id.input_email);
        input_phone=(Custom_EditText) view.findViewById(R.id.input_phone);
        input_password=(Custom_EditText) view.findViewById(R.id.input_password);
        ripple_lgin=(LinearLayout) view.findViewById(R.id.ripple_lgin);
        getProfile();
        setClick();
         return  view;
    }
    public void getProfile(){
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.getProfile)
                .addBodyParameter("user_id",user_id)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        dialog.dismiss();
                        try {
                            String success = response.getString("success");
                            if(success.equals("1")){
                                JSONObject user = response.getJSONObject("user");
                                String phone = user.getString("phone");
                                String username = user.getString("username");
                                String password = user.getString("password");
                                input_email.setText(username);
                                input_password.setText(password);
                                input_phone.setText(phone);

                            }
                        } catch (JSONException e) {

                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.dismiss();
                        // handle error
                    }
                });
    }


    public void update(){
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.updateProfi)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("phone",input_phone.getText().toString())
                .addBodyParameter("password",input_password.getText().toString())
                .addBodyParameter("username",input_email.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        dialog.dismiss();
                        try {
                            String success = response.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(getActivity(), "تم تحديث بياناتك بنجاح", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {

                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        dialog.dismiss();
                        // handle error
                    }
                });
    }
    public void setClick(){
        ripple_lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }
}
