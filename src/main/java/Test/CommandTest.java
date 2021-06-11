package Test;


import Main_Component.ChessBoard;
import Main_Component.Command;
import Main_Component.Controller;
import Main_Component.View;
import main.java.Piece_Properties.Type;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * \brief
 * Tests the Command class for executing and undoing Commands
 * @author Rodney Shaghoulian
 */
public class CommandTest {

    /**
     * Tests executing a Command, and undoing a Command.
     */
    @Test
    public void testExecuteUndo() {
        /* Set up data */
        Controller controller = new Controller();
        ChessBoard chessBoard = controller.chessBoard;
        View view = controller.view;
        Command command = new Command(chessBoard, view, new Point (0, 0), new Point(4, 4));
        /* Test execute */
        command.execute();
        assertEquals(chessBoard.tile[0][0], null);
        assertEquals(chessBoard.tile[4][4].type, Type.CHARIOT);
        /* Test Undo */
        command.undo();
        assertEquals(chessBoard.tile[0][0].type, Type.CHARIOT);
        assertEquals(chessBoard.tile[4][4], null);
    }
}
