package ConstantClasss;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Created by ahmed on 5/12/2018.
 */

public class CustomRadio extends RadioButton {


    public CustomRadio(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/cairofont.ttf"));
    }
}
