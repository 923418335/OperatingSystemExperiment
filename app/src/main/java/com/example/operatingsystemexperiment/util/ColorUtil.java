package com.example.operatingsystemexperiment.util;

import com.example.operatingsystemexperiment.R;

/**
 * Created by 山东娃 on 2016/3/13.
 */
public class ColorUtil {
    private static final int[] colors;
    static {
        colors = new int[11];
        colors[0] = R.color.lawngreen;
        colors[1] = R.color.gold;
        colors[2] = R.color.cadetblue;
        colors[3] = R.color.darkblue;
        colors[4] = R.color.chocolate;
        colors[5] = R.color.firebrick;
        colors[6] = R.color.tan;
        colors[7] = R.color.chocolate;
        colors[8] = R.color.lemonchiffon;
        colors[9] = R.color.navajowhite;
        colors[10] = R.color.peru;
    }
    public static int getColor(int index){
        return colors[index % 11];
    }
}
