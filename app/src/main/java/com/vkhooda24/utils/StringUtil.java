package com.vkhooda24.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Vikram Hooda on 12/25/18.
 */
public class StringUtil {

    public static String formatNumberWithCommas(String number) {
        try {
//            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
//            return numberFormat.format(number);
            return String.format("%,d", Integer.valueOf(number));
        } catch (Exception e) {
            return number;
        }
    }
}
