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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.LookupModel;
import Model.RequestsModel;
import souk.arab.com.soukelarab.R;
import souk.arab.com.soukelarab.Requestdetails;


/**
 * Created by ahmed on 11/18/2017.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
RelativeLayout relall;
    List<LookupModel> faqModels;
    boolean ischecked=false;

    public CarAdapter(Context context, ArrayList<LookupModel> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sp, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    LookupModel faqModel = faqModels.get(position);

holder.fieldSpinnerItem.setText(faqModel.getName());


holder.fieldSpinnerItem.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onclick(position);
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
       /* private final TextView lawer_faq_qu,answer;
        private final LinearLayout lin_ques,aswerLin;*/
        TextView fieldSpinnerItem;

        public ViewHolder(View itemView) {
            super(itemView);
            fieldSpinnerItem=(TextView)itemView.findViewById(R.id.fieldSpinnerItem);

        }
    }
}
