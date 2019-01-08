package game.pieces;

import game.Board;

public class King extends Piece {
    public King(int row, int column, Board board) {
        super(row, column, board);
    }

    public King(int row, int column, boolean isWhite, Board board) {
        super(row, column, isWhite, board);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}