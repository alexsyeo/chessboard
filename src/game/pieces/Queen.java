package game.pieces;

import game.Board;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();

        return legalMoves;
    }
}