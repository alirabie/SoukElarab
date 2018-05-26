package souk.arab.com.soukelarab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;

import java.util.ArrayList;

public class addSupplierPicDelivery extends AppCompatActivity implements View.OnClickListener{
String phone,name,pass;
Intent intent;
    private static final int INTENT_REQUEST_GET_IMAGES = 11;
    LinearLayout ripple_next;
    LinearLayout ripple_add,ripple_adddoc,ripple_addcarpic;
    de.hdodenhof.circleimageview.CircleImageView img_addpic,img_docdpic,img_carpic;
    private static final int INTENT_REQUEST_GET_skel = 12;
    private static final int INTENT_REQUEST_car = 13;
    private String rosa="";
    private String skel="";
    private String car="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier_pic_delivery);
        ripple_next =(LinearLayout)findViewById(R.id.ripple_nextsupplier);
        ripple_add = (LinearLayout)findViewById(R.id.ripple_add);
        ripple_adddoc = (LinearLayout)findViewById(R.id.ripple_adddoc);
        ripple_addcarpic = (LinearLayout)findViewById(R.id.ripple_addcarpic);
        img_addpic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.img_addpic);
        img_docdpic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.img_docdpic);
        img_carpic = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.img_carpic);
        intent = getIntent();
        ripple_next.setOnClickListener(this);
        pass = intent.getStringExtra("pass");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        setImage();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_nextsupplier:
                ripple_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!validation()){

                        }else {
                            Intent intent=new Intent(addSupplierPicDelivery.this,storeinfo.class);
                            intent.putExtra("name",name);
                            intent.putExtra("phone",phone);
                            intent.putExtra("password",pass);
                            intent.putExtra("rosa",rosa);
                            intent.putExtra("skel",skel);
                            intent.putExtra("car",car);
                            startActivity(intent);

                        }

                    }
                });
               // startActivity(new Intent(addSupplierPicDelivery.this,storeinfo.class));
                break;
        }
    }





    private boolean validation() {

        boolean check = true;
        if (rosa.equals("")&&skel.equals("1")&&car.equals("")){
            Toast.makeText(addSupplierPicDelivery.this, "من فضلك ادخل الصور المطلوبة", Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(rosa.equals("")){
            Toast.makeText(addSupplierPicDelivery.this, "من فضلك ادخل الصور المطلوبة", Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(skel.equals("")){
            Toast.makeText(addSupplierPicDelivery.this, "من فضلك ادخل الصور المطلوبة", Toast.LENGTH_SHORT).show();
            check =false;
        }
        else if(car.equals("1")){
            Toast.makeText(addSupplierPicDelivery.this, "من فضلك ادخل الصور المطلوبة", Toast.LENGTH_SHORT).show();
            check =false;
        }
        return check;
    }





    public void setImage() {

        ripple_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config config = new Config();
                config.setSelectionMin(1);
                config.setSelectionLimit(1);
                ImagePickerActivity.setConfig(config);
                Intent intent = new Intent(addSupplierPicDelivery.this, ImagePickerActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
            }
        });       ripple_adddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config config = new Config();
                config.setSelectionMin(1);
                config.setSelectionLimit(1);
                ImagePickerActivity.setConfig(config);
                Intent intent = new Intent(addSupplierPicDelivery.this, ImagePickerActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_GET_skel);
            }
        });       ripple_addcarpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Config config = new Config();
                config.setSelectionMin(1);
                config.setSelectionLimit(1);
                ImagePickerActivity.setConfig(config);
                Intent intent = new Intent(addSupplierPicDelivery.this, ImagePickerActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_car);
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
                    img_addpic.setImageBitmap(bitmap);
                    // Toast.makeText(addPicsDelivery.this, R.string.load, Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    //    Toast.makeText(addPicsDelivery.this, R.string.notlooad, Toast.LENGTH_LONG).show();

                }


            }


        } else if (requestCode == INTENT_REQUEST_GET_skel && resultCode == Activity.RESULT_OK) {


            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for (int i = 0; i < image_uris.size(); i++) {
                Uri uri = image_uris.get(i);
                String path = uri.getPath();
                try {
                    Bitmap bitmap = PhotoLoader.init().from(path).requestSize(512, 512).getBitmap();

                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 300);
                    skel = ImageBase64.encode(resizedBitmap);
                    img_docdpic.setImageBitmap(bitmap);
                    //Toast.makeText(addPicsDelivery.this, R.string.load, Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    //      Toast.makeText(NewTeamms.this, R.string.notlooad, Toast.LENGTH_LONG).show();

                }
            }
        }
        else if (requestCode == INTENT_REQUEST_car && resultCode == Activity.RESULT_OK) {


            ArrayList<Uri> image_uris = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
            for (int i = 0; i < image_uris.size(); i++) {
                Uri uri = image_uris.get(i);
                String path = uri.getPath();
                try {
                    Bitmap bitmap = PhotoLoader.init().from(path).requestSize(512, 512).getBitmap();

                    Bitmap resizedBitmap = getResizedBitmap(bitmap, 300);
                    car = ImageBase64.encode(resizedBitmap);
                    // Toast.makeText(addPicsDelivery.this, R.string.load, Toast.LENGTH_LONG).show();
                    img_carpic.setImageBitmap(bitmap);

                } catch (Exception e) {
                    //      Toast.makeText(NewTeamms.this, R.string.notlooad, Toast.LENGTH_LONG).show();

                }
            }
        }

    }
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
