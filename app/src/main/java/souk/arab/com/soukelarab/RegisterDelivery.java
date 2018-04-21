package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

public class RegisterDelivery extends AppCompatActivity {
LinearLayout ripple_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_delivery);
        ripple_next =(LinearLayout)findViewById(R.id.ripple_next);
        ripple_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterDelivery.this,codeDelivertActivity.class));
            }
        });
    }
}
