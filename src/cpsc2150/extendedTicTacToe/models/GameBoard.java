package cpsc2150.extendedTicTacToe.models;
//Abigail Barrett

/**
 * This class is used to contain our actual GameBoard.
 *
 *
 * @author Abigail Barrett
 * @version Project 4.0
 *
 * @invariant 3 < NUM_WIN <= 25 AND
 *              NUM_WIN <= NUM_ROWS AND
 *              NUM_WIN <= NUM_COLUMNS AND
 *              3 <= NUM_ROWS <= 100 AND
 *              3 <= NUM_COLUMNS <= 100 AND
 *              [when accessing the board, BoardPositions be w/i bounds]
 *              -1 < BoardPosition.getRow() < NUM_ROWS AND
 *              -1 < BoardPosition.getColumn() < NUM_COLUMNS
 *
 * @correspondence self = board[0...NUM_ROWS-1][0...NUM_COLUMNS-1] AND
 *                  number_of_rows = NUM_ROWS AND
 *                  number_of_columns = NUM_COLUMNS AND
 *                  number_to_win = NUM_WIN
 *
 *
 */


public class GameBoard extends AbsGameBoard implements IGameBoard{

    private char[][] board;
    private int NUM_ROWS;
    private int NUM_COLUMNS;
    private int NUM_WIN;

    /**
     * This constructor creates a new Gameboard with a 2D NUM_ROWSxNUM_COLUMNS array of characters.
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
     * @post board[NUM_ROWS][NUM_COLUMNS] = ' '
     *       AND NUM_ROWS = rows
     *       AND NUM_COLUMNS = columns
     *       AND NUM_WIN = num_win
     */
    public GameBoard(final int rows, final int columns, final int num_win){

        NUM_ROWS = rows;
        NUM_COLUMNS = columns;
        NUM_WIN = num_win;

        //create the board
        board = new char[NUM_ROWS][NUM_COLUMNS];

        //loop through the board and make every value an empty character
        for ( int i = 0; i < NUM_ROWS; i++){
            for (int j = 0; j < NUM_COLUMNS; j++){
                board[i][j] = ' ';
            }
        }
    }

    public void placeMarker (BoardPosition marker, char player){
        board[marker.getRow()][marker.getColumn()] = player;
    }

    public char whatsAtPos (BoardPosition pos){
        return board[pos.getRow()][pos.getColumn()];
    }

    public int getNumRows(){
        return NUM_ROWS;
    }

    public int getNumColumns(){
        return NUM_COLUMNS;
    }

    public int getNumToWin(){
        return NUM_WIN;
    }

    //Functions that override interface IGameBoard functions

    /**
     * This method checks to see if the game has resulted in a tie
     * This method is overrided for increased efficiency with access to board
     *
     * @return true if there is no position on board with a single blank space character
     *          false if there is 1 or greater blank space characters on the board
     *
     * @pre checkHorizontalWin(BoardPosition lastPos, char player) == false
     *      AND checkVerticalWin (BoardPosition lastPos, char player) == false
     *      AND checkDiagonalWin (BoardPosition lastPos, char player) == false
     * @post board = #board AND checkForDraw [true iff] board[0...NUM_ROWS][0...NUM_COLUMNS] != ' '
     * */
    @Override
    public boolean checkForDraw(){
        for (int i = 0; i < NUM_ROWS; i++){
            for (int j = 0; j < NUM_COLUMNS; j++){
                if (board[i][j] == ' ') {return false;}
            }
        }
        return true;
    }

    /**
     * This method checks to see if the last marker placed resulted in a getNumToWin() in a row horizontally
     * This method is overrided for increased efficiency with access to board
     *
     * @param lastPos the last placed BoardPosition on the board
     * @param player the last placed character on the board
     *
     * @return true iff 5 characters in a row on the board in lastPos.getRow() have the same character
     *
     * @pre checkSpace(lastPos) == true
     * @post board = #board AND checkHorizontalWin [true iff] board[lastPos.getRow()][0....NUM_COLUMNS] [has the same character in getNumToWin() consecutive positions]
     * */
    @Override
    public boolean checkHorizontalWin (BoardPosition lastPos, char player){
        for (int i = 0; i < NUM_COLUMNS + 1 - NUM_WIN; i ++){
            boolean win = true;

            for (int j = 0; j < NUM_WIN; j++){
                if (board[lastPos.getRow()][i+j] != player) {
                    win = false;
                    break;
                }
            }

            if (win) {return true;}
        }
        return false;
    }


    /**
     * This method checks to see if the last marker placed results in getNumToWin() in a row vertically
     * This method is overrided for increased efficiency with access to board
     *
     * @param lastPos the last placed BoardPosition on the board
     * @param player the last placed character on the board
     *
     * @return true if every row in the column lastPos.getColumn() is the character player
     *
     * @pre checkSpace(lastPos) == true
     * @post board = #board AND checkVerticalWin [true iff] board[0...NUM_ROWS][lastPos.getColumn()] [has the same character]
     * */
    @Override
    public boolean checkVerticalWin (BoardPosition lastPos, char player){
        for (int i = 0; i < NUM_ROWS + 1 - NUM_WIN; i ++) {
            boolean win = true;

            for (int j = 0; j < NUM_WIN; j++) {
                if (board[i + j][lastPos.getColumn()] != player) {
                    win = false;
                    break;
                }
            }

            if (win) {return true;}
        }
        return false;
    }

    /**
     * This method checks to see if the last marker placed results in getNumToWin() in a row diagonally
     * This method is overrided for increased efficiency with access to board
     *
     * @param lastPos the last placed BoardPosition on the board
     * @param player the last placed character on the board
     *
     * @return true if 5 characters on board in a row contain player is in a row diagonally
     *
     * @pre checkSpace(lastPos) == true
     * @post board = #board AND checkDiagonalWin [true iff getNumToWin() diagonal places on the board contain the same character]
     * */
    @Override
    public boolean checkDiagonalWin (final BoardPosition lastPos, char player){

        //check right diagonals
        //keep a count of player in a row diagonally
        int count = 0;

        //all right diagonals have the same sum
        int sum = lastPos.getColumn() + lastPos.getRow();

        //loop through all values in table
        for (int i = 0; i < NUM_ROWS; i++){
            for (int j = 0; j < NUM_COLUMNS; j++){

                //if the current position has the same sum as the lastPos
                if (i + j == sum){

                    //if the character is not the same as player start the count over again
                    if(board[i][j] != player) {
                        count = 0;
                    }
                    //if the character is the same as player increase the count
                    else {
                        count ++;
                    }
                }

                //if the count equals the number to win return true
                if(count == NUM_WIN) {return true;}
            }
        }

        //check left diagonals
        //keep a count of player in a row diagonally
        count = 0;

        //the sum of left diagonals is the column - row
        sum = lastPos.getColumn() - lastPos.getRow();

        //loop through the entire table
        for (int i = 0; i < NUM_ROWS; i++){
            for (int j = 0; j < NUM_COLUMNS; j++){

                //if the current position has the same sum as lastPos
                if (j - i == sum){

                    //if the character is not the same as player start the count over again
                    if(board[i][j] != player) {
                        count = 0;
                    }

                    //if the character is the same as player increase the count
                    else {
                        count ++;
                    }
                }

                //if the count equals the number to win return true
                if(count == NUM_WIN) {return true;}
            }
        }
        return false;
    }
}
