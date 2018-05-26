package Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.ChateModelList;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.Chat;
import souk.arab.com.soukelarab.ProuductDetails;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class AllChatAdapter extends RecyclerView.Adapter<AllChatAdapter.ViewHolder> {


    private final SharedPreferences preferencesid;
    private final String user_id;
    private final SharedPreferences.Editor edit;
    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
    List<ChateModelList> chateModelLists;
    boolean ischecked=false;


     boolean setInFav=false;
     boolean setIncart=false;


    int cart;
    public AllChatAdapter(Context context, ArrayList<ChateModelList> chateModelLists) {
        AndroidNetworking.initialize(context);
        preferencesid = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
      edit = preferencesid.edit();
        this.context = context;
        this.chateModelLists = chateModelLists;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.chate_item_all, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ChateModelList chateModelList = chateModelLists.get(position);
        holder.name.setText(chateModelList.getMatjarName());


        Picasso.with(context)
                .load(URLS.imageBase+chateModelList.getMatjarImage())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.ques_image);
holder.chate.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context, Chat.class);
        intent.putExtra("userId",chateModelList.getUserId());
        intent.putExtra("matgerId",chateModelList.getMatjarId());
        intent.putExtra("photo",chateModelList.getMatjarImage());
        context.startActivity(intent);
    }
});

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
       private final TextView name;
       com.whinc.widget.ratingbar.RatingBar ratingBar;

        ImageView ques_image,like,share;
        LinearLayout chate;
        public ViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            ques_image=itemView.findViewById(R.id.ques_image);
            chate=itemView.findViewById(R.id.chate);


        }
    }


}
