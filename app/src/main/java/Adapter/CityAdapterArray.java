package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import Model.LookupModel;
import souk.arab.com.soukelarab.R;


/**
 * Created by IDotmasr on 3/14/2018.
 */

public class CityAdapterArray extends ArrayAdapter<LookupModel> {


    private final Context context;
    private final List<LookupModel> lookupModels;
    Typeface tf ;

    public CityAdapterArray(Context context, List<LookupModel> lookupModels) {

        super(context, R.layout.item_sp, lookupModels);

        this.context = context;
        this.lookupModels = lookupModels;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
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
        View row=inflater.inflate(R.layout.item_sp, parent, false);
        TextView label=(TextView)row.findViewById(R.id.fieldSpinnerItem);
        label.setText(lookupModels.get(position).getName());

        return row;
    }
}

