package game.pieces;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();

        return legalMoves;
    }
}