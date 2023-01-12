package cpsc2150.extendedTicTacToe.models;
//Abigail Barrett

/**
 * An abstract class that contains an overridden
 * implementation of the toString() method.
 */

public abstract class AbsGameBoard implements IGameBoard{

    /**
     * This method overrides the toString() function.
     * This method creates a string that shows the entire game board.
     *
     * @return a string representation of GameBoard
     *
     * @post  self = #self AND
     *        toString = [ (getNumRows()+1) x (getNumColumns()+1) grid of GameBoard where first column and row are position numbers 0-getNumRows() and 0-getNumColumns]
     *
     */
    @Override
    public String toString(){
        String str = "   ";

        //list all the column numbers across the top
        for (int i = 0; i < getNumColumns(); i ++){

            //if the number is not a double-digit add an extra space
            if (i < 10){
                str = str.concat(" ");
            }
            //add the column number followed by |
            str = str.concat(  i + "|");
        }
        str = str.concat( "\n");

        //list all the row numbers at the start of each row
        for (int i = 0; i < getNumRows(); i++) {

            //if the number of rows is double-digits add an extra space for numbers that are not double-digits
            if ( i < 10){
                str = str.concat(" " + i + "|");
            }
            else {
                str = str.concat(i + "|");
            }

            //list all the values in the row
            for (int j = 0; j < getNumColumns(); j++) {
                BoardPosition pos = new BoardPosition(i,j);
                str = str.concat(whatsAtPos(pos) + " |");
            }
            str = str.concat( "\n");
        }

        return str;
    }
}
