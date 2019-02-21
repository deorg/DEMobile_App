package com.domestic.dmobile.util;

import java.text.DecimalFormat;

public class FormatUtil {



    public static String doubleTo(double num) {
        return "";
    }


    //region #----- Formatting ----------
    public static String format(String str) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        String result = decimalFormat.format(str);
        return result;
    }
    //endregion
}
