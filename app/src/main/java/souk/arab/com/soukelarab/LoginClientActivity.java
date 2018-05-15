package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import ConstantClasss.Custom_EditText;
import ConstantClasss.Custom_Textview;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class LoginClientActivity extends AppCompatActivity {
    LinearLayout ripple_lgin ;
    private ImageView img_logo;
    private int type;
    private Custom_Textview txt_signin;
    Custom_EditText input_email,input_phone,input_password;
    private SharedPreferences.Editor editid;
    private ACProgressFlower dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);
        AndroidNetworking.initialize(getApplicationContext());
         type = getIntent().getExtras().getInt("type");
        SharedPreferences preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        setVar();

    }
    public void setVar(){
        ripple_lgin =(LinearLayout)findViewById(R.id.ripple_lgin);
        txt_signin =(Custom_Textview)findViewById(R.id.txt_signin);
        img_logo=(ImageView) findViewById(R.id.img_logo);
        input_email=(Custom_EditText) findViewById(R.id.input_email);
        input_phone=(Custom_EditText) findViewById(R.id.input_phone);
        input_password=(Custom_EditText) findViewById(R.id.input_password);

        // slide-up animation
//        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
//
//        if (img_logo.getVisibility() == View.INVISIBLE) {
//            img_logo.setVisibility(View.VISIBLE);
//            img_logo.startAnimation(slideUp);
//        }
        ripple_lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type==1){
                    if (!validation()){

                    }else {
                        registerClient();
                    }


                }
             else if (type==2){
                    Intent intent=new Intent(LoginClientActivity.this,codeActivity.class);
                    intent.putExtra("type",2);
                    startActivity(intent);
                }
            }
        });

        if (type==1){
            txt_signin.setText("إنشاء حساب عميل");
        }else {
            txt_signin.setText("إنشاء حساب مقدم خدمة");
        }

        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void registerClient(){
        dialog = new ACProgressFlower.Builder(LoginClientActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.register)
                .addBodyParameter("role","client")
                .addBodyParameter("username",input_email.getText().toString())
                .addBodyParameter("phone",input_phone.getText().toString())
                .addBodyParameter("password",input_password.getText().toString())
                .addBodyParameter("dev_type","android")
                .addBodyParameter("dev_token","android")
               // .addBodyParameter("profile_image","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")

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
                                String user_id = response.getString("user_id");
                                String role = response.getString("role");
                                editid.putString("user_id",user_id);
                                editid.putString("role",role);
                                editid.apply();
                                Intent intent=new Intent(LoginClientActivity.this,codeActivity.class);
                                intent.putExtra("type",1);
                                startActivity(intent);
                            }
                            else if(success.equals("0")){
                                Toast.makeText(LoginClientActivity.this, "عفوا هذا الحساب موجود مسبقا", Toast.LENGTH_LONG).show();
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

    //
    private boolean validation() {


        boolean check = true;
        if (input_email.getText().toString().equals("")&&input_password.getText().toString().equals("")&&input_phone.getText().toString().equals("")){
     //       input_email.setError(getResources().getString(R.string.required));
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
     //       input_password.setError(getResources().getString(R.string.required));
       //     input_phone.setError(getResources().getString(R.string.required));
            check =false;
        }
        else if(input_email.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
       //     input_email.setError(getResources().getString(R.string.required));
            check =false;
        }
        else if(input_phone.getText().length()<9){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
      //      input_phone.setError(getResources().getString(R.string.number));
            check =false;
        }
        else if(input_password.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
         //   input_password.setError(getResources().getString(R.string.required));
            check =false;
        }
        else if(input_phone.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
         //   input_phone.setError(getResources().getString(R.string.required));
            check =false;
        }
        return check;
    }
}
