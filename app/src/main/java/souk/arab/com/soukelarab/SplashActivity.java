package souk.arab.com.soukelarab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private String role;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        startThread();
    }

    private void startThread() {
        Thread mythread = new Thread() {
            public void run() {
                try {
                    int mythread = 0;
                    while (mythread < 3500) {
                        sleep(100);
                        mythread = mythread + 100;
                    }
                    SharedPreferences preferencesid = SplashActivity.this.getSharedPreferences("pref", Context.MODE_PRIVATE);
                    role = preferencesid.getString("role", "nnn");
                    code = preferencesid.getString("code", "nnn");
                    if (role.equals("client")&&code.equals("1")){
                        Intent intentt=new Intent(SplashActivity.this, ClinetHomePage.class);
                        intentt.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentt);
                    }   if (role.equals("driver")&&code.equals("1")){
                        Intent intent=new Intent(SplashActivity.this, DeliverMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                    else if (role.equals("nnn")||code.equals("nnn")){
                        Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }


                } catch (InterruptedException ie) {
                } finally {
                    finish();
                }
            }

        };
        mythread.start();
    }
}
