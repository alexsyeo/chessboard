package game.pieces;

import game.Position;

public abstract class Piece {
    boolean isWhite;
    boolean hasMoved;
    Position position;

    public Piece() {
        
    }

    public Piece(int row, int column) {
        this(new Position(row, column), false);
    }

    public Piece(int row, int column, boolean isWhite) {
        this(new Position(row, column), isWhite);
    }

    public Piece(Position position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
        this.hasMoved = false;
    }

    public Position getPosition() {
        return position;
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
        RETURNS WHETHER OR NOT THE PIECE HAS MOVED. THIS METHOD IS PARTICULARLY
        USEFUL FOR CHECKING IF A PLAYER CAN CASTLE SINCE THE KING AND ROOK
        CASTLE MUST NOT HAVE MOVED PRIOR TO CASTLING.
    */
    public boolean hasMoved() {
        return hasMoved;
    }

    
    // DIFFERENT TYPES OF PINS:
    // EXAMPLE: BISHOP PINNED TO KING BY BISHOP VS ROOK.

    // /*
    //     RETURNS WHETHER OR NOT THE PIECE IS PINNED TO THE KING.
    // */
    // public abstract boolean isPinned();
}