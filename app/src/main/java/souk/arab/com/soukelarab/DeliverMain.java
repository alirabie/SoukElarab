package souk.arab.com.soukelarab;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.andexert.library.RippleView;

import Fragments.Fragment_DriverSetting;
import Fragments.Fragment_Driverhome;

public class DeliverMain extends AppCompatActivity implements View.OnClickListener {
    ImageButton btn_setting, btn_home;
    RippleView ripple_home, ripple_setting;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_main);
        btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        ripple_setting = (RippleView) findViewById(R.id.ripple_setting);
        ripple_home = (RippleView) findViewById(R.id.ripple_home);
        ripple_home.setOnClickListener(this);
        ripple_setting.setOnClickListener(this);
        setSelectedFragment(0);
    }

    public void replace_fragment(Fragment frag, String frag_tag) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, frag, frag_tag).addToBackStack("0");
        // onRestart();
        ft.commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ripple_setting:
                selectedFragment = new Fragment_DriverSetting();
                replace_fragment(selectedFragment, "container");
                break;
            case R.id.ripple_home:
//                selectedFragment = new Fragment_Driverhome();
//                replace_fragment(selectedFragment, "");
                selectedFragment = new Fragment_DriverSetting();
                replace_fragment(selectedFragment, "");
                break;
        }
    }

    private void setSelectedFragment(int position) {
        switch (position) {
            case 0:
                selectedFragment = new Fragment_DriverSetting();
                replace_fragment(selectedFragment, "container");
                // sub_title.setText("اخبار الاداره");
                break;
            case 1:
//                selectedFragment = new Fragment_Driverhome();
//                replace_fragment(selectedFragment, "");
                selectedFragment = new Fragment_DriverSetting();
                replace_fragment(selectedFragment, "");
                break;
        }
    }
}
