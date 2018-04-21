package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

public class addPicsDelivery extends AppCompatActivity implements View.OnClickListener {
LinearLayout ripple_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pics_delivery);
        ripple_next =(LinearLayout)findViewById(R.id.ripple_next);
        ripple_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_next:
                startActivity(new Intent(addPicsDelivery.this,DeliveryInfoActivity.class));
                break;
        }
    }
}
