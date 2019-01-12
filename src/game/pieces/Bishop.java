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

        int row = position.row;
        int column = position.column;

        // Each of the below cases checks to make sure that the square is within the board and that the square is not
        // occupied by a piece of the same color. There is also a check to make sure that the piece is not pinned.

        // Move northwest.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row - i, column - i)) {
                if (board.isOccupied(row - i, column - i) && board.getPiece(row - i, column - i).isWhite() == isWhite) {
                    break;
                } else {
                    Position potentialSquare = new Position(row - i, column - i);
                    if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                        break;
                    } else {
                        if (board.getPiece(row - i, column - i) != null) {
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }


        // Move northeast.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row - i, column + i) && !(board.isOccupied(row - i, column + i) && board.getPiece(row - i, column + i).isWhite() == isWhite)) {
                Position potentialSquare = new Position(row - i, column + i);
                if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                    break;
                } else {
                    if (board.getPiece(row - i, column + i) != null) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        // Move southwest.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row + i, column - i) && !(board.isOccupied(row + i, column - i) && board.getPiece(row + i, column - i).isWhite() == isWhite)) {
                Position potentialSquare = new Position(row + i, column - i);
                if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                    break;
                } else {
                    if (board.getPiece(row + i, column - i) != null) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        // Move southeast.
        for (int i = 1; i < BOARD_LENGTH; i++) {
            if (board.inBoard(row + i, column + i) && !(board.isOccupied(row + i, column + i) && board.getPiece(row + i, column + i).isWhite() == isWhite)) {
                Position potentialSquare = new Position(row + i, column + i);
                if (checkForPinAndAddSquare(legalMoves, potentialSquare, i)) {
                    break;
                } else {
                    if (board.getPiece(row + i, column + i) != null) {
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