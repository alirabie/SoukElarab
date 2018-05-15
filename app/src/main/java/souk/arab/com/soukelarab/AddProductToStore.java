package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class AddProductToStore extends AppCompatActivity {

    private LinearLayout ripple_addprodct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_store);
        setUpVar();
        setUpclick();

    }
    public void setUpVar(){
        ripple_addprodct= (LinearLayout) findViewById(R.id.ripple_addprodct);
    }
    public void setUpclick(){
        ripple_addprodct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDiloge();
            }
        });
    }
    public void setDiloge(){
        LinearLayout ripple_home;
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.addprosucc))
                .setExpanded(true)
                // This will enable the expand feature, (similar to android L share dialog)
                .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                .setContentBackgroundResource(R.drawable.lin_shap)
                .setExpanded(true, 850)
                .setGravity(Gravity.BOTTOM)
                .setMargin(10,10,10,10)
                .create();
        LinearLayout ok = (LinearLayout) dialog.findViewById(R.id.ripple_home);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProductToStore.this,StoreHome.class));
            }
        });
        dialog.show();
    }
}
