package game.pieces;

import java.util.List;

import game.Board;

public abstract class Piece {
    protected boolean isWhite;
    protected boolean hasMoved;
    protected Position position;
    protected Board board;
    protected static final int BOARD_LENGTH = 8;

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

    public void updateHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Position getPosition() {
        return position;
    }

    // A helper method for checking if a piece is pinned and adding the square to the list of legal moves if applicable.
    // This method applies for rooks, bishops, and queens.
    protected boolean checkForPinAndAddSquare(List<Position> legalMoves, Position potentialSquare, int i) {
        // For the first square, we must check if the piece is pinned.
        if (i == 1) {
            board.move(this, potentialSquare);
            // If the piece is pinned, then we do not have to go down the rest of the row/column/diagonal.
            if (isWhite) {
                if (board.whiteKingInCheck()) {
                    board.revertPreviousMove();
                    return true;
                } else {
                    board.revertPreviousMove();
                    legalMoves.add(potentialSquare);
                }
            } else {
                if (board.blackKingInCheck()) {
                    board.revertPreviousMove();
                    return true;
                } else {
                    board.revertPreviousMove();
                    legalMoves.add(potentialSquare);
                }
            }

        } else {
            legalMoves.add(potentialSquare);
        }
        return false;
    }
}