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

        int row = position.row;
        int column = position.column;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (board.inBoard(row + i, column + j) && !(board.isOccupied(row + i, column + j) && board.getPiece(row + i, column + j).isWhite() == isWhite)) {
                        Position potentialSquare = new Position(row + i, column + j);
                        board.move(this, potentialSquare);
                        if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                            legalMoves.add(potentialSquare);
                        }
                        board.revertPreviousMove();
                    }
                }
            }
        }

        // Castle king-side.
        if (!hasMoved) {
            Piece potentialRook = board.getPiece(row, column + 3);
            if (potentialRook != null && potentialRook.isRook() && !potentialRook.hasMoved()) {
                if (potentialRook.isWhite() == isWhite) {
                    if (!board.isOccupied(row, column + 1)) {
                        Position rightOne = new Position(row, column + 1);
                        board.move(this, rightOne);
                        if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                            board.revertPreviousMove();
                            Position rightTwo = new Position(row, column + 2);
                            board.move(this, rightTwo);
                            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                                legalMoves.add(rightTwo);
                            }
                            board.revertPreviousMove();
                        } else {
                            board.revertPreviousMove();
                        }
                    }
                }
            }
        }

        // Castle queen-side.
        if (!hasMoved) {
            Piece potentialRook = board.getPiece(row, column - 4);
            if (potentialRook != null && potentialRook.isRook() && !potentialRook.hasMoved() && !board.isOccupied(row, column - 3)) {
                if (potentialRook.isWhite() == isWhite) {
                    if (!board.isOccupied(row, column - 1)) {
                        Position leftOne = new Position(row, column - 1);
                        board.move(this, leftOne);
                        if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                            board.revertPreviousMove();
                            Position leftTwo = new Position(row, column - 2);
                            board.move(this, leftTwo);
                            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                                legalMoves.add(leftTwo);
                            }
                            board.revertPreviousMove();
                        } else {
                            board.revertPreviousMove();
                        }
                    }
                }
            }
        }

        return legalMoves;
    }
}