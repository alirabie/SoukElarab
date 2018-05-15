package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Model.BestTradingAdapter;
import Model.Model;
import URLS.URLS;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<Model> faqModels;
    boolean ischecked=false;

    public ImagesAdapter(Context context, ArrayList<Model> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.images_item, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Model faqModel = faqModels.get(position);

        Picasso.with(context).load(URLS.imageBase+faqModel.getimage()).into(holder.image);

    }
    @Override
    public int getItemCount() {
        return faqModels.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);

            image= itemView.findViewById(R.id.image);



        }
    }
}
