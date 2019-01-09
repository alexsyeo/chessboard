package game.pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;

public class Knight extends Piece {
    public Knight(Position position, Board board) {
        super(position, board);
    }

    public Knight(Position position, boolean isWhite, Board board) {
        super(position, board);
    }


    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();
        if (!isPinned()) {
            if (!board.isOccupied(row + 2, column + 1)) {
                legalMoves.add(new Position(row + 2, column + 1));
            }
            if (!board.isOccupied(row + 2, column - 1)) {
                legalMoves.add(new Position(row + 2, column - 1));
            }
            if (!board.isOccupied(row + 1, column + 2)) {
                legalMoves.add(new Position(row + 1, column + 2));
            }
            if (!board.isOccupied(row + 1, column - 2)) {
                legalMoves.add(new Position(row + 1, column - 2));
            }
            if (!board.isOccupied(row - 1, column + 2)) {
                legalMoves.add(new Position(row - 1, column + 2));
            }
            if (!board.isOccupied(row - 1, column - 2)) {
                legalMoves.add(new Position(row - 1, column - 2));
            }
            if (!board.isOccupied(row - 2, column + 1)) {
                legalMoves.add(new Position(row - 2, column + 1));
            }
            if (!board.isOccupied(row - 2, column - 1)) {
                legalMoves.add(new Position(row - 2, column - 1));
            }
        }

        return legalMoves;
    }
}