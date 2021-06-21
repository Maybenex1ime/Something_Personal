# Design_Document
## Fuction Details
- Visualize Chinese chess
- For better understanding about demo, please spend time to read the rules here : http://gambiter.com/xiangqi/
## Project Structure
1. Folder Image: 
  - Include image icon for each chess piece
2. Main_Component Folder : 
  - Include main component classes:
    - 2.1) ChessBoard class:
    - Have properities about chess board and allow you to create a new chessboard and interact with it
    - 2.2) ChessPiece class:
    - Allow you to create a new chess piece
    - 2.3) Controller:
    - Controller of the game
    - 2.4) StartGame
    - Allow you to create a new game
    - 2.5) View:
    - Provide view for MVC(model - View - Controller)
    - 2.6) Command:
    - Provide command for player
    - 2.7) CommandManager : 
    - Manage commands
    - 2.8) Button : 
    - Allow you to create button, which will help player interact with program
3. Piece_Properties
  - Include information about each chess piece:
    - 3.1) Color (Enum Class)
    - Represent which side chess piece on
    - 3.2) Moves
    - Represent how chess piece move
    - 3.3) Type (Enum Class)
    - Represent which type of the chess piece 
4. Listeners
  - Include extension of MouseAdapter classes, which connect your action with program via pressing button
5. Test folder : 
  - Include test to make sure program works well 
## Execution plan 
  - Stages:
    - Piece_properties folder : 3/6
    - Main_Component folder:
      - ChessBoard and ChessPiece classes : 6/6
      - View and Button classes : 8/6
      - Command and CommandManager class : 11/6
    - Listeners folder : 11/6
## Result 
![image](https://github.com/Maybenex1ime/Something_Personal/blob/main/ScreenShot/199799014_582521089567981_3398538406345156789_n.png?raw=true)
