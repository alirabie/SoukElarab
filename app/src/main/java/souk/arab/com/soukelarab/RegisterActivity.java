package souk.arab.com.soukelarab;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

public class RegisterActivity extends Activity implements View.OnClickListener {
/*@InjectView(R.id.ripple_login)RippleView ripple_login,ripple_client,ripple_servant;
@InjectView(R.id.ripple_delivery)RippleView ripple_delivery;*/
LinearLayout ripple_login,ripple_client,ripple_servant,ripple_delivery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ripple_servant =(LinearLayout)findViewById(R.id.ripple_servant);
        ripple_client =(LinearLayout)findViewById(R.id.ripple_client);
        ripple_login =(LinearLayout)findViewById(R.id.ripple_login);
        ripple_delivery = (LinearLayout)findViewById(R.id.ripple_delivery);
        //
        ripple_client.setOnClickListener(this);
        ripple_delivery.setOnClickListener(this);
        ripple_login.setOnClickListener(this);
       // ButterKnife.inject(RegisterActivity.this);
        setUpAnim();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_client:
                startActivity(new Intent(RegisterActivity.this,LoginClientActivity.class));
                break;
            case R.id.ripple_delivery:
                startActivity(new Intent(RegisterActivity.this,RegisterDelivery.class));
                break;
        }
    }

    public void setUpAnim(){
        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.popupanimation);
        ripple_client.setAnimation(animationFadeIn);
        ripple_delivery.setAnimation(animationFadeIn);
        ripple_servant.setAnimation(animationFadeIn);
    }
}
