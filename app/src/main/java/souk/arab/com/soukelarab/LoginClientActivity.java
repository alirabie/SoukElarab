package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;

public class LoginClientActivity extends AppCompatActivity {
    LinearLayout ripple_lgin ;
    private ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_client);
        setVar();

    }
    public void setVar(){
        ripple_lgin =(LinearLayout)findViewById(R.id.ripple_lgin);
        //
        img_logo=(ImageView) findViewById(R.id.img_logo);
        // slide-up animation
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        if (img_logo.getVisibility() == View.INVISIBLE) {
            img_logo.setVisibility(View.VISIBLE);
            img_logo.startAnimation(slideUp);
        }
        ripple_lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginClientActivity.this,codeActivity.class));
            }
        });
    }
}
