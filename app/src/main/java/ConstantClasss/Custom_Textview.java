package ConstantClasss;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by afaf.elshafey on 6/15/2016.
 */
public class Custom_Textview extends TextView {
    public Custom_Textview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cairofont.ttf"));
    }
}
