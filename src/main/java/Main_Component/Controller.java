package Main_Component;

import listeners.*;

import java.awt.*;
import java.util.ArrayList;

public class Controller {

    public ChessBoard chessBoard; 					///< The Model in MVC structure
    public CommandManager commandManager;			///< Saves Commands so we can undo and redo them
    public boolean inMovement;						///< true if a player is mid-move
    public ArrayList<Button> buttonsToHighlight; 	///< Buttons to highlight for the user to display valid moves for a ChessPiece
    public ArrayList<Button> buttonsCanMoveTo;		///< Buttons that a current ChessPiece can move to
    public Point originPoint;						///< The origin of a moving ChessPiece

    public View view;								///< The "View" in MVC structure.
    public int redGamesWon = 0;					///< Number of games RED has won
    public int blackGamesWon = 0;					///< Number of games BLACK has won
    public boolean isClassicMode = true;			///< true for Classic Mode. false for Custom Mode.

    public Controller(){
        initializeVariables();
        view = new View(chessBoard, this);
        addMouseListeners();
    }

    public void reset(){
        initializeVariables();
        view.initialize(chessBoard, this);
        addMouseListeners();
    }

    public void initializeVariables(){
        chessBoard = new ChessBoard(10, 9);
        commandManager = new CommandManager();
        inMovement = false;
        buttonsToHighlight = new ArrayList<Button>();
        buttonsCanMoveTo = new ArrayList<Button>();
        originPoint = null;
    }

    /**
     * Add mouseListeners to the View
     */
    public void addMouseListeners(){
        /* Add MoveListener to all tiles */
        for (int i = 0; i < chessBoard.rows; i++){
            for (int j = 0; j < chessBoard.columns; j++){
                view.addMouseListener(new MoveListener(this), view.button[i][j]);
            }
        }
        view.addMouseListener(new UndoListener(this), view.undoButton);
        view.addMouseListener(new ForfeitListener(this), view.RedForfeit);
        view.addMouseListener(new ForfeitListener(this), view.blackForfeit);
        view.addMouseListener(new RestartListener(this), view.RedRestart);
        view.addMouseListener(new RestartListener(this), view.blackRestart);
        view.addMouseListener(new NewGameListener(this, true), view.classicMode);
    }
}
