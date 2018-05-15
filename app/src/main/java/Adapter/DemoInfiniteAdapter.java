package Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;

import java.util.ArrayList;

import Model.PagerModel;
import souk.arab.com.soukelarab.R;

/**
 * Created by ahmed on 5/6/2018.
 */

public class DemoInfiniteAdapter extends ViewPager {

ArrayList<PagerModel>itemList;
Context context;
    private Boolean disable = false;
    public DemoInfiniteAdapter(Context context, ArrayList<PagerModel> itemList) {
        super(context);
    }
    public DemoInfiniteAdapter(Context context, AttributeSet attrs){
        super(context,attrs);
        this.context = context;
        this.itemList = itemList;
    }


    //This method will be triggered if the item View has not been inflated before.
//    @Override
//    protected View inflateView(int viewType, int listPosition) {
//        return LayoutInflater.from(context).inflate(R.layout.item_pager, null);
//    }

    //Bind your data with your item View here.
    //Below is just an example in the demo app.
    //You can assume convertView will not be null here.
    //You may also consider using a ViewHolder pattern.
//    @Override
//    protected void bindView(View convertView, int listPosition, int viewType) {
//      //  convertView.findViewById(R.id.image).setBackgroundColor(context.getResources().getColor(getBackgroundColor(listPosition)));
//        TextView description = convertView.findViewById(R.id.description);
//        description.setText((itemList.get(listPosition).getName()));
//    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return disable ? false : super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return disable ? false : super.onTouchEvent(event);
    }

    public void disableScroll(Boolean disable){
        //When disable = true not work the scroll and when disble = false work the scroll
        this.disable = disable;
    }

}