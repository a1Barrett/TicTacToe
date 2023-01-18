# TicTacToe
School project to create a tic tac toe game with a gui.


Introduction:

  Project was orginally created using a command line interface.  In this assignment, we will use a 
  Graphical User Interface (GUI) and the Model-View-Controller architectural pattern to move our 
  Tic-Tac-Toe game from a command line interface to a Graphical User Interface. 

Files:

TicTacToeGame.java

    This class is the entry point of our Tic-Tac-Toe program. It calls the GameSetupScreen and
    GameSetupController class and turns the control of the program over to the event dispatch
    thread which will wait for an event to occur.

Models -

BoardPosition.java

      This class is be used to keep track of an individual cell for a board. BoardPosition will
      have variables to represent the Row position and the Column position. There is only be one 
      constructor for the class, which will take in an int for the Row position and an int for the 
      Column position. After the constructor is called, there is no way to change any fields.
      The equals() method is overriden.  The toString() method is also overriden to create a string
      in the format "<row><column>".
      
IGameBoard.java

      An interface that the GameBoard and GameBoardMem will implement. 
      
GameBoard.java

      Contains the actual game board. This is a fast implementation as the game board is represented as a 2D character 
      array. Each position has a single blank character until a player plays on that position. It contains the following 
      methods: checkForDraw, checkHorizontalWin, checkVeritcalWin, checkDiagonalWin, whatsAtPos, and isPlayerAtPos. 
      
GameBoardMem.java

      Contains the actual game board. This is a memory efficient implementation as the game board is represented
      using a Map.  Map uses a key-value pair. The key field of our Map will be a Character that represents the player so
      that each player will get their own entry in the Map. The value associated with that player will be a
      List of BoardPositions that the player occupies on the board. If a player has not placed a token,
      there will be no key or List in the Map to represent that player. The memory efficiency of this implementation
      comes from the fact that the empty board does not use up any space in memory. When a player adds
      their first token, you add the key for that player and a List with that BoardPosition in it. As a
      player adds more tokens, you add the BoardPosition onto the List for that player.
      
Views -
  
GameSetupScreen.java

      This class contains the code to create and layout the GUI for the setup screen. It also is the observer of
      the submit button. When someone clicks on submit, the actionPerformed method is called, which
      then calls the controller object. 
      
TicTacToeView.java

      This class is the view of our Tic-Tac-Toe game. Our view has a message area, and a list of buttons. The
      buttons will be arranged in a ROWS_IN_BUTTON_PANEL x COLUMNS_IN_BUTTON_PANEL grid.
      Players will click on a button in the grid to place the marker in the grid. All events will be passed to the
      controller by calling the processButtonClick method.
      
Controllers - 
  
GameSetupController.java

      This class is the controller for our setup screen. The processButtonClick method is called by
      GameSetupView when someone clicks on the submit button. It is passed in the rows, cols, players and
      the number to win by the view, but it still needs to validate that input. If there are any errors it can use
      the displayError method in the GameSetupView class to inform the player of the error, then
      wait for them to fix it and resubmit. If there are no errors, it will create a new IGameBoard object (the 
      implementation will depend on the size of the game board) to serve as the model, and the TicTacToeController 
      and TicTacToeView. Control is then passed over the event dispatch thread that will wait for an event to
      occur.
      
TicTacToeController.java

      This class is the controller for the game. It uses the TicTacToeView object to interact with the user
      and the IGameBoard object as its model to track what’s happening in the game. The processButtonClick 
      method handles everything about the process when a user clicks on a column to place a token, including 
      input validation. The methods in the TicTacToeView class and the methods in the IGameBoard object are 
      used to accomplish this. A newGame method is provided to send the program back to the setup stage. After 
      someone wins the game, it is displayed to the players who won and tells them to click any button to start 
      a new game. If they click a button, a new game will start. Clicking the button will callprocessButtonClick 
      and return to the top of the method. The same should happen for a tie game. We are waiting for events to 
      occur, and then we respond to them (event-driven programming).

JUnit tests:
  
    There are two JUnit test classes.  One for GameBoard and one for GameBoardMem. The JUnit test code
    uses the Factory Method Design Pattern by having a private method that calls the constructor fo the 
    IGameBoard object and returns it.
    Each tests the following methods: constructor, checkSpace, checkHorizontalWin, checkVerticalWin,
    checkDiagonalWin, checkForDraw, whatsAtPos, isPlayerAtPos, and placeMarker.

Javadoc Documentation:

    Doc folder contains javadoc comments for all files presented as a web page.

Program Report:
  
Requirements Analysis -

    Fully analyze the requirements of this program. Express all functional requirements as user
    stories. List all non-functional requirements of the program as well. 
  
Design - 

    Create class diagrams for GameScreen, BoardPosition, IGameBoard, GameBoardMem, AbsGameBoard, 
    GameBoard, and TicTacToeController. You also need to provide an activity diagram for 
    TicTacToeController, IGameBoard, GameBoard, and GameBoardMem. All design elements are created
    using draw.io.
  
Testing:

    Your report should include the test cases created.

