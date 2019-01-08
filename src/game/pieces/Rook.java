package game.pieces;

import game.Board;

public class Rook extends Piece {
    public Rook(int row, int column, Board board) {
        super(row, column, board);
    }

    public Rook(int row, int column, boolean isWhite, Board board) {
        super(row, column, isWhite, board);
    }

    @Override
    public boolean isRook() {
        return true;
    }
}