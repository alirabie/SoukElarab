package souk.arab.com.soukelarab;

import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

import Adapter.CardAdapter;
import Adapter.NotiicationAdapter;
import Model.RequestsModel;

public class RecycleNotifcation  extends DialogFragment {


    private View view;
    private RecyclerView recyanim;
    private ArrayList<RequestsModel> faqModels;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_recycle_notifcation, container);
//        Window window = getDialog().getWindow();
//        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        window.setGravity(Gravity.TOP | Gravity.LEFT);
//        window.setBackgroundDrawableResource(R.color.bacground);


        // e.g. top + right margins:
        Window window = getDialog().getWindow();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        window.setBackgroundDrawableResource(R.color.bacground);
        getDialog().getWindow().setGravity(Gravity.TOP|Gravity.LEFT);
        WindowManager.LayoutParams layoutParams =  getDialog().getWindow().getAttributes();
        layoutParams.x =0; // right margin
        layoutParams.y = 115; // top margin
        getDialog().getWindow().setAttributes(layoutParams);
//        // e.g. bottom + left margins:
//        getDialog().getWindow().setGravity(Gravity.BOTTOM|Gravity.LEFT);
//        layoutParams = getDialog().getWindow().getAttributes();
//        layoutParams.x = 100; // left margin
//        layoutParams.y = 170; // bottom margin
//        getDialog().getWindow().setAttributes(layoutParams);



        getDialog().getWindow().getAttributes().windowAnimations= R.style.dialog_slide_animation;

        setUpAll();
        return view;
}
    public void setUpAll(){
        recyanim =(RecyclerView)view.findViewById(R.id.recyanim);

        NotiicationAdapter adapter = new NotiicationAdapter(getActivity(),faqModels);
        recyanim.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyanim.setAdapter(adapter);
    }
    }
