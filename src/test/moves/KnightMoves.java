package test.moves;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightMoves {

    /*
        TEST KNIGHT'S MOVEMENT FROM CENTER OF THE BOARD.
    */
    @Test
    public void testKnightMovementCenter() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Knight(new Position(4, 4), true, board));

        System.out.println("Knight Position: " + board.getPiece(4, 4).getPosition());
        for (Position position : board.getPiece(4, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        MAKE SURE PINNED KNIGHT CANNOT MOVE.
    */
    @Test
    public void testPinnedKnightCannotMove() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Knight(new Position(1, 1), true, board));
        board.insertPiece(new Bishop(new Position(2, 2), board));

        System.out.println("Knight Position: " + board.getPiece(1, 1).getPosition());

        assertEquals(0, board.getPiece(1, 1).legalMoves().size());
    }

    /*
        MAKE SURE KNIGHT CANNOT MOVE TO SQUARE OCCUPIED BY PIECE OF SAME COLOR.
    */
    @Test
    public void testOccupiedSquareSameColor() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Knight(new Position(1, 2), true, board));

        System.out.println("Knight Position: " + board.getPiece(1, 2).getPosition());
        for (Position position : board.getPiece(1, 2).legalMoves()) {
            System.out.println(position);
        }
    }

}