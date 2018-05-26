package souk.arab.com.soukelarab;

import android.graphics.Color;
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
import java.util.HashMap;
import java.util.List;

import Adapter.CardAdapter;
import Adapter.DriverAdapter;
import Adapter.NotiicationAdapter;
import ConstantClasss.Constanturl;

import Model.Drivers;
import Model.Notifications;
import Model.Notus;
import Model.RequestsModel;
import Presenter.Idealinterface;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleNotifcation  extends DialogFragment {


    private View view;
    private RecyclerView recyanim;
    ArrayList<Notus> faqModels;
    ACProgressFlower dialog;


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
        getNotifications(1);
        return view;
}
    public void setUpAll(){
        recyanim =(RecyclerView)view.findViewById(R.id.recyanim);


    }
    public void getNotifications(int id) {
        if (dialog == null) {
            dialog =  new ACProgressFlower.Builder(getActivity())
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .fadeColor(Color.DKGRAY).build();
        }
        dialog.show();
        HashMap input=new HashMap();
        input.put("matjar_id", id);
        // Log.e("inpp", input + "");
        Constanturl.createService(Idealinterface.class).getNotifications(input).enqueue(new Callback<Notifications>() {
            @Override
            public void onResponse(Call<Notifications> call, Response<Notifications> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                    faqModels = response.body().getNoti();
                    if (faqModels.size()==0){

                    }else {
                        NotiicationAdapter adapter = new NotiicationAdapter(getActivity(),faqModels);
                        recyanim.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        recyanim.setAdapter(adapter);
                    }


                } else {

                }
            }

            @Override
            public void onFailure(Call<Notifications> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                // Log.e("ERROrss", "ASMAA");
            }

        });
    }
    }
