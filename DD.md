# Design_Document
## Fuction Details
- Visualize Chinese chess
## Project Structure
1. Folder Image: 
  - Include image icon for each chess piece
2. Main_Component Folder : 
  - Include main component classes:
    - 2.A) ChessBoard class:
    - Have properities about chess board and allow you to create a new chessboard and interact with it
    - 2.B) ChessPiece class:
    - Allow you to create a new chess piece
    - 2.C) Controller:
    - Controller of the game
    - 2.D) StartGame
    - Allow you to create a new game
    - 2.E) View:
    - Provide view for MVC(model - View - Controller)
    - 2.F) Command:
    - Provide command for player
    - 2.G) CommandManager : 
    - Manage commands
    - 2.H) Button : 
    - Allow you to create button, which will help player interact with program
3. Piece_Properties
  - Include information about each chess piece:
    - 3.A) Color (Enum Class)
    - Represent which side chess piece on
    - 3.B) Moves
    - Represent how chess piece move
    - 3.C) Type (Enum Class)
    - Represent which type of the chess piece 
4. Listeners
  - Include MouseAdapter classes, which connect your action with program via pressing button
5. Test folder : 
  - Include test to make sure program works well 
## Execution plan 