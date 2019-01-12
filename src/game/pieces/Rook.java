package game.pieces;

import game.Board;
import javafx.geometry.Pos;

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

        int row = position.row;
        int column = position.column;

        // Each of the below cases checks to make sure that the square is within the board and that the square is not
        // occupied by a piece of the same color. There is also a check to make sure that the piece is not pinned.

        // Move north.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row - i, column)) {
                if (board.isOccupied(row - i, column) && board.getPiece(row - i, column).isWhite() == isWhite) {
                    break;
                } else {
                    Position potentialSquare = new Position(row - i, column);
                    if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                        break;
                    } else {
                        if (board.getPiece(row - i, column) != null) {
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }


        // Move south.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row + i, column) && !(board.isOccupied(row + i, column) && board.getPiece(row + i, column).isWhite() == isWhite)) {
                Position potentialSquare = new Position(row + i, column);
                if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                    break;
                } else {
                    if (board.getPiece(row + i, column) != null) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        // Move east.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row, column + i) && !(board.isOccupied(row, column + i) && board.getPiece(row, column + i).isWhite() == isWhite)) {
                Position potentialSquare = new Position(row, column + i);
                if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                    break;
                } else {
                    if (board.getPiece(row, column + i) != null) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        // Move west.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row, column - i) && !(board.isOccupied(row, column - i) && board.getPiece(row, column - i).isWhite() == isWhite)) {
                Position potentialSquare = new Position(row, column - i);
                if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                    break;
                } else {
                    if (board.getPiece(row, column - i) != null) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return legalMoves;
    }
}