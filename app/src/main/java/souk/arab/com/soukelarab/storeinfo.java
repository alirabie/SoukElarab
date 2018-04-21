package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class storeinfo extends AppCompatActivity implements View.OnClickListener {
RippleView ripple_register;
    NiftyDialogBuilder dialogBuilder;
    TextView txt_registerd,txt_confirm;
    RippleView ripple_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);
        ripple_register =(RippleView)findViewById(R.id.ripple_register);
        ripple_register = (RippleView)findViewById(R.id.ripple_register);
        ripple_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_register:
                dialogBuilder = NiftyDialogBuilder.getInstance(storeinfo.this);
                dialogBuilder
                        .withDialogColor(getResources().getColor(R.color.movebg))
                        .withDuration(700).withTitle("تم انشاء الحساب بنجاح")                                          //def
                        .withEffect(Effectstype.Flipv).withTitleColor(getResources().getColor(R.color.wite))
                        .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                        .setCustomView(R.layout.done, this)
                        .show();
                txt_registerd =(TextView)dialogBuilder.findViewById(R.id.txt_registerd);
                txt_confirm =(TextView)dialogBuilder.findViewById(R.id.txt_confirm);
                ripple_home =(RippleView)dialogBuilder.findViewById(R.id.ripple_home);
                break;
        }
    }
}
