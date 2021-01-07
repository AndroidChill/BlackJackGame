package com.example.blackjackgame.data;

import java.util.Locale;

public class Constant {

    public static int app_ver = 20201105;
    public static String ln = Locale.getDefault().getCountry();

    //статусы
    public static String success = "success";
    public static String error = "error";

    //описание ошибок
    public static String failed_token = "failed_token";


    //вошел пользователь или нет
    public static String isSign = "isSign";

    //выбор типа турнира
    public static String isChooseTypeTournament = "isChooseTypeTournament";

    //скрыта ли подсказка
    public static String isShowHintRating = "isShowHintRating";

    //было ли выполнено задание
    public static String isCompleteTask = "isCompleteTask";
    public static String idCompleteTask = "idCompleteTask";

    //тип рекламы при показе
    public static String typeAdsForLoadGame = "typeAdsForLoadGame";

}
