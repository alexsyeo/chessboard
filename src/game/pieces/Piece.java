package game.pieces;

import java.util.List;

import game.Board;
import game.Position;

public abstract class Piece {
    protected boolean isWhite;
    protected boolean hasMoved;
    protected int row, column;
    protected Board board;

    public Piece() {
        
    }

    public Piece(int row, int column, Board board) {
        this(row, column, false, board);
    }

    public Piece(int row, int column, boolean isWhite, Board board) {
        this.isWhite = isWhite;
        this.hasMoved = false;
        this.board = board;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isRook() {
        return false;
    }

    public boolean isBishop() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    /*
        RETURNS A LIST OF ALL THE LEGAL MOVES THE PIECE CAN MAKE.
    */
    public abstract List<Position> legalMoves();

    /*
        MOVE THE PIECE TO THE SPECIFIED POSITION.
    */
    public void move(int row, int column) {
        this.row = row;
        this.column = column;
        board.move(this, row, column);
        hasMoved = true;
    }

    /*
        RETURNS WHETHER OR NOT THE PIECE HAS MOVED. THIS METHOD IS PARTICULARLY
        USEFUL FOR CHECKING IF A PLAYER CAN CASTLE SINCE THE KING AND ROOK
        CASTLE MUST NOT HAVE MOVED PRIOR TO CASTLING.
    */
    public boolean hasMoved() {
        return hasMoved;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /*
        RETURN THE POSITION OF THE PIECE AS A STRING. THE COLUMN VALUE MUST BE
        SUBTRACTED BY 1 SINCE CHESS ROWS ARE ONE-INDEXED.

        EXAMPLE: A KNIGHT ON B1 HAS POSITION VALUE OF (0, 1);
    */
    public String position() {
        switch(row) {
            case 0: return 'A' + Integer.toString(column - 1);
            case 1: return 'B' + Integer.toString(column - 1);
            case 2: return 'C' + Integer.toString(column - 1);
            case 3: return 'D' + Integer.toString(column - 1);
            case 4: return 'E' + Integer.toString(column - 1);
            case 5: return 'F' + Integer.toString(column - 1);
            case 6: return 'G' + Integer.toString(column - 1);
            case 7: return 'H' + Integer.toString(column - 1);
            default: return "Invalid position.";
        }
    }




    // DIFFERENT TYPES OF PINS:
    // EXAMPLE: BISHOP PINNED TO KING BY BISHOP VS ROOK.

    /*
        RETURNS WHETHER OR NOT THE PIECE IS PINNED TO THE KING.
    */
    public abstract boolean isPinned();
}