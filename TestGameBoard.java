package cpsc2150.extendedTicTacToe.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {

    private final int MIN_ROW_COL_NUM = 3;
    private final int MAX_ROW_COL = 100;
    private final int MAX_NUM = 25;

    /**
     * Helper function to create a 2D character array that contains whitespaces
     *
     * @pre MIN_ROW_COL_NUM <= row <= MAX_ROW_COL AND
     *      MIN_ROW_COL_NUM <= col <= MAX_ROW_COL
     *
     * @param row is the number of rows in the 2D array
     * @param col is the number of columns in the 2D array
     *
     * @post char[][] expected = new char[row][col] [where] char[0..row][0..col] = ' '
     *
     * @return a 2D character array full of whitespaces
     */
    private char[][] createBoard(int row, int col){
        char[][] expected = new char[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++ ){
                expected[i][j] = ' ';
            }
        }
        return expected;
    }

    /**
     * Helper function to add a character to a 2D character array
     *
     * @pre MIN_ROW_COL_NUM <= row <= MAX_ROW_COL AND
     *      MIN_ROW_COL_NUM <= col <= MAX_ROW_COL
     *
     * @param row is the number of rows in the 2D array
     * @param col is the number of columns in the 2D array
     * @param marker is the character that will be placed into the 2D character array
     * @param expected is the 2D character array marker will be added to
     *
     * @post expected[row][col] = marker
     *
     */
    private void addToBoard(char[][] expected, int row, int col, char marker){
        expected[row][col] = Character.toUpperCase(marker);
    }

    /**
     * Helper function to convert a 2D character array to a string
     *
     * @pre MIN_ROW_COL_NUM <= row <= MAX_ROW_COL
     * @pre MIN_ROW_COL_NUM <= col <= MAX_ROW_COL
     *
     * @param r is the number of rows in the 2D array
     * @param c is the number of columns in the 2D array
     * @param expected is a 2D array
     *
     * @post string format [numbered columns and rows] AND [ " |" in between every value]]
     *
     * @return a string representation of expected
     */
    private String convertToString(char[][]expected, int r, int c){

        String str = "   ";

        //list all the column numbers across the top
        for (int i = 0; i < c; i ++){

            //if the number is not a double-digit add an extra space
            if (i < 10) str = str.concat(" ");

            //add the column number followed by |
            str = str.concat(  i + "|");
        }
        str = str.concat( "\n");

        //list all the row numbers at the start of each row
        for (int i = 0; i < r; i++) {

            //if the number of rows is double-digits add an extra space for numbers that are not double-digits
            if ( i < 10) str = str.concat(" ");
            str = str.concat(i + "|");

            //list all the values in the row
            for (int j = 0; j < c; j++) str = str.concat(expected[i][j] + " |");
            str = str.concat( "\n");
        }

        return str;
    }

    /**
     * Helper function that creates a GameBoard object
     * Implementation of factory method
     *
     * @pre MIN_ROW_COL_NUM <= row <= MAX_ROW_COL
     * @pre MIN_ROW_COL_NUM <= col <= MAX_ROW_COL
     * @pre MIN_ROW_COL_NUM <= num_win <= MAX_NUM
     *
     * @param row is the number of rows in the GameBoard
     * @param col is the number of columns in the GameBoard
     * @param num_win is the number of wins it takes to win the game
     *
     * @post GameBoard board [row number of rows, col number of columns, num_win number tokens in a row to win]
     *
     * @return an IGameBoard object
     */
    private IGameBoard makeAGameBoard(int row, int col, int num_win)  {return new GameBoard(row, col, num_win);}



    //************************** Start Constructor *****************************/

    @Test
    public void testConstructor_min_num_row_col(){

        char[][] expected = createBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);

        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testConstructor_max_num_row_col(){

        char[][]expected = createBoard(MAX_ROW_COL, MAX_ROW_COL);

        IGameBoard gb = makeAGameBoard(MAX_ROW_COL,MAX_ROW_COL,MAX_NUM);

        assertEquals(convertToString(expected, MAX_ROW_COL, MAX_ROW_COL), gb.toString());
    }

    @Test
    public void testConstructor_diff_num_row_col(){

        char[][]expected = createBoard(5, 7);
        IGameBoard gb = makeAGameBoard(5,7,4);

        assertEquals(convertToString(expected, 5, 7), gb.toString());
    }

    //************************** End Constructor *****************************/



    //************************** Start CheckSpace *****************************/

    @Test
    public void testCheckSpace_empty_space(){

        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);
        addToBoard(expected, 0,0,'X');
        addToBoard(expected, 2, 0, 'O');

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');

        assertTrue( gb.checkSpace(new BoardPosition(1,0)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testCheckSpace_not_space(){

        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);
        addToBoard(expected, 0,0,'X');
        addToBoard(expected, 2, 0, 'O');

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        gb.placeMarker(new BoardPosition(0,0), 'X');
        gb.placeMarker(new BoardPosition(2,0), 'O');

        assertFalse( gb.checkSpace(new BoardPosition(0,0)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testCheckSpace_full_space(){

        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);

        for (int i = 0; i < MIN_ROW_COL_NUM; i++){
            for (int j = 0; j < MIN_ROW_COL_NUM; j++){
                if ( i == 2 || ( i==1 && j == 1)) {
                    addToBoard(expected, i, j, 'O');
                    gb.placeMarker(new BoardPosition(i,j),'O');
                }
                else {
                    addToBoard(expected, i, j,'X');
                    gb.placeMarker(new BoardPosition(i,j), 'X');
                }
            }
        }

        assertFalse( gb.checkSpace(new BoardPosition(1,0)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End CheckSpace *****************************/



    //************************** Start CheckHorizontalWin *****************************/

    @Test
    public void testHorizontalWin_is_Win_Add_Middle(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i == 0 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2 && j != 2){
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j , 'O');
                }
            }
        }

        assertTrue(gb.checkHorizontalWin(new BoardPosition(0,1), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testHorizontalWin_no_Win(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i == 0 && j!= 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i , j, 'X');
                }
                else if (i == 2 && j != 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertFalse(gb.checkHorizontalWin(new BoardPosition(0,1), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testHorizontalWin_is_Win_Add_Right(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i == 0 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i , j , 'X');
                }
                else if (i == 2 && j != 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i , j, 'O');
                }
            }
        }

        assertTrue( gb.checkHorizontalWin(new BoardPosition(0,2), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testHorizontalWin_is_Win_add_left(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][]expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i == 0 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2 && j != 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkHorizontalWin(new BoardPosition(0,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End CheckHorizontalWin *****************************/



    //************************** Start CheckVerticalWin *****************************/

    @Test
    public void testVerticalWin_is_Win_Add_Middle(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (j == 0 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i ,j , 'X');
                }
                else if (j == 1 && i != 0) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i ,j , 'O');
                }
            }
        }

        assertTrue(gb.checkVerticalWin(new BoardPosition(1,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testVerticalWin_No_Win(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (j == 0 && i != 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i ,j , 'X');
                }
                else if (i == 2 && j == 0) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i ,j , 'O');
                }
            }
        }

        assertFalse(gb.checkHorizontalWin(new BoardPosition(1,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testVerticalWin_is_Win_Add_Upper(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (j == 0 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i ,j , 'X');
                }
                else if (j == 1 && i != 0) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i ,j , 'O');
                }
            }
        }

        assertTrue(gb.checkVerticalWin(new BoardPosition(0,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testVerticalWin_is_Win_Add_Lower(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (j == 0 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (j == 1 && i != 0) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkVerticalWin(new BoardPosition(2,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End CheckVerticalWin *****************************/



    //************************** Start CheckDiagonalWin *****************************/

    @Test
    public void testDiagonalWin_is_Win_Add_Middle_Left_Diagonal(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (j == i) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i , j, 'X');
                }
                else if (i == 2 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkDiagonalWin(new BoardPosition(1,1), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testDiagonalWin_is_Win_Add_UpLeft_Left_Diagonal(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,4,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, 4);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == (i+1)) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2 && j < 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkDiagonalWin(new BoardPosition(0,1), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, 4), gb.toString());
    }

    @Test
    public void testDiagonalWin_is_Win_Add_LowRight_Left_Diagonal(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (j == i) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i , j , 'X');
                }
                else if (i == 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkDiagonalWin(new BoardPosition(2,2), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testDiagonalWin_is_Win_Add_Middle_Right_Diagonal(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i + j == 2 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkDiagonalWin(new BoardPosition(1,1), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testDiagonalWin_is_Win_Add_UpRight_Right_Diagonal(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i + j == 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkDiagonalWin(new BoardPosition(0,2), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testDiagonalWin_is_Win_Add_LowLeft_Right_Diagonal(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MAX_ROW_COL);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i + j == 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2 ) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue(gb.checkDiagonalWin(new BoardPosition(2,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testDiagonalWin_no_Win(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if (i == j && (i != 2)) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (i == 2) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertFalse(gb.checkDiagonalWin(new BoardPosition(2,2), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End CheckDiagonalWin *****************************/



    //************************** Start CheckForDraw *****************************/

    @Test
    public void testCheckForDraw_is_Partially_Full(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if ( (j==0 && i != 2) || (j==1 && i != 2 ) ){
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else if (j != 1) {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertFalse(gb.checkForDraw());
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testCheckForDraw_is_Empty(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][]expected = createBoard(MIN_ROW_COL_NUM, MAX_ROW_COL);

        assertFalse(gb.checkForDraw() );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testCheckForDraw_is_Draw(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if ( (j==0 && i != 2) || (j==1 && i != 2 ) ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertTrue( gb.checkForDraw() );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testCheckForDraw_is_Full_Max_Board(){

        IGameBoard gb = makeAGameBoard(MAX_ROW_COL, MAX_ROW_COL, MAX_NUM);
        char[][] expected = createBoard(MAX_ROW_COL, MAX_ROW_COL);

        //list all the values
        for (int i = 0; i < MAX_ROW_COL; i++) {
            for (int j = 0; j < MAX_ROW_COL; j++) {
                if ( ( (j%2 == 1) && (i%3 != 2) ) || (j%2 == 0 && i%3 ==2) ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j , 'O');
                }
            }
        }

        assertTrue( gb.checkForDraw() );
        assertEquals(convertToString(expected, MAX_ROW_COL, MAX_ROW_COL), gb.toString());
    }

    //************************** End CheckForDraw *****************************/



    //************************** Start WhatsAtPos *****************************/

    @Test
    public void testWhatsAtPos_is_Empty(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        assertEquals(' ', gb.whatsAtPos(new BoardPosition(0,0)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testWhatsAtPos_is_X_Unique_Char(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        addToBoard(expected, 0, 0, 'X');

        assertEquals('X', gb.whatsAtPos(new BoardPosition(0,0)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testWhatsAtPos_is_O_Dist_Char(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'O');
        addToBoard(expected, 0, 0, 'O');

        assertEquals('O', gb.whatsAtPos(new BoardPosition(0,0)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testWhatsAtPos_is_Dist_Char_Lower_Corner(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(2,2), 'X');
        addToBoard(expected, 2, 2, 'X');

        assertEquals('X', gb.whatsAtPos(new BoardPosition(2,2)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testWhatsAtPos_is_Dist_Char_Full_Upper_Right(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        //list all the values
        for (int i = 0; i < MIN_ROW_COL_NUM; i++) {
            for (int j = 0; j < MIN_ROW_COL_NUM; j++) {
                if ( (j==0 && i != 2) || (j==1 && i != 2 ) ) {
                    gb.placeMarker(new BoardPosition(i, j), 'X');
                    addToBoard(expected, i, j, 'X');
                }
                else {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertEquals('O', gb.whatsAtPos(new BoardPosition(0,2)) );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End WhatsAtPos *****************************/



    //************************** Start IsPlayerAtPos *****************************/

    @Test
    public void testisPlayerAtPos_is_Empty_vs_Unique_Player(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][]expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        assertFalse(gb.isPlayerAtPos(new BoardPosition(0,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testisPlayerAtPos_is_empty_vs_empty_Player(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard(MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,0), ' ') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testisPlayerAtPos_is_true(){

        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        addToBoard(expected, 0, 0, 'X' );

        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testisPlayerAtPos_is_false(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'O');
        addToBoard(expected, 0, 0, 'O' );

        assertFalse(gb.isPlayerAtPos(new BoardPosition(0,0), 'X') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testisPlayerAtPos_is_new_Char(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(1,0), 'X');
        addToBoard(expected, 1, 0, 'X' );
        gb.placeMarker(new BoardPosition(1,1), 'O');
        addToBoard(expected, 1, 1, 'O' );

        assertFalse(gb.isPlayerAtPos(new BoardPosition(1,1), 'A') );
        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End IsPlayerAtPos *****************************/



    //************************** Start PlaceMarker *****************************/

    @Test
    public void testPlaceMarker_Empty(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'X');
        addToBoard(expected, 0, 0, 'X' );

        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testPlaceMarker_Almost_Full(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        for (int i = 0; i < MIN_ROW_COL_NUM; i++){
            for (int j = 0; j < MIN_ROW_COL_NUM; j++){
                if ((i == 2 && j != 0) || (i == 0 && j == 1) || (i == 1 && j == 0)){
                    gb.placeMarker(new BoardPosition(i,j), 'X');
                    addToBoard(expected, i, j, 'X' );
                }
                else {
                    gb.placeMarker(new BoardPosition(i, j), 'O');
                    addToBoard(expected, i, j, 'O');
                }
            }
        }

        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testPlaceMarker_New_Char(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'O');
        addToBoard(expected, 0, 0, 'O' );
        gb.placeMarker(new BoardPosition(1,0), 'X');
        addToBoard(expected, 1, 0, 'X' );
        gb.placeMarker(new BoardPosition(2,0), 'A');
        addToBoard(expected, 2, 0, 'A' );

        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testPlaceMarker_Upper_Right(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'O');
        addToBoard(expected, 0, 0, 'O' );
        gb.placeMarker(new BoardPosition(1,0), 'X');
        addToBoard(expected, 1, 0, 'X' );
        gb.placeMarker(new BoardPosition(0,2), 'A');
        addToBoard(expected, 0, 2, 'A' );

        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    @Test
    public void testPlaceMarker_Lower_Right(){
        IGameBoard gb = makeAGameBoard(MIN_ROW_COL_NUM,MIN_ROW_COL_NUM,MIN_ROW_COL_NUM);
        char[][] expected = createBoard( MIN_ROW_COL_NUM, MIN_ROW_COL_NUM);

        gb.placeMarker(new BoardPosition(0,0), 'O');
        addToBoard(expected, 0, 0, 'O' );
        gb.placeMarker(new BoardPosition(1,0), 'X');
        addToBoard(expected, 1, 0, 'X' );
        gb.placeMarker(new BoardPosition(2,2), 'A');
        addToBoard(expected, 2, 2, 'A' );

        assertEquals(convertToString(expected, MIN_ROW_COL_NUM, MIN_ROW_COL_NUM), gb.toString());
    }

    //************************** End PlaceMarker *****************************/
}
