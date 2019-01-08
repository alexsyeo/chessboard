package game.pieces;

import game.Board;

public class Queen extends Piece {
    public Queen(int row, int column, Board board) {
        super(row, column, board);
    }

    public Queen(int row, int column, boolean isWhite, Board board) {
        super(row, column, isWhite, board);
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}