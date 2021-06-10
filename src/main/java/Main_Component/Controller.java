package Main_Component;

import Main_Component.ChessBoard;

public class Controller {

    public ChessBoard chessBoard;

    public Controller(){
        initializeVariables();
    }

    public void initializeVariables(){
        this.chessBoard = new ChessBoard(10, 9);
    }
}
