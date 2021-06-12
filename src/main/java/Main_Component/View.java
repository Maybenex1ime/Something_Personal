package Main_Component;

import main.java.Piece_Properties.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * \brief
 * The "View" for MVC (Model - View - Controller)
 * @author Rodney Shaghoulian
 */
public class View{
    public ChessBoard chessBoard;					///< The view has access to the ChessBoard
    public Controller controller;					///< The view has access to the Controller
    public JFrame frame;							///< We will put a JPanel (which contains 64 JButtons) on this
    public JPanel panel;							///< Will add 64 Buttons to this panel, and add the JPanel to the JFrame
    public Button[][] button = new Button[10][9];	///< 64 Buttons on Panel

    private final int frameHeight = 814;			///< Picked to make a square JFrame of ~600 pixels.
    private final int frameWidth = 1067;				///< Picked to make a square JFrame of ~600 pixels.
    private final int boardWidth = 675; 			///< Approximate number of Pixels on square board
    private final int squareIconLength = 75;		///< length of square's edge for the icons we're using

    public JButton undoButton;						///< Allows for undoing chess moves
    public JButton redoButton;						///< Allows for redoing chess moves

    public JTextField statusBox;					///< Used to display information when Red or BLACK is in check

    public JTextField RedName;					///< Used to display unique name for Red Player
    public JTextField blackName;					///< Used to display unique name for BLACK Player

    public JTextField RedScore;					///< Used to display number of games Red has won
    public JTextField blackScore;					///< Used to display number of games BLACK has won

    public JButton RedForfeit;					///< Used to give Red the option to forfeit a game
    public JButton blackForfeit;					///< Used to give BLACK the option to forfeit a game

    public JButton RedRestart;					///< Used to give Red the option to restart a game (if opponent agrees)
    public JButton blackRestart;					///< Used to give BLACK the option to restart a game (if opponent agrees)

    public JButton classicMode;						///< Used to create a new game in "Classic" mode
    public JButton customMode;						///<  Used to create a new game in "Custom" mode


    public View(ChessBoard chessBoard, Controller controller){
        frame = new JFrame("Chess");
        initialize(chessBoard, controller);
    }

    /**
     * Initializes the view given a ChessBoard and Controller
     * @param chessBoard	The "Model" that we are initializing the View from
     * @param controller	The "Controller" we will use for event listening
     */
    public void initialize(ChessBoard chessBoard, Controller controller){
        this.chessBoard = chessBoard;
        this.controller = controller;

        /* Initializes Swing Variables */
        panel = new JPanel(null);
        for (int row = 0; row < chessBoard.rows; row++){
            for (int column = 0; column < chessBoard.columns; column++){
                button[row][column] = new Button(row, column);
            }
        }
        /* Set up JFrame */
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);						//null indicates the window is placed in the center of the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Loop through board - Create 64 Buttons. Add them to the JPanel */
        for (int row = 0; row < chessBoard.rows; row++){
            for (int column = 0; column < chessBoard.columns; column++){

                /* Create Buttons */
                ChessPiece currentPiece = chessBoard.tile[row][column];
                Button currentButton = button[row][column];
                if (currentPiece != null)
                    currentButton.setIcon(currentPiece.imageIcon);

                /* Set bounds for the button */
                int xPositionGUI = getXPositionGUI(row);
                int yPositionGUI = getYPositionGUI(column);
                currentButton.setBounds(xPositionGUI, yPositionGUI, squareIconLength, squareIconLength);

                setBackground(currentButton);

                panel.add(currentButton);
            }
        }
        createInterfaceButtons(panel);
        updateButtons();

