package game.pieces;

import game.Board;

public class King extends Piece {
    public King(Position position, Board board) {
        super(position, board);
    }

    public King(Position position, boolean isWhite, Board board) {
        super(position, isWhite, board);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}