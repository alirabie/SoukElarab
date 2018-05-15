package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import org.json.JSONException;
import org.json.JSONObject;

import ConstantClasss.Custom_EditText;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class codeDelivertActivity extends AppCompatActivity implements View.OnClickListener {
//@InjectView(R.id.ripple_confirm)RippleView ripple_confirm;
LinearLayout ripple_confirm;
    private SharedPreferences preferencesid;
    private String user_id;
    private Custom_EditText input_code;
    private ACProgressFlower dialog;
    private NiftyDialogBuilder dialogBuilder;

    TextView txt_signin;

    LinearLayout ripple_home;
    TextView txt_registerd, txt_confirm;
    private TextView txt_resend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_delivert);
        ripple_confirm =( LinearLayout)findViewById(R.id.ripple_confirm);
       // ButterKnife.inject(codeDelivertActivity.this);
        ripple_confirm.setOnClickListener(this);
        AndroidNetworking.initialize(codeDelivertActivity.this);
        preferencesid = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");

        ripple_confirm =(LinearLayout)findViewById(R.id.ripple_confirm);
        input_code =(Custom_EditText) findViewById(R.id.input_code);
        txt_signin =(TextView) findViewById(R.id.txt_signin);
        txt_resend =(TextView) findViewById(R.id.txt_resend);
        ripple_confirm.setOnClickListener(this);
        txt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txt_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resend();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_confirm:
                if (validation()){
                    code();
                }
                break;

        }
    }
    public void resend(){
        AndroidNetworking.post("http://market.wildso.com/public/api/replay_sms")

                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        try {
                            String success = response.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(codeDelivertActivity.this, "تم إرسال كود التحقيق مرة أخرى", Toast.LENGTH_SHORT).show();
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

    //
    private boolean validation() {
        boolean check = true;
        if (input_code.getText().toString().equals("")){
            Toast.makeText(this, "من فضلك ادخل الكود المرسل الى هاتفك ", Toast.LENGTH_SHORT).show();
            check =false;
        }
        return check;
    }

    public void code(){
        dialog = new ACProgressFlower.Builder(codeDelivertActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(getResources().getColor(R.color.colorAccent))
                .fadeColor(getResources().getColor(R.color.colorAccent)).build();
        dialog.show();
        AndroidNetworking.post(URLS.codeClient)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("active_code",input_code.getText().toString())

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
                                dialogBuilder = NiftyDialogBuilder.getInstance(codeDelivertActivity.this);
                                dialogBuilder

                                        .withDuration(700).withTitle("تم انشاء الحساب بنجاح")
                                        //def
                                        .withEffect(Effectstype.Flipv).withTitleColor(getResources().getColor(R.color.wite))
                                        .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                                        .setCustomView(R.layout.done, codeDelivertActivity.this)
                                        .show();
                                txt_registerd = (TextView) dialogBuilder.findViewById(R.id.txt_registerd);
                                txt_confirm = (TextView) dialogBuilder.findViewById(R.id.txt_confirm);
                                ripple_home = (LinearLayout) dialogBuilder.findViewById(R.id.ripple_home);
                                ripple_home.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(codeDelivertActivity.this, DeliverMain.class));
                                        dialogBuilder.dismiss();

                                    }
                                });

                                dialogBuilder.show();
                            }
                            else if(success.equals("0")){

                                Toast.makeText(codeDelivertActivity.this, "كود التحقيق غير صحيح", Toast.LENGTH_SHORT).show();
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
