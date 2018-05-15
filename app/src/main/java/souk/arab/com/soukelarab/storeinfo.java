package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class storeinfo extends AppCompatActivity implements View.OnClickListener {
LinearLayout ripple_register;
    NiftyDialogBuilder dialogBuilder;
    TextView txt_registerd,txt_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo);
        ripple_register =(LinearLayout)findViewById(R.id.ripple_register);
        ripple_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_register:
                setDiloge();
                break;
        }
    }


    public void setDiloge(){
        LinearLayout ripple_home;
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.done))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.drawable.lin_shap)
                .setExpanded(true, 800)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();
        LinearLayout ok = (LinearLayout) dialog.findViewById(R.id.ripple_home);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(storeinfo.this,StoreHome.class));
            }
        });
        dialog.show();
    }
}
