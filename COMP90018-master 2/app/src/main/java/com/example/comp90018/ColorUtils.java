package com.example.comp90018;

/**
 * Created by acer-pc on 2016/4/28.
 */
public class ColorUtils {

    public static int getColorFromStr(String s){
        int colorId = 0;
        switch (s) {
            case "moren":
                colorId = R.color.moren;
                break;
            case "green":
                colorId = R.color.green;
                break;
            case "yellow":
                colorId = R.color.yellow;
                break;
            case "red":
                colorId = R.color.red;
                break;
            case "gray":
                colorId = R.color.gray;
                break;
            case "orange":
                colorId = R.color.orange;
                break;
            case "blue":
                colorId = R.color.blue;
                break;
        }
        return colorId;
    }
}
