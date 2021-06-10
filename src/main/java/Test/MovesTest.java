package Test;


import Main_Component.ChessBoard;
import Main_Component.ChessPiece;
import Piece_Properties.Moves;
import main.java.Piece_Properties.Color;
import main.java.Piece_Properties.Type;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * \brief
 * Tests the fundamental 12 different types of moves as defined by each function \n
 * Also tests "Attack Tiles" and "Valid Tiles"
 * @author Rodney Shaghoulian
 */

public class MovesTest {

    @Test
    public void testMoves(){
        Moves testMoves = new Moves();

        assertEquals(testMoves.attackTiles.isEmpty(),true);
        assertEquals(testMoves.validTiles.isEmpty(),true);
    }

    @Test
    public void testSetAttackTiles(){
        ChessBoard board = new ChessBoard(10,9);
        for(int column = 0;column <= board.columns;column+=2){
            assertEquals(board.tile[3][column].moves.attackTiles.size(), 1);
            assertEquals(board.tile[6][column].moves.attackTiles.size(), 1);
        }
        for(int row = 0;row< board.rows; row+=9){
            assertEquals(board.tile[row][0].moves.attackTiles.size(), 2);
            assertEquals(board.tile[row][1].moves.attackTiles.size(), 2);
            assertEquals(board.tile[row][2].moves.attackTiles.size(), 2);
            assertEquals(board.tile[row][3].moves.attackTiles.size(), 1);
            assertEquals(board.tile[row][4].moves.attackTiles.size(), 1);
            assertEquals(board.tile[row][5].moves.attackTiles.size(), 1);
            assertEquals(board.tile[row][6].moves.attackTiles.size(), 2);
            assertEquals(board.tile[row][7].moves.attackTiles.size(), 2);
            assertEquals(board.tile[row][8].moves.attackTiles.size(), 2);
        }

        assertEquals(board.tile[2][1].moves.attackTiles.size(), 12);
        assertEquals(board.tile[2][7].moves.attackTiles.size(), 12);
        assertEquals(board.tile[7][1].moves.attackTiles.size(), 12);
        assertEquals(board.tile[7][7].moves.attackTiles.size(), 12);

        //Move a piece
        board.movePiece(board.tile[2][1], new Point(6,1));

        assertEquals(board.tile[6][1].moves.attackTiles.size(),7);

        board.movePiece(board.tile[0][1], new Point(2,2));
        board.movePiece(board.tile[3][2], new Point(4,2));
        board.movePiece(board.tile[4][2],new Point(5,2));

        assertEquals(board.tile[2][2].moves.attackTiles.size(),5);
        assertEquals(board.tile[5][2].moves.attackTiles.size(),3);

        //Soldier crossed river
        board.movePiece(board.tile[3][0],new Point(5,0));
        assertEquals(board.tile[5][0].moves.attackTiles.size(),2);
    }

    @Test
    public void setValidTiles(){
        ChessBoard board = new ChessBoard(10,9);
        for(int column = 0;column <= board.columns;column+=2){
            assertEquals(board.tile[3][column].moves.validTiles.size(), 1);
            assertEquals(board.tile[6][column].moves.validTiles.size(), 1);
        }
        for(int row = 0;row< board.rows; row+=9){
            assertEquals(board.tile[row][0].moves.validTiles.size(), 2);
            assertEquals(board.tile[row][1].moves.validTiles.size(), 2);
            assertEquals(board.tile[row][2].moves.validTiles.size(), 2);
            assertEquals(board.tile[row][3].moves.validTiles.size(), 1);
            assertEquals(board.tile[row][4].moves.validTiles.size(), 1);
            assertEquals(board.tile[row][5].moves.validTiles.size(), 1);
            assertEquals(board.tile[row][6].moves.validTiles.size(), 2);
            assertEquals(board.tile[row][7].moves.validTiles.size(), 2);
            assertEquals(board.tile[row][8].moves.validTiles.size(), 2);
        }

        assertEquals(board.tile[2][1].moves.validTiles.size(), 12);
        assertEquals(board.tile[2][7].moves.validTiles.size(), 12);
        assertEquals(board.tile[7][1].moves.validTiles.size(), 12);
        assertEquals(board.tile[7][7].moves.validTiles.size(), 12);

        board.movePiece(board.tile[2][1],new Point(2,4));
        board.movePiece(board.tile[6][4],new Point(5,4));
        board.movePiece(board.tile[3][4],new Point(4,4));
        board.updateValidTiles();
        assertEquals(board.tile[5][4].moves.validTiles.size(),0);
        /*board.movePiece(board.tile[5][4],new Point(4,4));
        board.updateAttackTilesOneColor(Color.RED);
        board.updateInChecked();

        assertTrue(board.black_in_checked);
        */
        board.movePiece(board.tile[5][4],new Point(4,4));
        board.updateValidTiles();
        assertFalse(board.tile[4][4].moves.validTiles.size() == board.tile[4][4].moves.attackTiles.size());
    }

    @Test
    public void testCannon(){
        ChessBoard board = new ChessBoard(10,9);
        ChessPiece testPiece = board.tile[2][1];
        assertEquals(testPiece.moves.validTiles.size(),12);

        board.movePiece(testPiece,new Point(6,3));
        board.movePiece(board.tile[7][1],new Point(7,3));
        board.updateAttackTiles();
        assertEquals(testPiece.moves.attackTiles.size(),8);
    }
}
