package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class registersupplierActivity extends AppCompatActivity {
    LinearLayout ripple_lgin ;
    private ImageView img_logo;
    private int type;
    private TextView txt_signin;
    EditText   input_email,input_phone,input_password;
    private SharedPreferences.Editor editid;
    private ACProgressFlower dialog;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_supplier);
        AndroidNetworking.initialize(getApplicationContext());
        check = true;
        type = getIntent().getExtras().getInt("type");
        SharedPreferences preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        editid = preferencesid.edit();
        setVar();
        ripple_lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (validation()){

                }*/
                if (input_email.getText().toString().equals("")){
                    input_email.setError(getApplicationContext().getResources().getString(R.string.required));
                    check=false;

                }else if (input_phone.getText().toString().equals("")){
                    input_phone.setError(getApplicationContext().getResources().getString(R.string.required));
                    check=false;
                }else if (input_phone.getText().toString().length()<9){
                    input_phone.setError(getApplicationContext().getResources().getString(R.string.number));
                    check=false;
                }
                else if (input_password.getText().toString().equals("")){
                    input_password.setError(getApplicationContext().getResources().getString(R.string.required));
                    check=false;
                }
                else {
                    //startActivity(new Intent(registersupplierActivity.this,addSupplierPicDelivery.class));
                    Intent intent = new Intent(registersupplierActivity.this,addSupplierPicDelivery.class);
                    intent.putExtra("phone",input_phone.getText().toString());
                    intent.putExtra("name",input_email.getText().toString());
                    intent.putExtra("pass",input_password.getText().toString());
                    startActivity(intent);
                }


            }

        });


    }
    public void setVar(){
        ripple_lgin =(LinearLayout)findViewById(R.id.ripple_lgin);
        txt_signin =(TextView)findViewById(R.id.txt_signin);
        img_logo=(ImageView) findViewById(R.id.img_logo);
        input_email=(EditText) findViewById(R.id.input_emailsupplier);
        input_phone=(EditText) findViewById(R.id.input_phonesupplier);
        input_password=(EditText) findViewById(R.id.input_passwordsupplier);

        // slide-up animation
//        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
//
//        if (img_logo.getVisibility() == View.INVISIBLE) {
//            img_logo.setVisibility(View.VISIBLE);
//            img_logo.startAnimation(slideUp);
//        }


        txt_signin.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_email.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_phone.getText().toString().length()<11){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_password.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(input_phone.getText().toString().equals("")){
            Toast.makeText(this, R.string.required, Toast.LENGTH_SHORT).show();
            check =false;
        }
        return check;
    }
}
