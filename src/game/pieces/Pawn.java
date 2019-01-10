package game.pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;

public class Pawn extends Piece {
    public Pawn(Position position, Board board) {
        super(position, board);
    }

    public Pawn(Position position, boolean isWhite, Board board) {
        super(position, isWhite, board);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    // @Override
    // public boolean isPinned() {
    //     boolean isPinned = false;


    //     return isPinned;
    // }

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();
//        if (!isPinned()) {
//            if (isWhite) {
//                if (!board.isOccupied(row - 1, column)) {
//                    legalMoves.add(new Position(row - 1, column));
//
//                    if (!hasMoved && !board.isOccupied(row - 2, column)) {
//                        legalMoves.add(new Position(row - 2, column));
//                    }
//                }
//            } else {
//                if (!board.isOccupied(row + 1, column)) {
//                    legalMoves.add(new Position(row + 1, column));
//
//                    if (!hasMoved && !board.isOccupied(row + 2, column)) {
//                        legalMoves.add(new Position(row + 2, column));
//                    }
//                }
//            }
//        }

        return legalMoves;
    }
}