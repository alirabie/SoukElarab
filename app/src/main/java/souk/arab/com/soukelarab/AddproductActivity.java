package souk.arab.com.soukelarab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andexert.library.RippleView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class AddproductActivity extends AppCompatActivity implements View.OnClickListener {
RippleView ripple_addproduct;
    NiftyDialogBuilder dialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        ripple_addproduct =(RippleView)findViewById(R.id.ripple_addproduct);
        ripple_addproduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_addproduct:
                dialogBuilder = NiftyDialogBuilder.getInstance(AddproductActivity.this);
                dialogBuilder
                        .withDialogColor(getResources().getColor(R.color.movebg))
                        .withDuration(700).withTitle("تم إضافة منتجك بنجاح")                                          //def
                        .withEffect(Effectstype.Flipv).withTitleColor(getResources().getColor(R.color.wite))
                        .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                        .setCustomView(R.layout.productadded, this)
                        .show();
                RippleView ripple_home =(RippleView)findViewById(R.id.ripple_home);
                ripple_home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogBuilder.dismiss();
                    }
                });
                break;
        }
    }
}
