package Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import Model.Driver;
import Model.Drivers;
import Model.RequestsModel;
import Presenter.ConstantMethods;
import souk.arab.com.soukelarab.R;


/**
 * Created by ahmed on 11/18/2017.
 */

public class FavouritAdapter extends RecyclerView.Adapter<FavouritAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;
    public static   int num;
    Context context;
ConstantMethods constantMethods;
    List<Driver> driverslist;
    boolean ischecked=false;

    public FavouritAdapter(Context context,  List<Driver> driverslist) {
        this.context = context;
        this.driverslist = driverslist;
    }
    public interface OnItemClickListener {
        void onclick(int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        constantMethods = new ConstantMethods(context);
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.favourititem, parent, false));
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Driver model = driverslist.get(position);
        holder.txtClientName.setText(model.getUsername());
        holder.txtlocationname.setText(model.getAddress());
        if (model.getOnline().equals("0")){
            holder.txtStatusnum.setText("غير متاح");
        }else {
            holder.txtStatusnum.setText("متاح");
        }
//
        holder.imgfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constantMethods.addfavouritsupplier(100,model.getDriverId());
            }
        });
        holder.btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + model.getPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return driverslist.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtClientName,txtdisc,txtdate,txtStatusnum,txtlocationname;
        Button btncall;
        // private final LinearLayout lin_ques,aswerLin;
        ImageView ques_image,imgfav;
        public ViewHolder(View itemView) {
            super(itemView);
            btncall = itemView.findViewById(R.id.btncall);
            txtStatusnum = itemView.findViewById(R.id.txtStatusnum);
            txtlocationname = itemView.findViewById(R.id.txtlocationname);
            txtClientName = itemView.findViewById(R.id.txtClientName);
            imgfav = itemView.findViewById(R.id.imgfav);
        }
    }
}

