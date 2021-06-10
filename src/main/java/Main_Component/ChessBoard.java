package Main_Component;

import main.java.Piece_Properties.Color;
import main.java.Piece_Properties.Type;

import java.awt.*;

public class ChessBoard {
    //Info Essential
    public final int rows;
    public final int columns;
    public ChessPiece[][] tile; // ChessBoard is a 2-dimension of ChessPiece

    public Color playerTurn ;
    public boolean Over ;

    public boolean red_in_checked ;
    public boolean black_in_checked;

    public ChessPiece BlackGeneral;
    public ChessPiece RedGeneral;

    public boolean stalemate ;

    public ChessBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.tile = new ChessPiece[rows][columns];
        createNewBoard();
        updateAttackTiles();
        updateValidTiles();
    }

    public ChessBoard(ChessBoard other){ //Copy Other ChessBoard
        this.rows = other.rows;
        this.columns = other.columns;
        this.tile = new ChessPiece[rows][columns];
        for(int i=0;i<rows;++i)
            for(int j=0;j<columns;++j){
                ChessPiece otherChess = other.tile[i][j];
                if(otherChess == null) tile[i][j] = null;
                else {
                    tile[i][j] = new ChessPiece(otherChess);
                    if(otherChess.type == Type.GENERAL){
                        if(otherChess.color == Color.BLACK){
                            BlackGeneral = tile[i][j];
                        } else RedGeneral = tile[i][j];
                    }
                }
            }

        this.playerTurn = other.playerTurn;
        this.red_in_checked = other.red_in_checked;
        this.black_in_checked = other.black_in_checked;
        this.Over = other.Over;
        this.stalemate = other.stalemate;

    }

    public void updateAttackTiles(){
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                ChessPiece currentPiece = tile[row][column];
                if (currentPiece != null)
                    currentPiece.moves.setAttackTiles(this, currentPiece);
            }
        }
    }

    public void updateValidTiles(){
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                ChessPiece currentPiece = tile[row][column];
                if (currentPiece != null)
                    currentPiece.moves.setValidTiles(this, currentPiece);
            }
        }
    }

    public boolean pieceExist(Point point){
        if(!tileExist(point)) return false;
        return PieceColor(point) != Color.NONE;
    }

    public boolean tileExist(Point point){
        if(point.x < 0) return false;
        if(point.y < 0) return false;
        if(point.x >= rows) return false;
        return point.y < columns;
    }

    public Color PieceColor(Point pos){
        if(!tileExist(pos)) return null;
        if(tile[pos.x][pos.y] == null) return Color.NONE;
        return tile[pos.x][pos.y].color;
    }

    public void updateAttackTilesOneColor(Color color){
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                ChessPiece currentPiece = tile[row][column];
                if (currentPiece != null && currentPiece.color == color)
                    currentPiece.moves.setAttackTiles(this, currentPiece);
            }
        }
    }

    public void movePiece(ChessPiece piece, Point newPoint){
        tile[newPoint.x][newPoint.y] = tile[piece.pos.x][piece.pos.y];
        tile[piece.pos.x][piece.pos.y] = null;

        piece.pos = newPoint;

        //Soldier change to unlocked type
        if(piece.type == Type.SOLDIER){
            if(piece.color == Color.RED && piece.pos.x == 5) piece.type = Type.UNLOCKED;
            if(piece.color == Color.BLACK && piece.pos.x == 4) piece.type = Type.UNLOCKED;
        }
        updateAttackTiles();
        updateInChecked();
    }

    public void updateBoard(){
        updateAttackTiles();
        updateInChecked();
        updateTurn();
        updateValidTiles();
    }

    public void updateTurn(){
        if(playerTurn == Color.RED) playerTurn = Color.BLACK;
        else playerTurn = Color.RED;
    }

    public void updateInChecked(){
        updateBlackInCheck();
        updateRedInCheck();
    }

    public void updateRedInCheck(){
        /* Loop through board */
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                /* If any piece is threatening white's king, player is in check */
                ChessPiece otherPiece = tile[row][column];
                if (otherPiece != null && otherPiece.color == Color.BLACK){
                    for (Point attackingPosition : otherPiece.moves.attackTiles){
                        if (attackingPosition.equals(RedGeneral.pos)){	//can't use " == " here since it compares addresses
                            red_in_checked = true;
                            return;
                        }
                    }
                }
            }
        }
        red_in_checked = false;
    }

    /**
     * Determines if BLACK is in check. Updates boolean variable blackInCheck
     */
    public void updateBlackInCheck(){
        ChessPiece otherPiece;
        /* Loop through board */
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                /* If any piece is threatening black's king, player is in check */
                otherPiece = tile[row][column];
                if (otherPiece != null && otherPiece.color == Color.RED){
                    for (Point attackingPosition : otherPiece.moves.attackTiles){
                        if (attackingPosition.equals(BlackGeneral.pos)){	//can't use " == " here since it compares addresses
                            black_in_checked = true;
                            return;
                        }
                    }
                }
            }
        }
        black_in_checked = false;
    }

    private void createNewBoard(){
        //Create Null Tile
        for(int i=0;i<=9;++i)
            for(int j=0;j<=8;++j) tile[i][j] = null;
        // Create Red Side
        createBackRow(0,Color.RED);
        createCannon(2,Color.RED);
        createSoldier(3,Color.RED);
        // Create Black Side
        createBackRow(9,Color.BLACK);
        createCannon(7,Color.BLACK);
        createSoldier(6,Color.BLACK);

        this.BlackGeneral = new ChessPiece(tile[9][4]);
        this.RedGeneral = new ChessPiece(tile[0][4]);

        this.playerTurn = Color.RED;
        this.red_in_checked = false;
        this.black_in_checked = false;
        this.Over = false;
        this.stalemate = false;

    }

    private void createBackRow(int row,Color color){
        tile[row][0] = new ChessPiece(Type.CHARIOT,color,new Point(row,0));
        tile[row][8] = new ChessPiece(Type.CHARIOT,color,new Point(row,8));
        tile[row][1] = new ChessPiece(Type.HORSE,color,new Point(row,1));
        tile[row][7] = new ChessPiece(Type.HORSE,color,new Point(row,7));
        tile[row][2] = new ChessPiece(Type.ELEPHANT,color,new Point(row,2));
        tile[row][6] = new ChessPiece(Type.ELEPHANT,color,new Point(row,6));
        tile[row][3] = new ChessPiece(Type.ADVISOR,color,new Point(row,3));
        tile[row][5] = new ChessPiece(Type.ADVISOR,color,new Point(row,5));
        tile[row][4] = new ChessPiece(Type.GENERAL,color,new Point(row,4));
    }

    private void createCannon(int row,Color color){
        tile[row][1] = new ChessPiece(Type.CANNON,color,new Point(row,1));
        tile[row][7] = new ChessPiece(Type.CANNON,color,new Point(row,7));
    }

    private void createSoldier(int row, Color color){
        tile[row][0] = new ChessPiece(Type.SOLDIER,color,new Point(row,0));
        tile[row][2] = new ChessPiece(Type.SOLDIER,color,new Point(row,2));
        tile[row][4] = new ChessPiece(Type.SOLDIER,color,new Point(row,4));
        tile[row][6] = new ChessPiece(Type.SOLDIER,color,new Point(row,6));
        tile[row][8] = new ChessPiece(Type.SOLDIER,color,new Point(row,8));
    }

}
