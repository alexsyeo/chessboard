package game.pieces;

import java.util.ArrayList;
import java.util.List;

import game.Board;

public class Knight extends Piece {
    public Knight(Position position, Board board) {
        super(position, board);
    }

    public Knight(Position position, boolean isWhite, Board board) {
        super(position, isWhite, board);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public List<Position> legalMoves() {
        List<Position> legalMoves = new ArrayList<>();

        int row = position.row;
        int column = position.column;

        // Each of the below cases checks to make sure that the square is within the board and that the square is not
        // occupied by a piece of the same color. There is also a check to make sure that the piece is not pinned.

        // Northwest high.
        if (board.inBoard(row - 2, column - 1) && !(board.isOccupied(row - 2, column - 1) && board.getPiece(row - 2, column - 1).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row - 2, column - 1);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Northeast high.
        if (board.inBoard(row - 2, column + 1) && !(board.isOccupied(row - 2, column + 1) && board.getPiece(row - 2, column + 1).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row - 2, column + 1);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Northwest low.
        if (board.inBoard(row - 1, column - 2) && !(board.isOccupied(row - 1, column - 2) && board.getPiece(row - 1, column - 2).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row - 1, column - 2);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Northeast low.
        if (board.inBoard(row - 1, column + 2) && !(board.isOccupied(row - 1, column + 2) && board.getPiece(row - 1, column + 2).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row - 1, column + 2);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Southwest high.
        if (board.inBoard(row + 2, column - 1) && !(board.isOccupied(row + 2, column - 1) && board.getPiece(row + 2, column - 1).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row + 2, column - 1);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Southeast high.
        if (board.inBoard(row + 2, column + 1) && !(board.isOccupied(row + 2, column + 1) && board.getPiece(row + 2, column + 1).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row + 2, column + 1);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Southwest low.
        if (board.inBoard(row + 1, column - 2) && !(board.isOccupied(row + 1, column - 2) && board.getPiece(row + 1, column - 2).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row + 1, column - 2);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        // Southeast low.
        if (board.inBoard(row + 1, column + 2) && !(board.isOccupied(row + 1, column + 2) && board.getPiece(row + 1, column + 2).isWhite() == isWhite)) {
            Position potentialSquare = new Position(row + 1, column + 2);
            board.move(this, potentialSquare);
            if ((isWhite && !board.whiteKingInCheck()) || (!isWhite && !board.blackKingInCheck())) {
                legalMoves.add(potentialSquare);
            }
            board.revertPreviousMove();
        }

        return legalMoves;
    }
}