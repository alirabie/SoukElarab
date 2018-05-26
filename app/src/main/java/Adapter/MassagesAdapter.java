package Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import Model.ChatModel;
import Model.ChateModelList;
import URLS.URLS;
import souk.arab.com.soukelarab.Chat;
import souk.arab.com.soukelarab.MatgerDetailss;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class MassagesAdapter extends RecyclerView.Adapter<MassagesAdapter.ViewHolder> {


    private final SharedPreferences preferencesid;
    private final String user_id;
    private final SharedPreferences.Editor edit;
    OnItemClickListener onItemClickListener;
    public static int num;
    Context context;
    List<ChatModel> chateModelLists;
    boolean ischecked = false;


    boolean setInFav = false;
    boolean setIncart = false;


    int cart;

    public MassagesAdapter(Context context, ArrayList<ChatModel> chateModelLists) {
        AndroidNetworking.initialize(context);
        preferencesid = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id", "0000");
        edit = preferencesid.edit();
        this.context = context;
        this.chateModelLists = chateModelLists;
    }

    public interface OnItemClickListener {
        void onclick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        long timeStamp = chateModelLists.get(holder.getAdapterPosition()).getTimeStamp();
        holder.tvMessage.setText(chateModelLists.get(holder.getAdapterPosition()).getText());
        Date date = new Date(timeStamp*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy "); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String format = sdf.format(date);

        holder.tvTime.setText(format);
    //    holder.tvTime.setText(""+chateModelLists.get(holder.getAdapterPosition()).getTimeStamp());
        Picasso.with(context)
                .load(URLS.imageBase+chateModelLists.get(holder.getAdapterPosition()).getPhoto())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.photo);

        if (chateModelLists.get(holder.getAdapterPosition()).getFrom().equals(user_id)) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.END;
            params.setMargins(40,10,40,10);
            holder.liFrame.setLayoutParams(params);
            holder.liFrame.setBackgroundResource(R.drawable.user_shape);
        }else{
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.START;
            params.setMargins(40,10,40,10);
            holder.liFrame.setLayoutParams(params);
            holder.liFrame.setBackgroundResource(R.drawable.sender_shape);
        }
    }

    @Override
    public int getItemCount() {
        return chateModelLists.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* private final TextView lawer_faq_qu,answer;
         private final LinearLayout lin_ques,aswerLin;*/
        private final TextView tvMessage,tvTime;
        com.whinc.widget.ratingbar.RatingBar ratingBar;

        ImageView photo, like, share;
        FrameLayout liFrame;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
            photo = itemView.findViewById(R.id.photo);
            liFrame = itemView.findViewById(R.id.liFrame);


        }
    }


}
