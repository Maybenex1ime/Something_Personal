package Test;

import Main_Component.Button;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * \brief
 * Tests the custom Button class
 * @author Rodney Shaghoulian
 */
public class ButtonTest {

    /**
     * Tests the Button Constructor
     */
    @Test
    public void testButtonConstructor() {
        Button button = new Button(3, 5);
        assertEquals(button.xPos, 3);
        assertEquals(button.yPos, 5);
    }

    /**
     * Tests the Button's createPoint function
     */
    @Test
    public void testCreatePoint() {
        Button button = new Button(3, 5);
        Point point = button.createPoint();
        assertEquals(point.x, 3);
        assertEquals(point.y, 5);
    }

}
