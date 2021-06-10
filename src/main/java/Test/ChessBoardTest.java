package Test;


import Main_Component.ChessBoard;
import Main_Component.ChessPiece;
import main.java.Piece_Properties.Color;
import main.java.Piece_Properties.Type;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    @Test
    public void testChessBoard() {
        ChessBoard testBoard = new ChessBoard(10,9);
        verifyInitialConfiguration(testBoard);
    }

    @Test
    public void testCopyBoard(){
        ChessBoard firstBoard = new ChessBoard(10,9);
        ChessBoard CopyBoard = new ChessBoard(firstBoard);

        verifyInitialConfiguration(firstBoard);
        verifyInitialConfiguration(CopyBoard);

        //After Move Piece
        CopyBoard.movePiece(CopyBoard.RedGeneral,new Point(1,4));
        verifyInitialConfiguration(firstBoard);
    }

    @Test
    public void testMovePiece(){
        ChessBoard chessBoard = new ChessBoard(10,9);
        ChessPiece testPiece = chessBoard.tile[0][0];
        assertEquals(chessBoard.playerTurn,Color.RED);
        chessBoard.movePiece(testPiece,new Point(1,0));
        chessBoard.updateInChecked();

        assertFalse(chessBoard.red_in_checked);
        assertEquals(testPiece.pos.x,1);
        assertEquals(testPiece.pos.y,0);
        assertEquals(chessBoard.tile[1][0].type, Type.CHARIOT);
        assertNull(chessBoard.tile[0][0]);
        assertEquals(chessBoard.playerTurn,Color.BLACK);
    }

    @Test
    public void GeneralInChecked(){
        ChessBoard chessBoard = new ChessBoard(10,9);
        chessBoard.movePiece(chessBoard.tile[0][1],new Point(7,3));
        assertTrue(chessBoard.black_in_checked);
        assertFalse(chessBoard.red_in_checked);
        chessBoard.movePiece(chessBoard.tile[7][1],new Point(4,4));
        assertTrue(chessBoard.red_in_checked);
    }

    private void verifyInitialConfiguration(ChessBoard testBoard){
        /* Verify Simple Fields */
        assertEquals(testBoard.rows, 10);
        assertEquals(testBoard.columns, 9);
        assertEquals(testBoard.playerTurn, Color.RED);
        assertFalse(testBoard.red_in_checked);
        assertFalse(testBoard.black_in_checked);
        assertFalse(testBoard.Over);
        assertFalse(testBoard.stalemate);
        assertEquals(testBoard.RedGeneral.type, Type.GENERAL);
        assertEquals(testBoard.RedGeneral.color, Color.RED);
        assertEquals(testBoard.BlackGeneral.type, Type.GENERAL);
        assertEquals(testBoard.BlackGeneral.color, Color.BLACK);
        /*************************/
        /* Verify All 64 Squares */
        /*************************/

        /* Verify 1st 2 rows are white color, and correct poss */
            for (int column = 0; column < 9; column++){
                assertEquals(testBoard.tile[0][column].color, Color.RED);
                /* Makes sure the board and pieces agree on pos */
                assertEquals(testBoard.tile[0][column].pos.y, (new Point(0, column)).y);
            }

        /* Verify last 2 rows are black color, and correct poss */

            for (int column = 0; column < 8; column++){
                assertEquals(testBoard.tile[9][column].color, Color.BLACK);
                /* Makes sure the board and pieces agree on pos */
                assertEquals(testBoard.tile[9][column].pos.y, (new Point(9, column)).y);
            }


        /* Verify row 1 and row 6 are pawns */
        for (int column = 0; column < 9; column+=2){
            assertEquals(testBoard.tile[3][column].type, Type.SOLDIER);
            assertEquals(testBoard.tile[6][column].type, Type.SOLDIER);
        }
        /* Verify Remaining 16 Pieces */
        assertEquals(testBoard.tile[0][0].type, Type.CHARIOT);
        assertEquals(testBoard.tile[0][8].type, Type.CHARIOT);
        assertEquals(testBoard.tile[9][0].type, Type.CHARIOT);
        assertEquals(testBoard.tile[9][8].type, Type.CHARIOT);
        assertEquals(testBoard.tile[0][1].type, Type.HORSE);
        assertEquals(testBoard.tile[0][7].type, Type.HORSE);
        assertEquals(testBoard.tile[9][1].type, Type.HORSE);
        assertEquals(testBoard.tile[9][7].type, Type.HORSE);
        assertEquals(testBoard.tile[0][2].type, Type.ELEPHANT);
        assertEquals(testBoard.tile[0][6].type, Type.ELEPHANT);
        assertEquals(testBoard.tile[9][2].type, Type.ELEPHANT);
        assertEquals(testBoard.tile[9][6].type, Type.ELEPHANT);
        assertEquals(testBoard.tile[0][3].type, Type.ADVISOR);
        assertEquals(testBoard.tile[9][3].type, Type.ADVISOR);
        assertEquals(testBoard.tile[0][5].type, Type.ADVISOR);
        assertEquals(testBoard.tile[9][5].type, Type.ADVISOR);
        assertEquals(testBoard.tile[0][4].type, Type.GENERAL);
        assertEquals(testBoard.tile[9][4].type, Type.GENERAL);

        //Verifying Cannon
        assertEquals(testBoard.tile[2][1].type, Type.CANNON);
        assertEquals(testBoard.tile[2][7].type, Type.CANNON);
        assertEquals(testBoard.tile[7][1].type, Type.CANNON);
        assertEquals(testBoard.tile[7][7].type, Type.CANNON);
    }
    
}
