package game.pieces;

import game.Board;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();

        return legalMoves;
    }
}