package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

import Model.RequestsModel;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class AllRecyclePro extends RecyclerView.Adapter<AllRecyclePro.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<RequestsModel> faqModels;
    boolean ischecked=false;

    public AllRecyclePro(Context context, ArrayList<RequestsModel> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.all_pro, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        RequestsModel faqModel = faqModels.get(position);

//        holder.likeButton.setOnLikeListener(new OnLikeListener() {
//            @Override
//            public void liked(LikeButton likeButton) {
//                likeButton.setLiked(true);
//            }
//
//            @Override
//            public void unLiked(LikeButton likeButton) {
//                likeButton.setLiked(false);
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return 6;
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       /* private final TextView lawer_faq_qu,answer;
        private final LinearLayout lin_ques,aswerLin;*/
        LikeButton likeButton;
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);
         //   likeButton=(LikeButton) itemView.findViewById(R.id.star_button);


        }
    }
}
