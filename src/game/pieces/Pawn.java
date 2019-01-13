package game.pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Board.Move;

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

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();

        int row = position.row;
        int column = position.column;

        // Each of the below cases checks to make sure that the square is within the board and that the square is not
        // occupied by a piece of the same color. There is also a check to make sure that the piece is not pinned.

        // Move forward.
        if (isWhite) {
            if (!board.isOccupied(row - 1, column)) {
                Position potentialSquare = new Position(row - 1, column);
                board.move(this, potentialSquare);
                if (!board.whiteKingInCheck()) {
                    legalMoves.add(potentialSquare);
                    board.revertPreviousMove();
                    if (!hasMoved) {
                        Position moveTwo = new Position(row - 2, column);
                        if (!board.isOccupied(moveTwo.row, moveTwo.column)) {
                            board.move(this, moveTwo);
                            if (!board.whiteKingInCheck()) {
                                legalMoves.add(moveTwo);
                            }
                            board.revertPreviousMove();
                        }
                    }
                } else {
                    board.revertPreviousMove();
                }
            }
        } else {
            if (!board.isOccupied(row + 1, column)) {
                Position potentialSquare = new Position(row + 1, column);
                board.move(this, potentialSquare);
                if (!board.blackKingInCheck()) {
                    legalMoves.add(potentialSquare);
                    board.revertPreviousMove();
                    if (!hasMoved) {
                        Position moveTwo = new Position(row + 2, column);
                        if (!board.isOccupied(moveTwo.row, moveTwo.column)) {
                            board.move(this, moveTwo);
                            if (!board.blackKingInCheck()) {
                                legalMoves.add(moveTwo);
                            }
                            board.revertPreviousMove();
                        }
                    }
                } else {
                    board.revertPreviousMove();
                }
            }
        }



        // Capture diagonal left.
        if (isWhite) {
            if (board.inBoard(row - 1, column - 1) && board.isOccupied(row - 1, column - 1) && !board.getPiece(row - 1, column - 1).isWhite()) {
                Position potentialSquare = new Position(row - 1, column - 1);
                board.move(this, potentialSquare);
                if (!board.whiteKingInCheck()) {
                    legalMoves.add(potentialSquare);
                }
                board.revertPreviousMove();
            }
        } else {
            if (board.inBoard(row + 1, column + 1) && board.isOccupied(row + 1, column + 1) && board.getPiece(row + 1, column + 1).isWhite()) {
                Position potentialSquare = new Position(row + 1, column + 1);
                board.move(this, potentialSquare);
                if (!board.blackKingInCheck()) {
                    legalMoves.add(potentialSquare);
                }
                board.revertPreviousMove();
            }
        }


        // Capture diagonal right.
        if (isWhite) {
            if (board.inBoard(row - 1, column + 1) && board.isOccupied(row - 1, column + 1) && !board.getPiece(row - 1, column + 1).isWhite()) {
                Position potentialSquare = new Position(row - 1, column + 1);
                board.move(this, potentialSquare);
                if (!board.whiteKingInCheck()) {
                    legalMoves.add(potentialSquare);
                }
                board.revertPreviousMove();
            }
        } else {
            if (board.inBoard(row + 1, column - 1) && board.isOccupied(row + 1, column - 1) && board.getPiece(row + 1, column - 1).isWhite()) {
                Position potentialSquare = new Position(row + 1, column - 1);
                board.move(this, potentialSquare);
                if (!board.blackKingInCheck()) {
                    legalMoves.add(potentialSquare);
                }
                board.revertPreviousMove();
            }
        }


        // En Passant.
        if (isWhite && position.row == 3) {
            Move lastMove = board.getLastMove();
            if (lastMove != null && lastMove.getPreviousSourcePiece().isPawn() &&
                    !lastMove.getPreviousSourcePiece().isWhite() && lastMove.getSourcePosition().row == 1 &&
                    lastMove.getTargetPosition().row == 3 && (lastMove.getTargetPosition().column == column - 1 ||
                    lastMove.getTargetPosition().column == column + 1)
            ) {
                Position enPassantSquare = new Position(2, lastMove.getTargetPosition().column);
                // Must simulate a capture.
                Piece removedPawn = board.removePiece(lastMove.getTargetPosition().row, lastMove.getTargetPosition().column);
                board.move(this, enPassantSquare);
                if (!board.whiteKingInCheck()) {
                    legalMoves.add(enPassantSquare);
                }
                board.insertPiece(removedPawn);
                board.revertPreviousMove();
            }
        } else if (!isWhite && position.row == 4) {
            Move lastMove = board.getLastMove();
            if (lastMove != null && lastMove.getPreviousSourcePiece().isPawn() &&
                    lastMove.getPreviousSourcePiece().isWhite() && lastMove.getSourcePosition().row == 6 &&
                    lastMove.getTargetPosition().row == 4 && (lastMove.getTargetPosition().column == column - 1 ||
                    lastMove.getTargetPosition().column == column + 1)
            ) {
                Position enPassantSquare = new Position(5, lastMove.getTargetPosition().column);
                // Must simulate a capture.
                Piece removedPawn = board.removePiece(lastMove.getTargetPosition().row, lastMove.getTargetPosition().column);
                board.move(this, enPassantSquare);
                if (!board.blackKingInCheck()) {
                    legalMoves.add(enPassantSquare);
                }
                board.insertPiece(removedPawn);
                board.revertPreviousMove();
            }
        }

        return legalMoves;
    }
}