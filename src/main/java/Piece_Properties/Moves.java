package Piece_Properties;

import Main_Component.ChessBoard;
import Main_Component.ChessPiece;
import main.java.Piece_Properties.Type;
import main.java.Piece_Properties.Color;

import java.awt.Point;
import java.util.Vector;

public class Moves {
    public Vector<Point> attackTiles;
    public Vector<Point> validTiles;
    boolean hopped ; //boolean only for Cannon

    public Moves(){
        attackTiles = new Vector<>();
        validTiles = new Vector<>();
    }

    /**
     * Function set attack Tile for a single piece
     * @param board board
     * @param piece piece
     */
    
    public void setAttackTiles(ChessBoard board, ChessPiece piece){
        piece.moves.attackTiles.clear();
        if(piece.type == Type.ADVISOR){
            attackAdvisor(board, piece);
        }
        if(piece.type == Type.CANNON){
            hopped = false;
            attacksLeft(board,piece);
            hopped = false;
            attacksRight(board,piece);
            hopped = false;
            attacksDown(board,piece);
            hopped = false;
            attacksUp(board,piece);
        }
        if(piece.type == Type.CHARIOT){
            attacksLeft(board, piece);
            attacksRight(board, piece);
            attacksDown(board, piece);
            attacksUp(board, piece);
        }
        if(piece.type == Type.ELEPHANT){
            if(!board.pieceExist(new Point(piece.pos.x + 1,piece.pos.y + 1))) possiblePoint(board, piece,2,2); 	//NE = NorthEast
            if(!board.pieceExist(new Point(piece.pos.x - 1,piece.pos.y + 1))) possiblePoint(board, piece,-2,2);    //SE = SouthEast
            if(!board.pieceExist(new Point(piece.pos.x - 1,piece.pos.y - 1))) possiblePoint(board, piece,-2,-2);    //SW = SouthWest
            if(!board.pieceExist(new Point(piece.pos.x + 1,piece.pos.y - 1))) possiblePoint(board, piece,2,-2); // NW = NorthWest
        }
        if(piece.type == Type.HORSE){
            attacksHorse(board,piece);
        }
        if(piece.type == Type.GENERAL){
        attacksGeneral(board, piece);
        }
        if(piece.type == Type.SOLDIER){
        attacksSoldier(board, piece);
        }
        if(piece.type == Type.UNLOCKED){
            attacksUnlocked(board, piece);
        }
    }

    public void attacksLeft(ChessBoard board,ChessPiece piece){
        Color color = Color.NONE;
        for(int offset = -1; color == Color.NONE ; offset--) color = possiblePoint(board,piece,0,offset);
    }

    public void attacksRight(ChessBoard board,ChessPiece piece){
        Color color = Color.NONE;
        for(int offset = 1; color == Color.NONE ; offset++) color = possiblePoint(board,piece,0,offset);
    }

    public void attacksUp(ChessBoard board,ChessPiece piece){
        Color color = Color.NONE;
        for(int offset = 1; color == Color.NONE ; offset++) color = possiblePoint(board,piece,offset,0);
    }

    public void attacksDown(ChessBoard board,ChessPiece piece){
        Color color = Color.NONE;
        for(int offset = -1; color == Color.NONE ; offset--) color = possiblePoint(board,piece,offset,0);
    }


    public void attacksGeneral(ChessBoard chessBoard, ChessPiece piece){
        possiblePoint(chessBoard,piece,-1,0);
        possiblePoint(chessBoard,piece,1,0);
        possiblePoint(chessBoard,piece,0,1);
        possiblePoint(chessBoard,piece,0,-1);
        if(chessBoard.RedGeneral.pos.y == chessBoard.BlackGeneral.pos.y){
            for(int i=chessBoard.RedGeneral.pos.x+1;i< chessBoard.BlackGeneral.pos.x; ++i){
                if(chessBoard.pieceExist(new Point(i,chessBoard.RedGeneral.pos.y))) return;
            }
            if(piece.color == Color.RED) piece.moves.attackTiles.add(chessBoard.BlackGeneral.pos);
            if(piece.color == Color.BLACK) piece.moves.attackTiles.add(chessBoard.RedGeneral.pos);
        }
    }

    public void attackAdvisor(ChessBoard chessBoard, ChessPiece piece){
        possiblePoint(chessBoard,piece,-1,-1);
        possiblePoint(chessBoard,piece,1,1);
        possiblePoint(chessBoard,piece,1,-1);
        possiblePoint(chessBoard,piece,-1,1);
    }

    public void attacksHorse(ChessBoard board, ChessPiece piece){
        //Horse can be blocked
        if(!board.pieceExist(new Point(piece.pos.x + 1,piece.pos.y))){
            possiblePoint(board,piece,2,1);
             possiblePoint(board,piece,2,-1);
        }
        if(!board.pieceExist(new Point(piece.pos.x - 1,piece.pos.y))){
             possiblePoint(board,piece,-2,1);
             possiblePoint(board,piece,-2,-1);
        }
        if(!board.pieceExist(new Point(piece.pos.x ,piece.pos.y + 1))){
            possiblePoint(board,piece,1,2);
            possiblePoint(board,piece,-1,2);
        }
        if(!board.pieceExist(new Point(piece.pos.x ,piece.pos.y - 1))){
             possiblePoint(board,piece,1,-2);
             possiblePoint(board,piece,-1,-2);
        }
    }

