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
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.whinc.widget.ratingbar.RatingBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.MoreBuyerModel;
import Model.RequestsModel;
import URLS.URLS;
import souk.arab.com.soukelarab.MatgerDetaise;
import souk.arab.com.soukelarab.ProuductDetails;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class MoreBuyerAdapter extends RecyclerView.Adapter<MoreBuyerAdapter.ViewHolder> {

    private final SharedPreferences preferencesid;
    private final String user_id;
    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<MoreBuyerModel> faqModels;
    boolean ischecked=false;
    boolean setInFav=false;
    private boolean setIncart=false;

    public MoreBuyerAdapter(Context context, ArrayList<MoreBuyerModel> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
        AndroidNetworking.initialize(context);
        preferencesid = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        user_id = preferencesid.getString("user_id","0000");
       // edit = preferencesid.edit();
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.more_buyer, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MoreBuyerModel faqModel = faqModels.get(position);
        if (faqModel.getFav()==0){
            holder.like.setImageResource(R.drawable.im1);
        }else {
            holder.like.setImageResource(R.drawable.like);
        }
        holder.txt_name.setText(faqModel.getTitle());
        holder.price.setText(faqModel.getPrice()+ " ريال ");
        holder.txt_des.setText(faqModel.getDescription());
        holder.ratingBar.setCount(Integer.valueOf(faqModel.getRate()));

        Picasso.with(context)
                .load(URLS.imageBase+faqModel.getDefImg())
                .placeholder(R.drawable.placehodleer)
                .error(R.drawable.placehodleer)
                .into(holder.ques_image);

//        if (faqModel.getDefImg().equals("")||faqModel.getDefImg().equals(null)){
//            Picasso.with(context)
//                    .load(URLS.imageBase+faqModel.getDefImg())
//                    .placeholder(R.drawable.image1)
//                    .error(R.drawable.image1)
//                    .into(holder.ques_image);
//        }
//        else {
//            Picasso.with(context).load(URLS.imageBase+faqModel.getDefImg()).into(holder.ques_image);
//        }
        holder.ques_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProuductDetails.class);
                intent.putExtra("proID",faqModel.getProductId());
                context.startActivity(intent);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                context.startActivity(Intent.createChooser(sharingIntent,"Share using"));
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDeleteFav(faqModel.getProductId(),holder);
            }
        });

//        holder.like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addDeleteFav(faqModel.getProductId());
//                if (setInFav==true){
//                    holder.like.setImageResource(R.drawable.im1);
//
//                }
//                else   if (setInFav==false){
//                    holder.like.setImageResource(R.drawable.like);
//                }
//            }
//        });
//        holder.basket.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (setIncart==true){
//                    holder.basket.setImageResource(R.drawable.pasketacttive);
//
//                }
//                else   if (setIncart==false){
//                    holder.basket.setImageResource(R.drawable.cart);
//                }
//            }
//        });
        holder.basket.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    addTopasket(faqModel.getProductId(),faqModel.getTraderId());
                    holder.basket.setBackgroundResource(R.drawable.pasketacttive);
                }else if (b==false){
                    addTopasket(faqModel.getProductId(),faqModel.getTraderId());
                    holder.basket.setBackgroundResource(R.drawable.cart);
                }
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
      private final TextView txt_name, txt_des,price;
        private final RatingBar ratingBar;
        ImageView like,share;
        CheckBox basket;
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);

            txt_name=itemView.findViewById(R.id.txt_name);
            txt_des=itemView.findViewById(R.id.txt_des);
            price=itemView.findViewById(R.id.price);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            ques_image=itemView.findViewById(R.id.ques_image);


            like=itemView.findViewById(R.id.like);
            basket=itemView.findViewById(R.id.basket);
            share=itemView.findViewById(R.id.share);
        }
    }
    // المفضله
    public void addDeleteFav(String proID, final ViewHolder holder){

        AndroidNetworking.post(URLS.fav)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",proID)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){

                                setInFav=true;
                                Toast.makeText(context, "تم إضافة المنتج الى المفضلة بنجاح", Toast.LENGTH_SHORT).show();
                                holder.like.setImageResource(R.drawable.like);
                            }
                            else if (success.equals("0")){
                                setInFav=false;
                                Toast.makeText(context, "تم حذف المنتج من المفضلة بنجاح", Toast.LENGTH_SHORT).show();
                                holder.like.setImageResource(R.drawable.im1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    // المفضله
    public void addTopasket(String proID,String traderId){

        AndroidNetworking.post(URLS.delete_insert_basket)
                .addBodyParameter("client_id",user_id)
                .addBodyParameter("user_id",user_id)
                .addBodyParameter("product_id",proID)
                .addBodyParameter("trader_id",traderId)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            if (success.equals("1")){

                                setIncart=true;
                                Toast.makeText(context, "تم إضافة المنتج الى السلة بنجاح", Toast.LENGTH_SHORT).show();

                            }
                            else if (success.equals("0")){
                                setIncart=false;
                                Toast.makeText(context, "تم حذف المنتج من السلة بنجاح", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
