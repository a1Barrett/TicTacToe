package cpsc2150.extendedTicTacToe.models;
//Abigail Barrett

/**
 * This class will be used to keep track of an individual cell for a board.
 * BoardPosition will have variables to represent the Row position and the Column position.
 *
 * @author Abigail Barrett
 * @version 4.0
 *
 */

public class BoardPosition {

    private int row;
    private int column;

    /**
     * This constructor stores the information row and column position of an individual cell for a board.
     * This constructor is the only way to set row and column
     *
     * @param r represents the row of an individual cell
     * @param c represents the column of an individual cell
     *
     * @post row = r AND column = c
     */
    public BoardPosition (int r, int c){
        row = r;
        column = c;
    }

    /**
     * This method returns the value in row.
     *
     * @return row of BoardPosition
     *
     * @post row = #row AND column = #column AND getRow = row
     *
     */
    public int getRow(){
        return row;
    }

    /**
     * This method returns the value in column.
     *
     * @return column of BoardPosition
     *
     * @post row = #row AND column = #column AND getColumn = column
     *
     */
    public int getColumn(){
        return column;
    }

    /**
     * This method overrides the toString() function.
     * This method creates a string in the format "<row>, <column>"
     *
     * @return str the string representing the BoardPosition's row and column
     *
     * @post row = #row
     * @post column = #column
     * @post toString = "[row],[column]"
     *
     */
    @Override
    public String toString(){
        return row + "," + column;
    }

    /**
     * This method overrides the equals() function.
     * This method returns true if two BoardPositions have the same row and column.
     * It returns false if the object is not a BoardPosition.
     *
     * @param obj the Object that will be compared to this BoardPosition object
     *
     * @return true iff two BoardPosition have the same row and column and false otherwise
     * *
     * @post row = #row AND column = #column AND equals[true iff] (two BoardPositions) && ( this.row = obj.getRow() AND this.column = obj.getColumn() )
     *
     */
    @Override
    public boolean equals (Object obj){

        //if the object is compared to its exact same memory address return true
        if (obj == this){
            return true;
        }

        //if the objects are not the same return false
        if ( !(obj instanceof BoardPosition)){
            return false;
        }

        BoardPosition pos = (BoardPosition) obj;

        //if the row and column are the same return true
        return row == pos.row && column == pos.column;

    }
}
