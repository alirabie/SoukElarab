package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Model.Department;
import Model.RequestsModel;
import URLS.URLS;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import souk.arab.com.soukelarab.MatgerDetaise;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;

    List<Department> faqModels;
    boolean ischecked=false;

    public DepartmentAdapter(Context context, ArrayList<Department> faqModels) {
        this.context = context;
        this.faqModels = faqModels;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.department_iitem, parent, false));

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        RequestsModel faqModel = faqModels.get(position);
        int raduis=50;
        int margin=3;
        final Department department = faqModels.get(position);
        holder.txt_dept.setText(department.getName());




        if (department.getImage().equals("")||department.getImage().equals(null)){
            Picasso.with(context)
                    .load(URLS.imageBase+department.getImage())
                    .placeholder(R.drawable.grop)
                    .error(R.drawable.grop)
                    .into(holder.imageView);
        }
        else {
            final Transformation transformation = new RoundedCornersTransformation(raduis, margin);
            Picasso.with(context).load(URLS.imageBase+department.getImage()).transform(transformation).into(holder.imageView);
       //     Picasso.with(context).load(URLS.imageBase+department.getImage()).into(holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, MatgerDetaise.class);
                intent.putExtra("cat_id",department.getCatId());
                intent.putExtra("name",department.getName());
                context.startActivity(intent);


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
        private final ImageView imageView;
        private final TextView txt_dept;
        /* private final TextView lawer_faq_qu,answer;
                private final LinearLayout lin_ques,aswerLin;*/
        ImageView ques_image;
        public ViewHolder(View itemView) {
            super(itemView);
             imageView = (ImageView)itemView.findViewById(R.id.imageView);
            txt_dept = (TextView)itemView.findViewById(R.id.txt_dept);

        }
    }
}
