package Test;


import Main_Component.ChessPiece;
import main.java.Piece_Properties.Color;
import main.java.Piece_Properties.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.*;

public class ChessPieceTess {
    /**
     * Simple test to make sure the constructor works properly
     */
    @Test
    public void testChessPiece(){
        ChessPiece BAdvisor = new ChessPiece(Type.ADVISOR, Color.BLACK,new Point(0,4));
        assertNotNull(BAdvisor);
        assertEquals(BAdvisor.type,Type.ADVISOR);
        assertEquals(BAdvisor.color,Color.BLACK);
    }
}
