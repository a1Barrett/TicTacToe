package cpsc2150.extendedTicTacToe.models;
//Abigail Barrett

/**
 * A GameBoard is a 2D board of characters that allows you to insert characters onto the board.
 *
 * @author Abigail Barrett
 * @version Project 4.0
 *
 * @InitializationEnsures
 *      GameBoard contains nothing, i.e., a blank character and
 *      is number_of_rows * number_of_columns
 *
 * @Defines
 *      size_of_gameboard: Z
 *      number_of_rows: Z
 *      number_of_columns: Z
 *      number_to_win: Z
 *
 * @Constraints
 *      size of a GameBoard = number_of_rows * number_of_columns
 *
 */


public interface IGameBoard {

    //Primary Functions


    /**
     * This method places the character player in marker on the position specified by the marker
     *
     * @param marker the position on the board to place the character player
     * @param player the character that will be placed on the board
     *
     *
     * @pre checkSpace(marker) == true
     * @post board[marker.getRow()][marker.getColumn()] = player
     */
    void placeMarker (BoardPosition marker, char player);

    /**
     * This method returns the character on the board at BoardPosition pos
     *
     * @param pos the BoardPosition that will be checked
     *
     * @return the character contained on the board at BoardPosition pos
     *
     * @pre pos.getRow() < getNumRows() AND pos.getRow() >= 0 AND pos.getColumn() < getNumColumns() AND pos.getColumn() >= 0
     * @post board = #board AND whatsAtPos = board[pos.getRow()][pos.getColumn()]
     * */
    char whatsAtPos (BoardPosition pos);

    /**
     * This method returns the number of rows in the GameBoard
     *
     * @return number of rows in the GameBoard
     * @post getNumRows = board[0].length && board = #board
     */
    int getNumRows();

    /**
     *This method returns the number of columns in the GameBoard
     *
     * @return number of columns in the GameBoard
     * @post getNumColumns = board.length && board = #board
     */
    int getNumColumns();

    /**
     * This method returns the number of tokens in a row needed to win the game
     *
     * @return number of tokens in a row needed to win the game
     * @post board = #board && getNumToWin = [num of tokens to win the game]
     */
    int getNumToWin();


    //Secondary Functions


    /**
     * This method determines if a position is available to place a token on
     *
     * @param pos the BoardPosition that will be checked
     *
     * @return true if the character on the board at BoardPosition pos is ' ' (a whitespace character) and pos is in bounds
     *
     * @post board = #board AND checkSpace [true iff] ( pos.getRow() < getNumRows() AND pos.getRow() >= 0 AND pos.getColumn() < getNumColumns()
     *                                                  AND pos.getColumn() >= 0 AND whatsAtPos(pos) == ' ' )
     */
    default boolean checkSpace (BoardPosition pos){
        if (0 <= pos.getRow() && pos.getRow() < getNumRows() && 0 <= pos.getColumn() && pos.getColumn() < getNumColumns()){
            if (whatsAtPos(pos) == ' ') {return true;}
        }
        return false;
    }

    /**
     * This method checks to see if the last position resulted in a winner
     *
     * @param lastPos the location (BoardPosition) that was last player marker placed on the board
     *
     * @return true if the character at the last position placed on the board BoardPosiiton lastPos
     *              resulted in a horizontal, vertical, or diagonal win
     *
     * @pre checkSpace(lastPos) == true AND lastPos = [BoardPosition on last play]
     * @post board = #board AND checkForWinner [true iff] ( checkHorizontalWin ( lastpos, whatsAtPos(lastPos) ) == true
     *                                                      OR checkVerticalWin ( lastpos, whatsAtPos(lastPos) ) == true
     *                                                      OR checkDiagonalWin ( lastpos, whatsAtPos(lastPos) ) == true )
     */
    default boolean checkForWinner (BoardPosition lastPos){

        //if the player has one in any way return true
        if (checkHorizontalWin(lastPos, whatsAtPos(lastPos))
            || checkVerticalWin(lastPos, whatsAtPos(lastPos))
            || checkDiagonalWin(lastPos, whatsAtPos(lastPos))){

            return true;
        }
        return false;
    }

    /**
     * This method checks to see if the game has resulted in a tie
     *
     *
     * @return true if there is no position on board with a single blank space character
     *          false if there is 1 or greater blank space characters on the board
     *
     * @pre checkHorizontalWin(BoardPosition lastPos, char player) == false
     *      AND checkVerticalWin (BoardPosition lastPos, char player) == false
     *      AND checkDiagonalWin (BoardPosition lastPos, char player) == false
     * @post board = #board AND checkForDraw [true iff] checkSpace( BoardPosition pos( 0...getNumRows() - 1, 0...getNumColumns() - 1 ) == false
     * */
    default boolean checkForDraw(){
        //loop through the rows
        for (int i = 0; i < getNumRows(); i++){

            //loop through the column
            for (int j = 0; j < getNumColumns(); j++){

                //create a boardPosition
                BoardPosition pos = new BoardPosition(i,j);

                //if there is an empty space at pos it is not a draw
                if(checkSpace(pos)) {return false;}
            }
        }
        return true;
    }

