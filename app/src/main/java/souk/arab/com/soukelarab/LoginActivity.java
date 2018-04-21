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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
LinearLayout ripple_creatAccount;
ImageView img_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ripple_creatAccount=(LinearLayout)findViewById(R.id.ripple_creatAccount);
        img_logo=(ImageView) findViewById(R.id.img_logo);
        ripple_creatAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ripple_creatAccount:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                break;
                case R.id.img_logo:

                break;
        }
    }
}
