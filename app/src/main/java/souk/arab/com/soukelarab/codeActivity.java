package souk.arab.com.soukelarab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import ConstantClasss.Custom_EditText;
import ConstantClasss.Custom_Textview;
import URLS.URLS;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class codeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int INTENT_REQUEST_GET_IMAGES = 200;
    LinearLayout ripple_confirm;
    NiftyDialogBuilder dialogBuilder;
    ImageView img_addpic;
    Custom_Textview txt_addpic,txt_skip;
    TextView txt_signin;
    LinearLayout ripple_add;
    private int type;
    private ACProgressFlower dialog;
    Custom_EditText input_code;
    private SharedPreferences preferencesid;
    private String user_id;
    private String rosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        AndroidNetworking.initialize(codeActivity.this);
        rosa="null";
        preferencesid = codeActivity.this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
        type = getIntent().getExtras().getInt("type");
        ripple_confirm =(LinearLayout)findViewById(R.id.ripple_confirm);
        input_code =(Custom_EditText) findViewById(R.id.input_code);
        txt_signin =(TextView) findViewById(R.id.txt_signin);
        ripple_confirm.setOnClickListener(this);

//        dialogBuilder = NiftyDialogBuilder.getInstance(codeActivity.this);
//        dialogBuilder
//                .withDuration(500).withTitle("تم انشاء الحساب بنجاح")                                       //def
//                .withEffect(Effectstype.SlideBottom).withTitleColor(getResources().getColor(R.color.wite))
//                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
//                .setCustomView(R.layout.layout_registerdsuccess, this)
//                ;
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
            case R.id.ripple_confirm:
                if (validation()){
                    code();
                }

                break;
        }
    }
    public void setDiloge(){
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.layout_registerdsuccess))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.drawable.lin_shap)
                .setExpanded(true, 800)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();
        txt_skip =(Custom_Textview)dialog.findViewById(R.id.txt_skip);
        txt_addpic =(Custom_Textview)dialog.findViewById(R.id.txt_addpic);
        img_addpic =(ImageView) dialog.findViewById(R.id.img_addpic);
        ripple_add =(LinearLayout)dialog.findViewById(R.id.ripple_add);
        img_addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Config config = new Config();
                config.setSelectionMin(1);
                config.setSelectionLimit(1);
                ImagePickerActivity.setConfig(config);
                Intent intent = new Intent(codeActivity.this, ImagePickerActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
            }
        });
        ripple_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type==1){
                    if (rosa.equals("null")){
                        Toast.makeText(codeActivity.this, "من فضلك قم باختيار الصورة الشخصية", Toast.LENGTH_SHORT).show();
                    }else {
                        UploadImage();
                        Intent intent=new Intent(codeActivity.this,ClinetHomePage.class);
                        startActivity(intent);
                    }

                }
                else if (type==2){
                    if (rosa.equals("null")){
                        Toast.makeText(codeActivity.this, "من فضلك قم باختيار الصورة الشخصية", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        UploadImage();
                        Intent intent=new Intent(codeActivity.this,addSupplierPicDelivery.class);
                        startActivity(intent);
                    }

                }

            }
        });
        dialog.show();
    }

    public void code(){
        dialog = new ACProgressFlower.Builder(codeActivity.this)
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

                              setDiloge();
                            }
                            else if(success.equals("0")){
                                setDiloge();
                                Toast.makeText(codeActivity.this, "كود التحقيق غير صحيح", Toast.LENGTH_SHORT).show();
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

    public void UploadImage(){

        AndroidNetworking.post("http://market.wildso.com/public/api/upload_profile_image")
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("profile_image",rosa)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response

                        try {
                            String success = response.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(codeActivity.this, "تم تحميل الصورة الشخصية بنجاح", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == INTENT_REQUEST_GET_IMAGES && resultCode == Activity.RESULT_OK) {


            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for (int i = 0; i < image_uris.size(); i++) {
                Uri uri = image_uris.get(i);
                String path = uri.getPath();
                try {
                    Bitmap bitmap = PhotoLoader.init().from(path).requestSize(512, 512).getBitmap();

                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 300);
                    rosa = ImageBase64.encode(resizedBitmap);
                    Toast.makeText(codeActivity.this, R.string.load, Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    //    Toast.makeText(addPicsDelivery.this, R.string.notlooad, Toast.LENGTH_LONG).show();

                }


            }


        }
    }

//get image calculate


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


}