    /**
     * This method checks to see if the last marker placed resulted in getNumToWin() in a row horizontally
     *
     * @param lastPos the last placed BoardPosition on the board
     * @param player the last placed character on the board
     *
     * @return true iff getNumToWin() characters in a row on the board in lastPos.getRow() have the same character
     *
     * @pre checkSpace(lastPos) == true
     * @post board = #board AND checkHorizontalWin [true iff] whatsAtPos( pos( lastPos.getRow(), 0...getNumColumns() ) == player [in getNumToWin() consecutive positions]
     * */
    default boolean checkHorizontalWin (BoardPosition lastPos, char player){

        //loop through the column
        for (int j = 0; j < getNumColumns() + 1 - getNumToWin(); j++){
            boolean win = true;

            //loop through NUM_TO_WIN columns ahead of the current column
            for (int k = 0; k < getNumToWin(); k++){

                BoardPosition pos = new BoardPosition (lastPos.getRow(), j + k);

                if (!isPlayerAtPos(pos,player)) {win = false;}
            }
            if (win) {return true;}
        }
        return false;
    }

    /**
     * This method checks to see if the last marker placed results in getNumToWin() in a row vertically
     *
     * @param lastPos the last placed BoardPosition on the board
     * @param player the last placed character on the board
     *
     * @return true if every row in the column lastPos.getColumn() is the character player
     *
     * @pre checkSpace(lastPos) == true
     * @post board = #board AND checkVerticalWin [true iff] whatsAtPos( pos(0...getNumRows(), lastPos.getColumn() ) == player [in getNumToWin() consecutive positions]
     * */
    default boolean checkVerticalWin (BoardPosition lastPos, char player){

        for (int i = 0; i < getNumRows() + 1 - getNumToWin(); i++){
            boolean win = true;

            for (int j = 0; j < getNumToWin(); j++){
                BoardPosition pos = new BoardPosition(i + j, lastPos.getColumn());

                if (!isPlayerAtPos(pos,player)) {win = false;}
            }
            if (win) {return true;}
        }
        return false;
    }

    /**
     * This method checks to see if the last marker placed results in getNumToWin() in a row diagonally
     *
     * @param lastPos the last placed BoardPosition on the board
     * @param player the last placed character on the board
     *
     * @return true if getNumToWin() characters on board in a row contain player is in a row diagonally
     *
     * @pre checkSpace(lastPos) == true
     * @post board = #board AND checkDiagonalWin [true iff getNumToWin() diagonal places on the board contain the same character]
     * */
    default boolean checkDiagonalWin (BoardPosition lastPos, char player){

        //check right diagonals
        //keep a count of player in a row diagonally
        int count = 0;

        //all right diagonals have the same sum
        int sum = lastPos.getColumn() + lastPos.getRow();

        //loop through all values in table
        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumColumns(); j++){

                //if the current position has the same sum as the lastPos
                if (i + j == sum){

                    BoardPosition pos = new BoardPosition(i,j);

                    //if the character is not the same as player start the count over again
                    if(!isPlayerAtPos(pos,player)) {
                        count = 0;
                    }
                    //if the character is the same as player increase the count
                    else {
                        count ++;
                    }
                }

                //if the count equals the number to win return true
                if(count == getNumToWin()) {return true;}
            }
        }

        //check left diagonals
        //keep a count of player in a row diagonally
        count = 0;

        //the sum of left diagonals is the column - row
        sum = lastPos.getColumn() - lastPos.getRow();

        //loop through the entire table
        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumColumns(); j++){

                //if the current position has the same sum as lastPos
                if (j - i == sum){

                    BoardPosition pos = new BoardPosition(i,j);

                    //if the character is not the same as player start the count over again
                    if(!isPlayerAtPos(pos,player)) {
                        count = 0;
                    }

                    //if the character is the same as player increase the count
                    else {
                        count ++;
                    }
                }

                //if the count equals the number to win return true
                if(count == getNumToWin()) {return true;}
            }
        }
        return false;
    }


    /**
     * This method returns whether the player is at the BoardPositiion
     *
     * @param pos the BoardPosition that will be checked
     * @param player the character that is compared to the character on the board at pos
     *
     * @return true if the character on the board at BoardPosition has the same character as the character player
     *
     * @pre pos.getRow() < getNumRows() AND pos.getRow() >= 0 AND pos.getColumn() < getNumColumns() AND pos.getColumn() >= 0
     * @post board = #board AND isPlayerAtPos [true iff] whatsAtPos(pos) == player
     * */
    default boolean isPlayerAtPos (BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }
}
