package com.example.blackjackgame.ui.interfaceClick;

import com.example.blackjackgame.rModel.news.News;

public interface INewsClick {

    void onClick(News news);
    void onAddFriend(int position, int type, int userId);
    void onDelFriend(int position, int type, int userId);
    void onApplyGame(int position, int type, int gameId);
    void onCancelGame(int position, int type, int gameId);
    void onTransferMonet();
    void onTransferMoney();

}
