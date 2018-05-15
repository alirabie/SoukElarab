package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class AllTrader extends RecyclerView.Adapter<AllTrader.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<BestTradingAdapter> faqModels;
    boolean ischecked=false;

    public AllTrader(Context context, ArrayList<BestTradingAdapter> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.all_matger_item, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BestTradingAdapter faqModel = faqModels.get(position);
        holder.name.setText(faqModel.getShopeName());
        holder.des.setText(faqModel.getTraderInfo());
        holder.proNum.setText(faqModel.getProduct() + " منتج فى المتجر");
        holder.loc.setText(faqModel.getAddress());

        Picasso.with(context)
                .load(URLS.imageBase+faqModel.getShopeLogo())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.ques_image);

        holder.ques_image.setOnClickListener(new View.OnClickListener() {
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
       private final TextView name,des,proNum,loc ;
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            des= itemView.findViewById(R.id.des);
            proNum= itemView.findViewById(R.id.proNum);
            loc= itemView.findViewById(R.id.loc);
            ques_image= itemView.findViewById(R.id.ques_image);



        }
    }
}
