package com.example.blackjackgame.ui.interfaceClick;

import android.widget.CheckBox;

import com.example.blackjackgame.rModel.coins.MoneyTransfer;

public interface CheckClick {

    void onClick(Boolean active, CheckBox last, CheckBox current, MoneyTransfer model);

}
