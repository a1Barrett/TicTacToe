package cpsc2150.extendedTicTacToe.models;
//Abigail Barrett
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to contain our actual gameboard.
 *
 *
 * @author Abigail Barrett
 * @version Project 4.0
 *
 * @invariant 3 < NUM_WIN <= 100 AND
 *              NUM_WIN <= NUM_ROWS AND
 *              NUM_WIN <= NUM_COLUMNS AND
 *              3 <= NUM_ROWS <= 100 AND
 *              3 <= NUM_COLUMNS <= 100 AND
 *              [when accessing the board, BoardPositions be w/i bounds]
 *              -1 < BoardPosition.getRow() < NUM_ROWS AND
 *              -1 < BoardPosition.getColumn() < NUM_COLUMNS
 *
 * @correspondence self = Map<Character, List<BoardPosition>> AND
 *                  number_of_rows = NUM_ROWS AND
 *                  number_of_columns = NUM_COLUMNS AND
 *                  number_of_wins = NUM_WIN
 */


public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    private Map<Character , List<BoardPosition>> board;
    private int NUM_ROWS;
    private int NUM_COLUMNS;
    private int NUM_WIN;


    /**
     * This constructor creates a new Gameboard represented by a Map with a key value pair.  The key is the player's Character.
     *      The value is a List of BoardPositions.
     *
     * @param rows number of rows in the board
     * @param columns number of columns in the board
     * @param num_win how many tokens in a row to win the game
     *
     * @pre 3 <= rows <= 100
     *      AND 3 <= columns <= 100
     *      AND 3 <= num_win <= 25
     *      AND num_win <= columns
     *      AND num_win <= rows
     * @post board = new HashMap<>()
     *       AND NUM_ROWS = rows
     *       AND NUM_COLUMNS = columns
     *       AND NUM_WIN = num_win
     */
    public GameBoardMem(final int rows, final int columns, final int num_win) {
        NUM_ROWS = rows;
        NUM_COLUMNS = columns;
        NUM_WIN = num_win;

        board = new HashMap<>();
    }

    public void placeMarker (BoardPosition marker, char player) {

        //if the character is already on the board
        if (board.containsKey(player)){
            //add the marker to the list of characters
            board.get(player).add(marker);
        }

        //if the character is not on the board already
        else {
            //add a new arraylist to the board
            ArrayList<BoardPosition> addToBoard = new ArrayList<>();
            addToBoard.add(marker);
            board.put(player, addToBoard);
        }
    }

    public char whatsAtPos (BoardPosition pos){

        //loop through the entire map
        for (Map.Entry<Character, List<BoardPosition>> indEntry: board.entrySet()) {

            //if the BoardPosition is in the list
            if (indEntry.getValue().contains(pos)){
                //return the key
                return indEntry.getKey();
            }
        }

        //if there is no character at pos then the pos is empty and contains a whitespace character
        return ' ';
    }

    public int getNumRows() {return NUM_ROWS;}

    public int getNumColumns() {return NUM_COLUMNS;}

    public int getNumToWin() {return NUM_WIN;}


    /**
     * This method returns whether the player is at the BoardPositiion
     *
     * @param pos the BoardPosition that will be checked
     * @param player the character that is compared to the character on the board at pos
     *
     * @return true if the List of BoardPositions at the key, player, contains pos
     *
     * @pre pos.getRow() < getNumRows() AND pos.getRow() >= 0 AND pos.getColumn() < getNumColumns() AND pos.getColumn() >= 0
     * @post board = #board AND isPlayerAtPos [true iff] board.get(player).contains(pos)
     * */
    @Override
    public boolean isPlayerAtPos (BoardPosition pos, char player){

        return board.get(player).contains(pos);
    }
}
