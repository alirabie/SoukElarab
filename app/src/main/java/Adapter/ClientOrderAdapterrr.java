package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import Model.ClientOrderModel;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class ClientOrderAdapterrr extends RecyclerView.Adapter<ClientOrderAdapterrr.ViewHolder> {


    private final SharedPreferences preferencesid;
    private final String user_id;
    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<ClientOrderModel> faqModels;
    boolean ischecked=false;
    int numberOfPro=0;
    public ClientOrderAdapterrr(Context context, ArrayList<ClientOrderModel> faqModels) {
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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_item, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       final ClientOrderModel faqModel = faqModels.get(position);
       holder.des.setText(faqModel.getDes());
       holder.name.setText(faqModel.getName());
       holder.proNum.setText(faqModel.getNumber());
       holder.price.setText(faqModel.getPrice() + " SAR ");

        Picasso.with(context)
                .load(URLS.imageBase+faqModel.getImage())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.ques_image);


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
       private final TextView name,price,des,proNum;
        com.whinc.widget.ratingbar.RatingBar ratingBar;
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);
            ques_image = itemView.findViewById(R.id.ques_image);
            des=itemView.findViewById(R.id.des);
            name=itemView.findViewById(R.id.name);
            proNum=itemView.findViewById(R.id.proNum);
            price=itemView.findViewById(R.id.price);
            ratingBar=itemView.findViewById(R.id.ratingBar);


        }
    }


}