        frame.setContentPane(panel);	//add the JPanel to the JFrame
        frame.setVisible(true);
    }

    /**
     * Updates the status of JButtons, Buttons, and JTextFields on current View
     */
    public void updateButtons(){
        /* Show which player's turn it is */
        if (chessBoard.playerTurn == Color.RED){
            blackName.setBackground(null);
            RedName.setBackground(java.awt.Color.GREEN);
            RedForfeit.setEnabled(true);
            blackForfeit.setEnabled(false);
            RedRestart.setEnabled(true);
            blackRestart.setEnabled(false);
        }
        else{
            blackName.setBackground(java.awt.Color.GREEN);
            RedName.setBackground(null);
            RedForfeit.setEnabled(false);
            blackForfeit.setEnabled(true);
            RedRestart.setEnabled(false);
            blackRestart.setEnabled(true);
        }

        /* Determine if players are in check or checkmate */
        if (chessBoard.winner == Color.RED){
            javax.swing.JOptionPane.showMessageDialog(null, "Checkmate. Red Wins!");
            controller.redGamesWon++;
            controller.reset();
        }
        else if (chessBoard.winner == Color.BLACK){
            javax.swing.JOptionPane.showMessageDialog(null, "Checkmate. Black Wins!");
            controller.blackGamesWon++;
            controller.reset();
        }
        else if (chessBoard.red_in_checked){
            statusBox.setText("Red In Check");
            statusBox.setBackground(java.awt.Color.YELLOW);
        }
        else if (chessBoard.black_in_checked){
            statusBox.setText("Black In Check");
            statusBox.setBackground(java.awt.Color.YELLOW);
        }
        else{
            statusBox.setText("Have Fun!!!");
            statusBox.setBackground(null);
        }
    }

    /**
     * Create the JButtons, Buttons, and JTextFields for the View
     * @param panel		The panel we are attaching the buttons to
     */
    public void createInterfaceButtons(JPanel panel){
        createNames(panel);
        createScores(panel);
        createUndoButton(panel);
        createRedoButton(panel);
        createStatusBox(panel);
        createForfeitButtons(panel);
        createRestartButtons(panel);
        createModeButtons(panel);
    }

    /**
     * Creates unique names (JTextfields) for BLACK and Red
     * @param panel		The panel to attach the JTextFields to
     */
    public void createNames(JPanel panel){
        blackName = new JTextField("Black Player: Player 2");
        blackName.setBounds(800, 20, 200, 40);
        blackName.setBackground(null);
        blackName.setBorder(null);
        blackName.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(blackName);
        RedName = new JTextField("Red Player: Player 1");
        RedName.setBounds(800, 540, 200, 40);
        RedName.setBackground(java.awt.Color.GREEN);
        RedName.setBorder(null);
        RedName.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(RedName);
    }

    /**
     * Creates scores for BLACK and Red
     * @param panel		The panel to attach the JTextFields to
     */
    public void createScores(JPanel panel){
        RedScore = new JTextField("Red Score: " + controller.redGamesWon);
        RedScore.setBounds(760, 480, 100, 40);
        RedScore.setEditable(false);
        RedScore.setBorder(null);
        panel.add(RedScore);
        blackScore = new JTextField("Black Score: " + controller.blackGamesWon);
        blackScore.setBounds(760, 80, 100, 40);
        blackScore.setBorder(null);
        blackScore.setEditable(false);
        panel.add(blackScore);
    }

    /**
     * Creates an "Undo" JButton that lets us undo our previous move
     * @param panel		The panel to attach the JTextFields to
     */
    public void createUndoButton(JPanel panel){
        undoButton = new JButton("Undo");
        undoButton.setBounds(780, 250, 100, 40);
        panel.add(undoButton);
    }

    /**
     * Creates a "Redo" JButton that lets us redo our previous move
     * @param panel		The panel to attach the JTextFields to
     */
    public void createRedoButton(JPanel panel){
        redoButton = new JButton("Redo");
        redoButton.setBounds(780, 310, 100, 40);
        panel.add(redoButton);
    }

    /**
     * Creates a status box as a JTextField that will tell us when a player is in check
     * @param panel		The panel to attach the JTextFields to
     */
    public void createStatusBox(JPanel panel){
        statusBox = new JTextField("Have Fun!!!");
        statusBox.setBounds(250, 695, 110, 40);
        statusBox.setBorder(null);
        statusBox.setBackground(null);
        statusBox.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(statusBox);
    }

    /**
     * Creates a "Forfeit" JButton that lets us resign from a game and take a loss
     * @param panel		The panel to attach the JTextFields to
     */
    public void createForfeitButtons(JPanel panel){
        RedForfeit = new JButton("Red Forfeit");
        RedForfeit.setBounds(870, 500, 150, 30);
        panel.add(RedForfeit);
        blackForfeit = new JButton("Black Forfeit");
        blackForfeit.setBounds(870, 70, 150, 30);
        panel.add(blackForfeit);
    }

    /**
     * Creates a "Restart" JButton that lets us restart a game (if opponent also wants to restart)
     * @param panel		The panel to attach the JTextFields to
     */
    public void createRestartButtons(JPanel panel){
        RedRestart = new JButton("Red Restart");
        RedRestart.setBounds(870, 460, 150, 30);
        panel.add(RedRestart);
        blackRestart = new JButton("Black: Restart");
        blackRestart.setBounds(870, 110, 150, 30);
        panel.add(blackRestart);
    }

    /**
     * Creates JButtons that let us start new games in Classic or Custom mode
     * @param panel		The panel to attach the JTextFields to
     */
    public void createModeButtons(JPanel panel){
        classicMode = new JButton("New Game ");
        classicMode.setBounds(600, 735, 200, 30);
        panel.add(classicMode);
    }

    public void setBackground(Button button){
        if ((button.xPos + button.yPos) % 2 == 0)
            button.setBackground(java.awt.Color.GRAY);
        else
            button.setBackground(null);
    }

    /**
     * Calculates "x" Position on GUI for ChessPiece
     * @param x 	the "x" Position from 0-7
     * @return 		the "x" coordinate on the GUI from 0 - 525 (viewablePixels)
     */
    public int getXPositionGUI(int x){
        return x * squareIconLength;
    }

    /**
     * Calculates "y" Position on GUI for ChessPiece
     * @param y 	the "y" Position from 0-7
     * @return 		the "y" coordinate on the GUI from 0 - 525 (for boardWidth of 600)
     */
    public int getYPositionGUI(int y){
        int yOffset = squareIconLength;
        return boardWidth - (y * squareIconLength) - yOffset;
    }

    public Button getButton(Point point){
        return button[point.x][point.y];
    }

    /**
     * Updates the ImageIcon for a Button
     * @param image		Our new desired ImageIcon
     * @param button	The Button to update with a new Image
     */
    public void setIcon(ImageIcon image, Button button){
        button.setIcon(image);
    }

    /**
     * Adds an MouseListener to a JButton
     * @param mouseListener		MouseListener to add (to a Button)
     * @param button			JButton to add a MouseListener to
     */
    public void addMouseListener(MouseListener mouseListener, JButton button){
        button.addMouseListener(mouseListener);
    }
}
