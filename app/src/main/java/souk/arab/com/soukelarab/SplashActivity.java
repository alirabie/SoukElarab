package souk.arab.com.soukelarab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

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

                    Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);

                } catch (InterruptedException ie) {
                    ie.printStackTrace();

                } finally {
                    finish();
                }
            }

        };
        mythread.start();
    }
}
