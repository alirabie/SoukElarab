package souk.arab.com.soukelarab;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.asksira.loopingviewpager.LoopingViewPager;

import java.util.ArrayList;

import Adapter.DemoInfiniteAdapter;
import Model.PagerModel;

public class ViewPagerAcctivity extends AppCompatActivity {

    private DemoInfiniteAdapter adapter;
    private ArrayList<PagerModel> dataItems;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_acctivity);
        setDate();
    }

    public void setDate(){
        dataItems=new ArrayList<>();
        viewPager=findViewById(R.id.viewpager);
        String Name[]={"fdfdfddfdf","ahhhhhmed","Ibrahimmmmm"};

        for (int i=0;i<Name.length;i++){
            PagerModel pagerModel=new PagerModel();
            pagerModel.setName(Name[i]);
            dataItems.add(pagerModel);
        }

    }
}
