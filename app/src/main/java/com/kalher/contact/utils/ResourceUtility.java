package com.kalher.contact.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.core.content.res.ResourcesCompat;

import com.kalher.contact.R;
import com.kalher.contact.base.Contact;

public class ResourceUtility {

    public static int getRandomColor(int position){
        int[] colors  = new int[6];
        colors[0] = ResourceUtility.getColor(R.color.color_blue);
        colors[1] = ResourceUtility.getColor(R.color.color_red);
        colors[2] = ResourceUtility.getColor(R.color.color_yellow);
        colors[3] = ResourceUtility.getColor(R.color.color_green);
        colors[4] = ResourceUtility.getColor(R.color.color_purple);
        colors[5] = ResourceUtility.getColor(R.color.color_orange);

        return colors[position%colors.length];
    }

    public static int getColor(int colorResId) {
        return ResourcesCompat.getColor(Contact.getsApplicationContext().getResources(), colorResId, null);
    }

    public static Drawable getDrawable(int drawableResId){
        return ResourcesCompat.getDrawable(Contact.getsApplicationContext().getResources(), drawableResId, null);
    }

}
