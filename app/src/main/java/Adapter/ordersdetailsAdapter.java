package Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.orders_detailslist;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class ordersdetailsAdapter extends RecyclerView.Adapter<ordersdetailsAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
RelativeLayout relall;
    List<orders_detailslist> faqModels;
    public   String imageBase="http://market.wildso.com/public/uploads/";

    boolean ischecked=false;

    public ordersdetailsAdapter(Context context, List<orders_detailslist> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.ordersitemdetails, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        relall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(position);
                    Toast.makeText(context, "tesst", Toast.LENGTH_SHORT).show();

                }
            }
        });
        orders_detailslist faqModel = faqModels.get(position);
        holder.txtClientName.setText(faqModel.getProductsName());
        holder.txtdisc.setText(faqModel.getProductsPrice());
        holder.txtdate.setText(faqModel.getProductsQty());
       // holder.txtorderTime.setText(faqModel.);
        if (faqModel.getUserImg().equals("")||faqModel.getUserImg().equals(null)){
            Picasso.with(context)
                    .load(imageBase+faqModel.getUserImg())
                    .placeholder(R.drawable.image1)
                    .error(R.drawable.image1)
                    .into(holder.ques_image);
        }
        else {
            Picasso.with(context).load(imageBase+faqModel.getUserImg()).into(holder.ques_image);
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
        private final TextView txtdisc,txtdate,txtClientName;
        ImageView ques_image;

        public ViewHolder(View itemView) {
            super(itemView);
            txtClientName=(TextView) itemView.findViewById(R.id.txtClientName);
            txtdisc = (TextView)itemView.findViewById(R.id.txtdisc);
            txtdate = (TextView)itemView.findViewById(R.id.txtdate);
        }
    }
}
