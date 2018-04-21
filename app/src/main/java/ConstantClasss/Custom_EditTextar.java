package ConstantClasss;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by afaf.elshafey on 6/15/2016.
 */
public class Custom_EditTextar extends EditText {
    public Custom_EditTextar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/beirut.ttf"));

    }
}
