package game.pieces;

import game.Board;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();

        return legalMoves;
    }
}