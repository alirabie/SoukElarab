package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
LinearLayout ripple_creatAccount;
LinearLayout ripple_login;
ImageView img_logo;
    private SharedPreferences.Editor editid;
    EditText     input_password, input_email;
    public static final int RequestPermissionCode = 1;
    private ACProgressFlower dialog;

    TextView txt_signin,txt_forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (checkPermission()) {

            // Toast.makeText(OnBoarding.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();

        } else {
            requestPermission();
        }
        AndroidNetworking.initialize(getApplicationContext());

        SharedPreferences preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        ripple_creatAccount=(LinearLayout)findViewById(R.id.ripple_creatAccount);
        ripple_login=(LinearLayout)findViewById(R.id.ripple_login);
        img_logo=(ImageView) findViewById(R.id.img_logo);

        input_password=(EditText) findViewById(R.id.input_password);
        input_email=(EditText) findViewById(R.id.input_email);
        txt_signin=(TextView) findViewById(R.id.txt_signin);
        txt_forgetPass=(TextView) findViewById(R.id.txt_forgetPass);
        ripple_creatAccount.setOnClickListener(this);
        setLogin();
        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_creatAccount:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
                case R.id.img_logo:

                break;

        }
    }
    public void setLogin(){
        ripple_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation())
                {
                    login();
                }
            }
        });
        txt_forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetPass();
            }
        });
    }

    public void login(){
        dialog = new ACProgressFlower.Builder(LoginActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.Login)
                .addBodyParameter("phone",input_email.getText().toString())
                .addBodyParameter("password",input_password.getText().toString())

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
                                String role = user.getString("role");
                                String user_id = user.getString("user_id");
                                editid.putString("role",role);
                                editid.putString("user_id",user_id);
                                editid.apply();
                                if (role.equals("client")){
                                    Intent intent=new Intent(LoginActivity.this,ClinetHomePage.class);
                                    intent.putExtra("type",1);
                                    startActivity(intent);
                                }

                                else if (role.equals("driver")){
                                    Intent intent=new Intent(LoginActivity.this,DeliverMain.class);

                                    startActivity(intent);
                                }

                            }
                            else if(success.equals("0")){
                                Toast.makeText(LoginActivity.this, "خطأ فى رقم الهاتف او كلمة المرور ", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    //
    private boolean validation() {
        boolean check = true;
        if (input_email.getText().toString().equals("")&&input_password.getText().toString().equals("")){
          //  input_email.setError(getResources().getString(R.string.required));
        //    input_password.setError(getResources().getString(R.string.required));
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_email.getText().toString().equals("")){
        //    input_email.setError(getResources().getString(R.string.required));
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_password.getText().toString().equals("")){
        //    input_password.setError(getResources().getString(R.string.required));
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        return check;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(LoginActivity.this, new String[]
                {
                        CAMERA,
                        ACCESS_FINE_LOCATION,
                        WRITE_EXTERNAL_STORAGE
                }, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (CameraPermission && ReadContactsPermission && ReadPhoneStatePermission) {

                        Toast.makeText(LoginActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }

                break;
        }
    }


    public boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
    //filter
    public void forgetPass() {

        final DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.forgetpa))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300

                .setExpanded(true, 800)
                .setGravity(Gravity.CENTER)
                .setMargin(10, 0, 10, 10)
                .create();

        final EditText phone = (EditText) dialog.findViewById(R.id.input_email);
        LinearLayout ripple_creatAccount = (LinearLayout) dialog.findViewById(R.id.ripple_creatAccount);
     ripple_creatAccount.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (phone.getText().toString().equals("")){
                 Toast.makeText(LoginActivity.this, "من فضلك أدخل رقم الجوال", Toast.LENGTH_SHORT).show();
             }else {
                 forgetPassword(phone,dialog);

             }
         }
     });

        dialog.show();
    }
    public void forgetPassword(EditText phone, final DialogPlus dialogPlus){

        AndroidNetworking.post("http://market.wildso.com/public/api/forgetpassword")
                .addBodyParameter("phone",phone.getText().toString())


                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                String user = response.getString("user");
                                Toast.makeText(LoginActivity.this, "تم إرسال كلمة مرور مؤقته على هاتفك ", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

}
