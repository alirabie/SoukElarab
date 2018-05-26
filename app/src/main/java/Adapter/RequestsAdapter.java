package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.Order;
import Model.Orderdetails;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.Requestdetails;


/**
 * Created by ahmed on 11/18/2017.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
RelativeLayout relall;
    List<Order> faqModels;
    boolean ischecked=false;

    public RequestsAdapter(Context context, List<Order> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.requestsitems, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        relall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(position);
                   // Toast.makeText(context, "tesst", Toast.LENGTH_SHORT).show();
               //    startActivity(context,Requestdetails.class);

                    Intent intent=new Intent(context, Orderdetails.class);
                    intent.putExtra("id",faqModels.get(position).getOrderId());
                    intent.putExtra("date",faqModels.get(position).getDate());
                    intent.putExtra("time",faqModels.get(position).getAddress());
                    intent.putExtra("img",faqModels.get(position).getUserImg());
                    intent.putExtra("name",faqModels.get(position).getUsername());

                    context.startActivity(intent);

                }
            }
        });
        Order faqModel = faqModels.get(position);
        if (faqModel.getUsername().equals("")){
            holder.txtClientName.setText("");

        }else {
            holder.txtClientName.setText(faqModel.getUsername());

        }
        if (faqModel.getAddress().equals("")){
            holder.txtClientlocation.setText("");

        }else {
            holder.txtClientlocation.setText(faqModel.getAddress());

        }
        if (faqModel.getDate().equals("")){
            holder.txtorderdate.setText("");
        }else {
            holder.txtorderdate.setText(faqModel.getDate());
        }
       // holder.txtorderTime.setText(faqModel.);
        if (faqModel.getUserImg().equals("")||faqModel.getUserImg().equals(null)){
            Picasso.with(context)
                    .load(URLS.imageBase+faqModel.getUserImg())
                    .placeholder(R.drawable.image1)
                    .error(R.drawable.image1)
                    .into(holder.ques_image);
        }
        else {
            Picasso.with(context).load(URLS.imageBase+faqModel.getUserImg()).into(holder.ques_image);
        }
    }
    @Override
    public int getItemCount() {
        return faqModels.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtClientName,txtorderTime,txtClientlocation,txtorderdate;
        private final LinearLayout lin_ques;
        ImageView ques_image;

        public ViewHolder(View itemView) {
            super(itemView);
            txtClientName=(TextView) itemView.findViewById(R.id.txtClientName);
            txtorderTime = (TextView)itemView.findViewById(R.id.txtorderTime);
            txtClientlocation = (TextView)itemView.findViewById(R.id.txtClientlocation);
            txtorderdate = (TextView)itemView.findViewById(R.id.txtorderdate);
            ques_image = (ImageView)itemView.findViewById(R.id.img_request);
            lin_ques = (LinearLayout)itemView.findViewById(R.id.relall);
            relall = (RelativeLayout)itemView.findViewById(R.id.relall);
        }
    }
}
