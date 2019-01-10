package game.pieces;

import java.util.List;

import game.Board;

public abstract class Piece {
    protected boolean isWhite;
    protected boolean hasMoved;
    protected Position position;
    protected Board board;

    public Piece() {
        
    }

    public Piece(Position position, Board board) {
        this(position, false, board);
    }

    public Piece(Position position, boolean isWhite, Board board) {
        this.isWhite = isWhite;
        this.hasMoved = false;
        this.position = position;
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
    public void move(Position position) {
        this.position = position;
        board.move(this, position.row, position.column);
        hasMoved = true;
    }

    /*
        PREVIEW MOVING THE PIECE TO THE SPECIFIED POSITION.
        THIS IS USEFUL FOR DETECTING CHECKS AND PINS.
    */
    public void previewMove(Position position) {
        this.position = position;
        board.move(this, position.row, position.column);
    }

    /*
        RETURNS WHETHER OR NOT THE PIECE HAS MOVED. THIS METHOD IS PARTICULARLY
        USEFUL FOR CHECKING IF A PLAYER CAN CASTLE SINCE THE KING AND ROOK
        CASTLE MUST NOT HAVE MOVED PRIOR TO CASTLING.
    */
    public boolean hasMoved() {
        return hasMoved;
    }

    public Position getPosition() {
        return position;
    }

    // // DIFFERENT TYPES OF PINS:
    // // EXAMPLE: BISHOP PINNED TO KING BY BISHOP VS ROOK.

    // /*
    //     RETURNS WHETHER OR NOT THE PIECE IS PINNED TO THE KING.
    // */
    // public abstract boolean isPinned();
}