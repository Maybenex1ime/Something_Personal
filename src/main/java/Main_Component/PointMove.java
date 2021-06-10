package Main_Component;

import javax.swing.*;
import java.awt.*;

public class PointMove {
    public int xPos;
    public int yPos;
    public PointMove(int xPos,int Ypos){
        this.xPos = xPos;
        this.yPos = Ypos;
    }

    public Point getPoint(){
        return new Point(xPos,yPos);
    }

    public void setIcon(ImageIcon imageIcon){

    }
}
