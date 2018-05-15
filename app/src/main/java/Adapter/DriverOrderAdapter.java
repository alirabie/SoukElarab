package Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ConstantClasss.Custom_Textview;
import Model.ClientOrderModel;
import Model.OrderModelDriver;
import URLS.URLS;
import souk.arab.com.soukelarab.ClinetHomePage;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.addSupplierPicDelivery;
import souk.arab.com.soukelarab.codeActivity;


/**
 * Created by ahmed on 11/18/2017.
 */

public class DriverOrderAdapter extends RecyclerView.Adapter<DriverOrderAdapter.ViewHolder> {


    private final SharedPreferences preferencesid;
    private final String user_id;
    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<OrderModelDriver> faqModels;
    boolean ischecked=false;
    int numberOfPro=0;
    public DriverOrderAdapter(Context context, ArrayList<OrderModelDriver> faqModels) {
        this.context = context;
        this.faqModels = faqModels;

        AndroidNetworking.initialize(context);
        preferencesid = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.driver_item_orddrrr, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       final OrderModelDriver faqModel = faqModels.get(position);
       holder.des.setText(faqModel.getProduct_name());
       holder.name.setText(faqModel.getMatgerName());
       holder.proNum.setText(faqModel.getProduct_qty());
       holder.price.setText(faqModel.getProduct_price() + " SAR ");
        String order_check = faqModel.getOrder_check();
        if (order_check.equals("1")){
            holder.status.setText("تحت الإجراء");
        }else if (order_check.equals("2")){
            holder.status.setText("تم شحن المنتج");
        }
        else if (order_check.equals("3")){
            holder.status.setText("تم تسليم المنتج");
        }

        holder.relClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(position);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return faqModels.size();
    }
    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       /* private final TextView lawer_faq_qu,answer;
        private final LinearLayout lin_ques,aswerLin;*/
       private final TextView name,price,des,proNum,status;
        com.whinc.widget.ratingbar.RatingBar ratingBar;
        ImageView ques_image;
        RelativeLayout relClick;
        public ViewHolder(View itemView) {
            super(itemView);
            ques_image = itemView.findViewById(R.id.ques_image);
            des=itemView.findViewById(R.id.des);
            name=itemView.findViewById(R.id.name);
            proNum=itemView.findViewById(R.id.proNum);
            price=itemView.findViewById(R.id.price);
            status=itemView.findViewById(R.id.status);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            relClick=itemView.findViewById(R.id.relClick);


        }
    }




}
