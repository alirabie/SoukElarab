package ConstantClasss;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by afaf.elshafey on 6/15/2016.
 */
public class Custom_EditText extends EditText {
    public Custom_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cairofont.ttf"));

    }
}