    public void attacksSoldier(ChessBoard board, ChessPiece piece){
        if(piece.color== Color.BLACK) {
            possiblePoint(board,piece,-1,0) ;
        }
        if(piece.color == Color.RED){
            possiblePoint(board,piece,1,0);
        }
    }

    public void attacksUnlocked(ChessBoard board,ChessPiece piece){
        if(piece.color== Color.BLACK) possiblePoint(board,piece,-1,0);
        if(piece.color == Color.RED) possiblePoint(board,piece,1,0);
        possiblePoint(board,piece,0,-1);
        possiblePoint(board,piece,0,1);
    }

    private Color possiblePoint(ChessBoard board, ChessPiece piece, int x_offset, int y_offset){
        Point testPos = new Point ( piece.pos.x + x_offset, piece.pos.y + y_offset);
        if(!board.tileExist(testPos)) return null; //Invalid Tile

        // General and Advisor cant go out of castle
        if(piece.type == Type.GENERAL || piece.type == Type.ADVISOR){
            if(piece.color == Color.BLACK){
                if(3 > testPos.y || testPos.y >5 || testPos.x <7) return Color.BLACK;
            }
            if(piece.color == Color.RED){
                if(3 > testPos.y || testPos.y >5 || testPos.x > 2) return Color.RED;
            }
        }

        //Elephant cant go through river
        if(piece.type == Type.ELEPHANT){
            if(piece.color == Color.BLACK) {
                if(testPos.x < 5) return Color.BLACK;
            }
            if(piece.color == Color.RED) {
                if(testPos.x > 4) return Color.RED;
            }
        }

        //Cannon must hop to attack
        if(piece.type == Type.CANNON){
            //Empty Tile
            if(!board.pieceExist(testPos) && !hopped){
                piece.moves.attackTiles.add(testPos);
                return Color.NONE;
            }
            if(!board.pieceExist(testPos) && hopped) return Color.NONE;
            if(board.pieceExist(testPos) && hopped) {
                if(piece.color != board.tile[testPos.x][testPos.y].color){
                    piece.moves.attackTiles.add(testPos);
                }
                return board.tile[testPos.x][testPos.y].color;
            }
            if(board.pieceExist(testPos) && !hopped) {
                hopped = true;
                return Color.NONE;
            }
        }

        if(!board.pieceExist(testPos)){
            piece.moves.attackTiles.add(testPos); //Empty Tile
        return Color.NONE;
        }
        if(board.pieceExist(testPos)){
            if(piece.color != board.tile[testPos.x][testPos.y].color){
                piece.moves.attackTiles.add(testPos);
            }
            return board.tile[testPos.x][testPos.y].color;
        }

        return null;
    }

    /**
     * Function set Valis Tile for a single piece
     * @param board board
     * @param piece piece
     */
    
    public void setValidTiles(ChessBoard board, ChessPiece piece){
        piece.moves.validTiles.clear();
        
        Vector<Point> possiblePoint = piece.moves.attackTiles;
        
        //Create a virtual 
        ChessBoard virtualboard = new ChessBoard(board);
        ChessPiece virtualpiece = virtualboard.tile[piece.pos.x][piece.pos.y];
        /* Figure out colors */
        Color ourColor = virtualpiece.color;
        Color opponentColor;
        if (virtualpiece.color == Color.BLACK)
            opponentColor = Color.RED;
        else
            opponentColor = Color.BLACK;

        /* Save the ChessPiece's original position */
        Point originalPosition = new Point(virtualpiece.pos);	// Must be DEEP COPY

        for (Point newPosition : possiblePoint)
        {
            /* Save any pieces on tile we are moving to in case they're captured */
            ChessPiece possibleCapturedPiece = virtualboard.tile[newPosition.x][newPosition.y];

            virtualboard.movePiece(virtualpiece, newPosition);
            virtualboard.updateAttackTilesOneColor(opponentColor);
            virtualboard.updateInChecked();

            /* Test for valid moves - avoid "Discover Check" */
            //if (Doesn't cause "Discover check" on own King)
            if ( ! (ourColor == Color.RED && virtualboard.red_in_checked || ourColor == Color.BLACK && virtualboard.black_in_checked))
                piece.moves.validTiles.add(new Point(newPosition));
            /* Undo Move */
            virtualboard.movePiece(virtualpiece, originalPosition);
            virtualboard.tile[newPosition.x][newPosition.y] = possibleCapturedPiece;
            virtualboard.updateAttackTilesOneColor(opponentColor);
            virtualboard.updateInChecked();
        }
    }
    
}
