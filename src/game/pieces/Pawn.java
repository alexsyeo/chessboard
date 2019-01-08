package game.pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;

public class Pawn extends Piece {
    public Pawn(int row, int column, Board board) {
        super(row, column, board);
    }

    public Pawn(int row, int column, boolean isWhite, Board board) {
        super(row, column, isWhite, board);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();
        if (!isPinned()) {
            if (!board.isOccupied(row + 1, column)) {
                legalMoves.add(new Position(row + 1, column));

                if (!hasMoved && !board.isOccupied(row + 2, column)) {
                    legalMoves.add(new Position(row + 2, column));
                }
            }
        }

        return legalMoves;
    }
}