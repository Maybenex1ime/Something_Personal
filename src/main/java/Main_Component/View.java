package Main_Component;

import javax.swing.*;

public class View {

    public ChessBoard chessBoard; // The view has access to the ChessBoard
    public Controller controller; // The view has access to the Controller
    public JFrame frame ;
    public JPanel panel ; // Will add 81 PointMove
    public PointMove[][] pointMoves;

    private final int frameHeight = 739;
    private final int frameWidth = 917;
    private final int boardWidth = 600;
    private final int squareIconLength = 75;

    public View(ChessBoard chessBoard, Controller controller){
        frame = new JFrame("Chess");
        initialize(chessBoard,controller);
    }

    public void initialize(ChessBoard chessBoard,Controller controller){
        this.chessBoard = chessBoard;
        this.controller = controller;

        panel = new JPanel(null);
        for(int i=0;i<chessBoard.rows;++i)
            for(int j=0;j<chessBoard.columns;++j){
                pointMoves[i][j] = new PointMove(j,i);
            }
        frame.setSize(frameWidth,frameHeight);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int i=0;i<chessBoard.rows;++i)
            for(int j=0;j<chessBoard.columns;++j){
                PointMove currentPointMove = pointMoves[i][j];
                ChessPiece chessPieceCurrent = chessBoard.tile[i][j];
                if(chessPieceCurrent !=null){
                    currentPointMove.setIcon(chessPieceCurrent.imageIcon);
                }
            }
    }
}
