package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.BestTradingAdapter;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.MatgerDetailss;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class BestTrading extends RecyclerView.Adapter<BestTrading.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<BestTradingAdapter> faqModels;
    boolean ischecked=false;

    public BestTrading(Context context, ArrayList<BestTradingAdapter> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.best_trader, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BestTradingAdapter faqModel = faqModels.get(position);
        holder.txt_name.setText(faqModel.getShopeName());
        holder.txx.setText(faqModel.getTraderInfo());

//        if (faqModel.getShopeLogo().equals("")||faqModel.getShopeLogo().equals(null)){
//            Picasso.with(context)
//                    .load(URLS.imageBase+faqModel.getShopeLogo())
//                    .placeholder(R.drawable.image1)
//                    .error(R.drawable.image1)
//                    .into(holder.logoID);
//        }
//        else {
//            Picasso.with(context).load(URLS.imageBase+faqModel.getShopeLogo()).into(holder.logoID);
//        }
        Picasso.with(context)
                .load(URLS.imageBase+faqModel.getShopeLogo())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.logoID);


        holder.lin_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,MatgerDetailss.class);
                intent.putExtra("matgerId",faqModel.getTraderId());
                context.startActivity(intent);
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
        RelativeLayout lin_clicked;
       TextView txt_name,txx;
        /* private final TextView lawer_faq_qu,answer;
                private final LinearLayout lin_ques,aswerLin;*/
        ImageView logoID;
        public ViewHolder(View itemView) {
            super(itemView);
            lin_clicked =(RelativeLayout) itemView.findViewById(R.id.lin_clicked);
            logoID =(ImageView) itemView.findViewById(R.id.logoID);
            txt_name =(TextView) itemView.findViewById(R.id.txt_name);
            txx =(TextView) itemView.findViewById(R.id.txx);

        }
    }
}
