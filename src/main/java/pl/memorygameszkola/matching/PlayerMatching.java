package pl.memorygameszkola.matching;

import pl.memorygameszkola.stockOfCard.Player;

public class PlayerMatching {

    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;

    public void Player (String firstPlayerName, String secondPlayerName){
        firstPlayer = new Player("Gracz 1");
        secondPlayer = new Player("Gracz 2");
        currentPlayer = firstPlayer;
    }
    private Player getCurrentPlayer(){
        return currentPlayer;
    }
    public void switchTurn(){
        if(currentPlayer == firstPlayer){
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }
    }
}
