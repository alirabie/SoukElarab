package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.whinc.widget.ratingbar.RatingBar;

import java.util.ArrayList;
import java.util.List;

import ConstantClasss.Custom_Textview;
import Model.CommentModel;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<CommentModel> faqModels;
    boolean ischecked=false;

    public CommentAdapter(Context context, ArrayList<CommentModel> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_lay, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      CommentModel commentModel = faqModels.get(position);
      holder.name.setText(commentModel.getName());
      holder.comm.setText(commentModel.getComment());
      holder.rate.setCount(Integer.valueOf(commentModel.getRating()));

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
        ImageView ques_image;
        RatingBar rate;
        Custom_Textview name,comm;
        public ViewHolder(View itemView) {
            super(itemView);
             rate = itemView.findViewById(R.id.ratingCount);
            name = itemView.findViewById(R.id.name);
             comm = itemView.findViewById(R.id.comm);

        }
    }
}
