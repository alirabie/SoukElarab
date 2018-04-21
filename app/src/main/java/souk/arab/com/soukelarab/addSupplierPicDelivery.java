package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andexert.library.RippleView;

public class addSupplierPicDelivery extends AppCompatActivity implements View.OnClickListener{
RippleView ripple_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier_pic_delivery);
        ripple_next =(RippleView)findViewById(R.id.ripple_next);
        ripple_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_next:
                startActivity(new Intent(addSupplierPicDelivery.this,storeinfo.class));
                break;
        }
    }
}
