package souk.arab.com.soukelarab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.widget.TextView;

import com.andexert.library.RippleView;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;

public class RegisterActivity extends Activity implements View.OnClickListener {
/*@InjectView(R.id.ripple_login)RippleView ripple_login,ripple_client,ripple_servant;
@InjectView(R.id.ripple_delivery)RippleView ripple_delivery;*/
LinearLayout ripple_login,ripple_client,ripple_servant,ripple_delivery;

TextView txt_creataccount;
boolean visible=false;
    private boolean visiblepr=false;
ObjectAnimator anim,anim2,anim3;
    private AnimatorSet bouncer;
    private boolean visiblee=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ripple_servant =(LinearLayout)findViewById(R.id.ripple_servant);
        ripple_client =(LinearLayout)findViewById(R.id.ripple_client);
        ripple_login =(LinearLayout)findViewById(R.id.ripple_login);
        ripple_delivery = (LinearLayout)findViewById(R.id.ripple_delivery);
        txt_creataccount = (TextView) findViewById(R.id.txt_creataccount);
        ripple_client.setVisibility(INVISIBLE);
        ripple_delivery.setVisibility(INVISIBLE);
        ripple_servant.setVisibility(INVISIBLE);
        //
        ripple_client.setOnClickListener(this);
        ripple_delivery.setOnClickListener(this);
        ripple_login.setOnClickListener(this);
        ripple_servant.setOnClickListener(this);
        txt_creataccount.setOnClickListener(this);
       // ButterKnife.inject(RegisterActivity.this);
         bouncer = new AnimatorSet();
        setUpAnim();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_client:
                Intent intent=new Intent(RegisterActivity.this,LoginClientActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.ripple_delivery:
                startActivity(new Intent(RegisterActivity.this,RegisterDelivery.class));
                break;
            case R.id.ripple_servant:
                Intent intentt=new Intent(RegisterActivity.this,registersupplierActivity.class);
                intentt.putExtra("type",2);
                startActivity(intentt);
                break;
                case R.id.txt_creataccount:
              finish();
                break;
                case R.id.ripple_login:
                    Intent intenttt=new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intenttt);
                break;
        }

    }

    public void setUpAnim(){
Thread thread=new Thread();
        if (visible==false){
            ripple_client.setVisibility(View.VISIBLE);
            anim = ObjectAnimator.ofFloat(ripple_client, "alpha",0f,1f);
            anim.setDuration(1000);
            anim.start();
            try {
                thread.sleep(500);
                ripple_delivery.setVisibility(View.VISIBLE);
                anim2 = ObjectAnimator.ofFloat(ripple_delivery, "alpha",0f,1f);
                anim2.setDuration(1000);
                anim2.start();
                thread.sleep(500);
                ripple_servant.setVisibility(View.VISIBLE);
                anim3 = ObjectAnimator.ofFloat(ripple_servant, "alpha", 0f,1f);
                anim3.setDuration(1000);
                anim3.start();

            } catch (InterruptedException e) {

            }

//            bouncer.playSequentially(anim, anim2, anim2);
//            bouncer.start();
        }

//        final Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.popupanimation);
//        ripple_client.setAnimation(animationFadeIn);
//        ripple_delivery.setAnimation(animationFadeIn);
//        ripple_servant.setAnimation(animationFadeIn);




//        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ripple_delivery, "alpha", 0f,1f);
//        anim2.setDuration(1000);
//        ObjectAnimator anim3 = ObjectAnimator.ofFloat(ripple_servant, "alpha", 0f,1f);
//        anim3.setDuration(1000);
//
//
//        bouncer.playSequentially(anim, anim2, anim3);
//        bouncer.start();
    }

}
