package com.example.blackjackgame.util;

import java.util.Calendar;

public class ConvertDate {

    public static String convertTimeToDate(long time){

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long)(time) * 1000);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        return ConvertDate.setNullInInt(mDay) + "." + ConvertDate.setNullInInt(mMonth) + "." + mYear;
    }

    private static String setNullInInt(int k){
        return k < 10 ? "0" + k : String.valueOf(k);
    }

}
