package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import Model.RequestsModel;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class ClientMessagesAdapter extends RecyclerView.Adapter<ClientMessagesAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<RequestsModel> faqModels;
    boolean ischecked=false;

    public ClientMessagesAdapter(Context context, ArrayList<RequestsModel> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.clientmessageitem, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        RequestsModel faqModel = faqModels.get(position);
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
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
