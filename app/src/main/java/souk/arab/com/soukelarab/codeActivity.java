package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class codeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout ripple_confirm;
    NiftyDialogBuilder dialogBuilder;
    ImageView img_addpic;
    TextView txt_addpic,txt_skip;
    LinearLayout ripple_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ripple_confirm =(LinearLayout)findViewById(R.id.ripple_confirm);
        ripple_confirm.setOnClickListener(this);

//        dialogBuilder = NiftyDialogBuilder.getInstance(codeActivity.this);
//        dialogBuilder
//                .withDuration(500).withTitle("تم انشاء الحساب بنجاح")                                       //def
//                .withEffect(Effectstype.SlideBottom).withTitleColor(getResources().getColor(R.color.wite))
//                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
//                .setCustomView(R.layout.layout_registerdsuccess, this)
//                ;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_confirm:
                setDiloge();
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
                .setExpanded(true, 1000)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();
        txt_skip =(TextView)dialog.findViewById(R.id.txt_skip);
        txt_addpic =(TextView)dialog.findViewById(R.id.txt_addpic);
        ripple_add =(LinearLayout)dialog.findViewById(R.id.ripple_add);
        ripple_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(codeActivity.this,ClinetHomePage.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
