package Main_Component;

import Piece_Properties.Moves;
import main.java.Piece_Properties.Color;
import main.java.Piece_Properties.Type;

import javax.swing.*;
import java.awt.*;

import static main.java.Piece_Properties.Color.*;
import static main.java.Piece_Properties.Type.*;

public class ChessPiece {
    public Color color;
    public Type type;
    public ImageIcon imageIcon;
    public Point pos ;
    public Moves moves; //

    public ChessPiece(Type type,Color color, Point pos){
        this.color = color;
        this.pos = pos;
        this.type = type;
        moves = new Moves();
        initializeImage();
    }

    public ChessPiece(ChessPiece other){
        //Copy other
        this.color = other.color;
        this.type = other.type;
        this.pos = new Point(other.pos);
        moves = new Moves();
        imageIcon = null;
    }

    public void initializeImage(){
        if(this.color == RED){
            if(this.type == ADVISOR) imageIcon = new ImageIcon("Image/Red_Advisor.svg.png");
            else if (this.type == CANNON) imageIcon = new ImageIcon("Image/Red_Cannon.png");
            else if (this.type == CHARIOT) imageIcon = new ImageIcon("Image/Red_Chariot.png");
            else if (this.type == ELEPHANT) imageIcon = new ImageIcon("Image/Red_Elephant.png");
            else if (this.type == GENERAL) imageIcon = new ImageIcon("Image/Red_General.png");
            else if (this.type == HORSE) imageIcon = new ImageIcon("Image/Red_Horse.png");
            else if (this.type == SOLDIER) imageIcon = new ImageIcon("Image/Red_Soldier.png");
        } else if(this.color == BLACK){
            if(this.type == ADVISOR) imageIcon = new ImageIcon("Image/Black_Advisor.svg.png");
            else if (this.type == CANNON) imageIcon = new ImageIcon("Image/Black_Cannon.png");
            else if (this.type == CHARIOT) imageIcon = new ImageIcon("Image/Black_Chariot.png");
            else if (this.type == ELEPHANT) imageIcon = new ImageIcon("Image/Black_Elephant.png");
            else if (this.type == GENERAL) imageIcon = new ImageIcon("Image/Black_General.png");
            else if (this.type == HORSE) imageIcon = new ImageIcon("Image/Black_Horse.png");
            else if (this.type == SOLDIER) imageIcon = new ImageIcon("Image/Black_Soldier.png");
        }
    }
}
