package Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.RequestsModel;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.Requestdetails;


/**
 * Created by ahmed on 11/18/2017.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
RelativeLayout relall;
    List<RequestsModel> faqModels;
    boolean ischecked=false;

    public RequestsAdapter(Context context, ArrayList<RequestsModel> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.requestsitems, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        relall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onclick(position);
                    Toast.makeText(context, "tesst", Toast.LENGTH_SHORT).show();
               //    startActivity(context,Requestdetails.class);

                    Intent intent=new Intent(context,Requestdetails.class);
                    context.startActivity(intent);

                }
            }
        });
//        RequestsModel faqModel = faqModels.get(position);
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

        public ViewHolder(View itemView) {
            super(itemView);
             relall=(RelativeLayout)itemView.findViewById(R.id.relall);

        }
    }
}
