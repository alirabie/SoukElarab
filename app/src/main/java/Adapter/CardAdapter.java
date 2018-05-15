package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    private final SharedPreferences preferencesid;
    private final String user_id;
    OnItemClickListener onItemClickListener;
    OnItemClickListenermius onItemClickListenermius;
    public static   int num;
    Context context;

    List<RequestsModel> faqModels;
    public  static boolean ischecked=false;
    int numberOfPro=1;
    public CardAdapter(Context context, ArrayList<RequestsModel> faqModels) {
        AndroidNetworking.initialize(context);
        this.context = context;
        this.faqModels = faqModels;


        preferencesid = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    user_id = preferencesid.getString("user_id","0000");
}
    public interface OnItemClickListener {
        void onclick(int position);
    } public interface OnItemClickListenermius {
        void onclickked(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       final RequestsModel faqModel = faqModels.get(position);
       holder.des.setText(faqModel.getDescription());
       holder.quanNum.setText(faqModel.getQuantity());
       holder.price.setText(faqModel.getPrice() + " SAR ");

//        if (faqModel.getDefImg().equals("")||faqModel.getDefImg().equals(null)){
//            Picasso.with(context)
//                    .load(URLS.imageBase+faqModel.getDefImg())
//                    .placeholder(R.drawable.image1)
//                    .error(R.drawable.image1)
//                    .into(holder.image);
//        }
//        else {
//            Picasso.with(context).load(URLS.imageBase+faqModel.getDefImg()).into(holder.image);
//        }
        Picasso.with(context)
                .load(URLS.imageBase+faqModel.getDefImg())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.image);


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(position);

                    numberOfPro=Integer.parseInt(holder.quanNum.getText().toString())+1;
                    holder.quanNum.setText(""+numberOfPro);
                    updateCart(faqModel.getProductId(),numberOfPro);
                }
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListenermius != null) {
                    if (numberOfPro==1){
                        holder.quanNum.setText("1");
                    }else {

                        numberOfPro=Integer.parseInt(holder.quanNum.getText().toString())-1;
                        holder.quanNum.setText(""+numberOfPro);
                        updateCart(faqModel.getProductId(),numberOfPro);
                    }

                }
            }
        });
        holder.updatecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCart(faqModel.getProductId(),numberOfPro);
            }
        });
    }
    @Override
    public int getItemCount() {
        return faqModels.size();
    }
    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    } public void setOnClickListenerMia(OnItemClickListenermius onClickListenerMi) {
        this.onItemClickListenermius = onClickListenerMi;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       /* private final TextView lawer_faq_qu,answer;
        private final LinearLayout lin_ques,aswerLin;*/
       private final TextView des,price,quanNum;
        com.whinc.widget.ratingbar.RatingBar ratingBar;
        ImageView image,updatecard,plus,minus;
        public ViewHolder(View itemView) {
            super(itemView);
             image = itemView.findViewById(R.id.image);
            updatecard = itemView.findViewById(R.id.updatecard);
            plus = itemView.findViewById(R.id.plus);
            des=itemView.findViewById(R.id.des);
            price=itemView.findViewById(R.id.price);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            quanNum=itemView.findViewById(R.id.quanNum);
            minus=itemView.findViewById(R.id.minus);
        }
    }
    // كارت
    public void updateCart(String prodID,int count){

        AndroidNetworking.post(URLS.updateCard)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",prodID)
                .addBodyParameter("count",String.valueOf(count))
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(context, "تم تحديد الكمية المطلوبة من هذا المنتج", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
