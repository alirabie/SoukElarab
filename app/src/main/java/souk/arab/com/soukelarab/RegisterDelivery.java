package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import org.w3c.dom.Text;

import URLS.URLS;

public class RegisterDelivery extends AppCompatActivity {
LinearLayout ripple_next;

    LinearLayout ripple_lgin ;
    private ImageView img_logo;
    private int type;
    private TextView txtdelivery_signin;
    EditText input_email,input_phone,input_password;
    private SharedPreferences.Editor editid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_delivery);
        AndroidNetworking.initialize(getApplicationContext());

        SharedPreferences preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        setVar();
        setUpClick();


    }
    public void setVar() {
        ripple_next =(LinearLayout)findViewById(R.id.ripple_next);
        input_email = (EditText) findViewById(R.id.input_email);
        input_phone = (EditText) findViewById(R.id.input_phone);
        input_password = (EditText) findViewById(R.id.input_password);
        txtdelivery_signin = (TextView) findViewById(R.id.txtdelivery_signin);
    }
    public void setUpClick(){
        ripple_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()){
        //            startActivity(new Intent(RegisterDelivery.this,codeDelivertActivity.class));
                    Intent intent=new Intent(RegisterDelivery.this,addPicsDelivery.class);
                    intent.putExtra("name",input_email.getText().toString());
                    intent.putExtra("phone",input_phone.getText().toString());
                    intent.putExtra("password",input_password.getText().toString());
                    startActivity(intent);
                }else if (!validation()){

                }

            }
        });
        txtdelivery_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //
    private boolean validation() {


        boolean check = true;
        if (input_email.getText().toString().equals("")&&input_password.getText().toString().equals("")&&input_phone.getText().toString().equals("")){
            input_email.setError(getResources().getString(R.string.required));
            input_password.setError(getResources().getString(R.string.required));
            input_phone.setError(getResources().getString(R.string.required));
            check =false;
        }
        else if(input_email.getText().toString().equals("")){
            input_email.setError(getResources().getString(R.string.required));
            check =false;
        }

        else if(input_password.getText().toString().equals("")){
            input_password.setError(getResources().getString(R.string.required));
            check =false;
        }
        else if(input_phone.getText().toString().equals("")){
            input_phone.setError(getResources().getString(R.string.required));
            check =false;
        }
        return check;
    }
    }
