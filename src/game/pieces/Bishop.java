package game.pieces;

import game.Board;

public class Bishop extends Piece {

    public Bishop(int row, int column, Board board) {
        super(row, column, board);
    }

    public Bishop(int row, int column, boolean isWhite, Board board) {
        super(row, column, isWhite, board);
    }

    @Override
    public boolean isBishop() {
        return true;
    }
}