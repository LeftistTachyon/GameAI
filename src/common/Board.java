package common;

/**
 * A class that represents any game board.
 * @author Jed Wang
 */
public abstract class Board {
    /**
     * The board
     */
    protected AbstractPiece[][] board;
    
    /**
     * Whether the current player is white
     */
    protected boolean playerIsWhite;
    
    /**
     * A private instantiator for basic stuff.
     */
    private Board() {
        playerIsWhite = true;
    }
    
    /**
     * Instantiates a Board with a set number of rows and columns
     * @param rows the number of rows in a board
     * @param columns the number of columns in a board
     */
    public Board(int rows, int columns) {
        this();
        board = new AbstractPiece[rows][columns];
        boardSetup();
    }
    
    /**
     * Instantiates a Board from an existing Board
     * @param b the Board to copy
     */
    public Board(Board b) {
        this(b.board.length, b.board[0].length);
        for(int i = 0; i < b.board.length; i++) {
            System.arraycopy(b.board[i], 0, board[i], 0, b.board[i].length);
        }
    }
    
    /**
     * Places all needed pieces onto the starting position.
     */
    protected abstract void boardSetup();
    
    /**
     * Determines which piece occupies a square
     * @param square a square
     * @return the piece on that square, and if none, null
     */
    public AbstractPiece getPiece(String square) {
        if(isValidSquare(square)) {
            return board[getColumn(square)][getRow(square)];
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines which piece occupies a space represented by ABSOLUTE coordinates<br>
     * i.e. (0, 0) represents the top left corner
     * @param col the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return the piece on that square, and if none, null
     */
    public AbstractPiece getPiece(int col, int row) {
        if(isValidSquare(col, row)) {
            return board[col][row];
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines whether a square is empty
     * @param square a square
     * @return whether that square is empty
     */
    public boolean isEmptySquare(String square) {
        return getPiece(square) == null;
    }
    
    /**
     * Determines whether a space represented by ABSOLUTE coordinates is empty
     * @param col the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return whether that square is empty
     */
    public boolean isEmptySquare(int col, int row) {
        return getPiece(col, row) == null;
    }
    
    /**
     * Determines the validity of the square
     * @param s a square
     * @return whether the square is valid
     */
    public static boolean isValidSquare(String s) {
        if(s == null) return false;
        if(s.length() == 2) {
            int col = s.charAt(0)-'a', 
                    row = 8 - Integer.parseInt(s.charAt(1) + "");
            return Character.isLowerCase(s.charAt(0)) && 
                    Character.isDigit(s.charAt(1)) && isValidSquare(col, row);
        } else return false;
    }
    
    /**
     * Determines the validity of the square
     * @param col the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return whether the square is valid
     */
    public static boolean isValidSquare(int col, int row) {
        return col >= 0 && col <= 7 && row >= 0 && row <= 7;
    }
    
    /**
     * Determines which column a square is referring to<br>
     * <br>
     * The columns are ordered as such:<br>
     * |_|_|_|_|_|_|_|_|<br>
     * |0 1 2 3 4 5 6 7<br>
     * |a b c d e f g h
     * @param s a square
     * @return which column the String is referring to
     */
    public static int getColumn(String s) {
        if(isValidSquare(s)) {
            return s.charAt(0)-'a';
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines which row a square is referring to<br>
     * <br>
     * The rows are ordered as such:<br>
     * ____<br>
     * 0 |_<br>
     * 1 |_ <br>
     * 2 |_<br>
     * 3 |_<br>
     * 4 |_<br>
     * 5 |_<br>
     * 6 |_<br>
     * 7 |_<br>
     * ___W
     * @param s the square
     * @return the column / file
     */
    public static int getRow(String s) {
        if(isValidSquare(s)) {
            return 8 - Integer.parseInt(s.charAt(1) + "");
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines where a square is after a shift (a.k.a. moving it left and right, up and down)
     * @param col current column
     * @param row current row
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return the shifted square
     */
    public static String shiftSquare(int col, int row, int colShift, int rowShift) {
        if(isValidSquare(col, row)) {
            int shiftedCol = col + colShift, shiftedRow = row + rowShift;
            if(isValidSquare(shiftedCol, shiftedRow)) {
                return toSquare(shiftedCol, shiftedRow);
            } else throw new IllegalArgumentException("Invalid shift");
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Determines where a square is after a shift (a.k.a. moving it left and right, up and down)
     * @param s the current square
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return the shifted square
     */
    public static String shiftSquare(String s, int colShift, int rowShift) {
        if(isValidSquare(s)) {
            int col = getColumn(s), row = getRow(s);
            int shiftedCol = col + colShift, shiftedRow = row + rowShift;
            if(isValidSquare(shiftedCol, shiftedRow)) {
                return toSquare(shiftedCol, shiftedRow);
            } else throw new IllegalArgumentException("Invalid shift");
        } else throw new IllegalArgumentException("Invalid square");
    }
    
    /**
     * Checks if a shift is valid
     * @param col current column
     * @param row current row
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return whether the shift is valid
     */
    public static boolean isValidShift(int col, int row, int colShift, int rowShift) {
        if(isValidSquare(col, row)) {
            int shiftedCol = col + colShift, shiftedRow = row + rowShift;
            return isValidSquare(shiftedCol, shiftedRow);
        } else return false;
    }
    
    /**
     * Checks if this shift is valid
     * @param s current square
     * @param colShift how much to shift the columns
     * @param rowShift how much to shift the rows
     * @return whether the shift is valid
     */
    public static boolean isValidShift(String s, int colShift, int rowShift) {
        return isValidShift(
                Board.getColumn(s), Board.getRow(s), 
                colShift, rowShift
        );
    }
    
    /**
     * Determines the square represented by the row and column
     * @param column the ABSOLUTE column
     * @param row the ABSOLUTE row
     * @return the square that is represented by the row and column
     */
    public static String toSquare(int column, int row) {
        return "" + (char)('a' + column) + (8 - row);
    }

    /**
     * Returns the current state of the game
     * @return the current state of the game
     */
    public AbstractPiece[][] getBoard() {
        return board;
    }
    
    /**
     * Moves a piece from fromWhere to toWhere
     * @param fromWhere from where a piece is moved
     * @param toWhere where to move a piece
     */
    public void movePiece(String fromWhere, String toWhere) {
        movePiece(
                Board.getColumn(fromWhere), 
                Board.getRow(fromWhere), 
                Board.getColumn(toWhere), 
                Board.getRow(toWhere)
        );
    }
    
    /**
     * Moves a piece from fromWhere(X, Y) to toWhere(X, Y)
     * @param fromWhereX from where a piece is moved
     * @param fromWhereY from where a piece is moved
     * @param toWhereX where to move a piece
     * @param toWhereY where to move a piece
     */
    public void movePiece(int fromWhereX, int fromWhereY, int toWhereX, int toWhereY) {
        pieceFromTo(fromWhereX, fromWhereY, toWhereX, toWhereY);
        // After move stuff
        playerIsWhite = !playerIsWhite;
    }
    
    /**
     * Moves a piece from fromWhere(X, Y) to toWhere(X, Y)
     * @param fromWhereX from where a piece is moved
     * @param fromWhereY from where a piece is moved
     * @param toWhereX where to move a piece
     * @param toWhereY where to move a piece
     */
    public abstract void pieceFromTo(int fromWhereX, int fromWhereY, int toWhereX, int toWhereY);

    /**
     * Used to check whether this move is legal
     * @param fromWhere from where to move a piece
     * @param toWhere to where to move a piece
     */
    public void maybeMove(String fromWhere, String toWhere) {
        maybeMove(
                getColumn(fromWhere), getRow(fromWhere), 
                getColumn(toWhere), getRow(toWhere)
        );
    }
    
    /**
     * Used to check whether this move is legal
     * @param fromWhereX from which column to move a piece
     * @param fromWhereY from which row to move a piece
     * @param toWhereX to which column to move a piece
     * @param toWhereY to which row to move a piece
     */
    public void maybeMove(int fromWhereX, int fromWhereY, int toWhereX, int toWhereY) {
        board[toWhereX][toWhereY] = board[fromWhereX][fromWhereY];
        board[fromWhereX][fromWhereY] = null;
    }
}