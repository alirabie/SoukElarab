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

import Model.Notifications;
import Model.Notus;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class NotiicationAdapter extends RecyclerView.Adapter<NotiicationAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    ArrayList<Notus> faqModels;
    boolean ischecked=false;

    public NotiicationAdapter(Context context, ArrayList<Notus> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Notus faqModel = faqModels.get(position);
        holder.txtlocatin.setText(faqModel.getAddress());
        holder.txtName.setText(faqModel.getUsername());
        if (faqModel.getImage().equals("")||faqModel.getImage().equals(null)){
            Picasso.with(context)
                    .load(URLS.imageBase+faqModel.getImage())
                    .placeholder(R.drawable.image1)
                    .error(R.drawable.image1)
                    .into(holder.ques_image);
        }
        else {
            Picasso.with(context).load(URLS.imageBase+faqModel.getImage()).into(holder.ques_image);
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
        private final TextView txttitle,txtName,txtlocatin,txttime;
       // private final LinearLayout lin_ques,aswerLin;*/*/
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);
            txttitle= itemView.findViewById(R.id.txttitle);
            txtName =itemView.findViewById(R.id.txtName);
            txtlocatin= itemView.findViewById(R.id.txtlocatin);
            txttime =itemView.findViewById(R.id.txttime);
            ques_image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}
