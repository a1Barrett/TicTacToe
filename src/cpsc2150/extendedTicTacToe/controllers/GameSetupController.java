package cpsc2150.extendedTicTacToe.controllers;

import cpsc2150.extendedTicTacToe.models.*;
import cpsc2150.extendedTicTacToe.views.*;

/**
 * <p>The {@link GameSetupController} class will handle communication between our {@link GameSetupScreen}
 * and start a new game when all the required fields to build an {@link IGameBoard} is met.</p>
 <p>
 * If there are no errors it will create a new {@link IGameBoard} object (the implementation will depend on
 * the size of the game board) to serve as the model, and the {@link TicTacToeController} and {@link TicTacToeView}.
 * Control is then passed over the event dispatch thread that will wait for an event to occur
 * <p>
 * No changes need to be made to this class.
 *
 * @version 2.0
 */
public class GameSetupController {

    /**
     * <p>
     * This stores the setup view associated with this controller.
     * </p>
     */
    private GameSetupScreen view;

    /**
     * <p>
     * Constants for minimum size, maximum size, minimum number of
     * tokens required to win and the board cutoff for automatically
     * choosing between Gameboard and GameboardMem.
     * </p>
     */
    private final int MAX_SIZE = 20;

    private final int MIN_SIZE = 3;

    private final int MIN_TO_WIN = 3;

    private final int MEM_CUTOFF = 64;

    /**
     * <p>
     * This creates a new game setup controller.
     * </p>
     *
     * @param v
     *      The setup view associated with this controller.
     */
    public GameSetupController(GameSetupScreen v) {
        view = v;
    }

    /**
     * <p>
     * This processes the submit button click.
     * </p>
     *
     * @param rows
     *      Number of rows for the board
     * @param cols
     *      Number of columns for the board
     * @param players
     *      Number of players for this game
     * @param numWin
     *      Number of tokens in a row required to win
     */
    public void processButtonClick(int rows, int cols, int players, int numWin) {
        String errorMsg = "";
        if (rows < MIN_SIZE || rows > MAX_SIZE) {
            errorMsg += "Rows must be between " + MIN_SIZE + " and " + MAX_SIZE;
        }

        if (cols < MIN_SIZE || cols > MAX_SIZE) {
            errorMsg += "Columns must be between " + MIN_SIZE + " and " + MAX_SIZE;
        }

        if (numWin > rows) {
            errorMsg += "Can't have more to win than the number of rows";
        }

        if (numWin > rows) {
            errorMsg += "Can't have more to win than the number of Columns";
        }

        if (numWin < MIN_TO_WIN) {
            errorMsg += "Number to win must be at least " + MIN_TO_WIN;
        }

        if (!errorMsg.equals("")) {
            view.displayError(errorMsg);
        } else {
            view.closeScreen();
            IGameBoard model;

            // if the board is too big we'll want the memory efficient version
            if (rows * cols <= MEM_CUTOFF) {
                model = new GameBoard(rows, cols, numWin);
            } else {
                model = new GameBoardMem(rows, cols, numWin);
            }

            TicTacToeView tview = new TicTacToeView(rows, cols);
            TicTacToeController tcontroller = new TicTacToeController(model, tview, players);

            tview.registerObserver(tcontroller);
        }
    }
}