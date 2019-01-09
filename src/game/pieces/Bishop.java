package game.pieces;

import game.Board;

public class Bishop extends Piece {

    public Bishop(Position position, Board board) {
        super(position, board);
    }

    public Bishop(Position position, boolean isWhite, Board board) {
        super(position, isWhite, board);
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}