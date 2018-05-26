package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Categore;
import souk.arab.com.soukelarab.R;


public class spin_adaptership extends ArrayAdapter<Categore> {


    private final Context context;
    private final List<Categore> all_offers;
    Typeface tf ;

    public spin_adaptership(Context context, List<Categore> all_offers) {

        super(context, R.layout.sp_school_item, all_offers);

        this.context = context;
        this.all_offers = all_offers;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }


    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
//       tf = Typeface.createFromAsset(context.getAssets(), "fonts/AraHamahSahetAlAssi-Regular.ttf");
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.sp_school_item, parent, false);
        TextView label=(TextView)row.findViewById(R.id.fieldSpinnerItem);
        if(position ==0){
            //label.setCompoundDrawablesWithIntrinsicBounds( R.drawable.arrow_down, 0, 0, 0);
        }
       label.setText(all_offers.get(position).getTitle());
       /* Log.e("nameee",all_offers.get(position).getUser().getName());*/
       // label.setTypeface(tf);*/

        return row;
    }
}

