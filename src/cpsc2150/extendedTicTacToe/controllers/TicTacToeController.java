package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

/**
 * <p>
 * The {@link TicTacToeController} class will handle communication between our {@link TicTacToeView}
 * and our model ({@link IGameBoard} and {@link BoardPosition})
 * </p>
 *
 * <p>
 * This is where you will write code
 * <p>
 *
 * You will need to include your {@link BoardPosition} class, the {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class TicTacToeController {

    /**
     * <p>
     * All possible player tokens.
     * </p>
     */
    private final char[] POSSIBLE_PLAYER_CHARS = {'X', 'O', 'C', 'L', 'K', 'R', 'Q', 'Z', 'B', 'F'};

    /**
     * <p>
     * Current player tokens being used.
     * </p>
     */
    private char[] playerCharacters;

    /**
     * <p>
     * The the current character's turn.
     * </p>
     */
    private int countCurrCharacter;

    /**
     * <p>
     * The BoardPosition that the user will place their marker on.
     * </p>
     */
    private BoardPosition pos;

    /**
     * <p>
     * A boolean that represents whether a game is over or is in play.
     * </p>
     */
    private boolean isEnd;

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private TicTacToeView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;

    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * This creates a controller for running the Extended TicTacToe game
     * </p>
     *
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     *
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;

        // Some code is needed here.
        //set the player characters for the current game
        playerCharacters = new char[numPlayers];
        for (int i = 0; i < numPlayers; i++) playerCharacters[i] = POSSIBLE_PLAYER_CHARS[i];

        //is is not the end of the game
        isEnd = false;
        //the first character's turn is first
        countCurrCharacter = 0;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     *
     * @param row
     *      The row of the activated button
     * @param col
     *      The column of the activated button
     *
     * @post [ will allow the player to place a marker in the position if it is a valid space, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int row, int col) {
        // Your code goes here.

        //if the end of the game has been reached, start a new game
        if (isEnd) {
            newGame();
            return;
        }

        //create a BoardPosition for the position the player wants to place a marker into
        pos = new BoardPosition(row, col);

        //if the row and column are not in the valid range
        if (row >= curGame.getNumRows() || row < 0 || col >= curGame.getNumColumns() || col < 0|| !curGame.checkSpace(pos)) {
            //print error message
            screen.setMessage("That space is unavailable, please pick again");
            return;
        }

        //place the marker onto the board and onto the screen
        curGame.placeMarker (pos, playerCharacters[countCurrCharacter % numPlayers]);
        screen.setMarker(row, col, playerCharacters[countCurrCharacter % numPlayers]);

        //if the user has won the game
        if (curGame.checkForWinner(pos)) {
            //print the winner information
            screen.setMessage("Player " + playerCharacters[countCurrCharacter % numPlayers] + " wins!\n" +
                                "Click any button to start a new game");
            //set the end of the game to be true
            isEnd = true;
            return;
        }

        //if the game is a draw
        if (curGame.checkForDraw()){
            //print draw information
            screen.setMessage("Draw!\nClick any button to start a new game");
            //set the end of the game to be true
            isEnd = true;
            return;
        }

        //if the game is to continue, update the current character
        countCurrCharacter ++;
        screen.setMessage("It is " + playerCharacters[countCurrCharacter % numPlayers] + "'s turn.");
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     *
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();

        //start back at the set up menu
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}