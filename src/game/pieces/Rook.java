package game.pieces;

import game.Board;

public class Rook extends Piece {
    public Rook(Position position, Board board) {
        super(position, board);
    }

    public Rook(Position position, boolean isWhite, Board board) {
        super(position, isWhite, board);
    }

    @Override
    public boolean isRook() {
        return true;
    }
}