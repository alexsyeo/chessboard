package game.pieces;

import game.Board;

public class Queen extends Piece {
    public Queen(Position position, Board board) {
        super(position, board);
    }

    public Queen(Position position, boolean isWhite, Board board) {
        super(position, isWhite, board);
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}