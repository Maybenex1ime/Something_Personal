package Main_Component;

import javax.swing.JButton;

import java.awt.Point;

public class Button extends JButton{
    public int xPos;	///< x Position of Button
    public int yPos;	///< y Position of Button

    /**
     * Creates a button given (x,y) coordinates
     * @param xPos	x Position of Button
     * @param yPos	y Position of Button
     */
    public Button(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public Point createPoint(){
        return new Point(xPos, yPos);
    }
}
