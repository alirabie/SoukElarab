package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

public class codeDelivertActivity extends AppCompatActivity implements View.OnClickListener {
//@InjectView(R.id.ripple_confirm)RippleView ripple_confirm;
LinearLayout ripple_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_delivert);
        ripple_confirm =( LinearLayout)findViewById(R.id.ripple_confirm);
       // ButterKnife.inject(codeDelivertActivity.this);
        ripple_confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_confirm:
                startActivity(new Intent(codeDelivertActivity.this,addPicsDelivery.class));
                break;

        }
    }
}
