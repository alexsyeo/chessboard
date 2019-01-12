package test.moves;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenMoves {

    /*
        TEST QUEEN'S MOVEMENT FROM CORNER OF THE BOARD.
    */
    @Test
    public void testQueenMovementFromCorner() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 2), true, board));
        board.insertPiece(new King(new Position(7, 6), board));
        board.insertPiece(new Queen(new Position(0, 0), true, board));
        assertEquals(1, board.getWhiteQueenCount());
        assertEquals(21, board.getPiece(0, 0).legalMoves().size());

        System.out.println("Queen Position: " + board.getPiece(0, 0).getPosition());
        for (Position position : board.getPiece(0, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST QUEEN'S MOVEMENT WHEN PINNED BY AN ENEMY BISHOP.
    */
    @Test
    public void testQueenMovementBishopPin() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Queen(new Position(1, 1), true, board));
        board.insertPiece(new Bishop(new Position(6, 6), board));
        assertEquals(1, board.getWhiteQueenCount());
        assertEquals(1, board.getBlackBishopCount());
        assertEquals(5, board.getPiece(1, 1).legalMoves().size());

        System.out.println("Queen Position: " + board.getPiece(1, 1).getPosition());
        for (Position position : board.getPiece(1, 1).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST QUEEN'S MOVEMENT WHEN PINNED BY AN ENEMY ROOK.
    */
    @Test
    public void testQueenMovementRookPin() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Queen(new Position(1, 0), true, board));
        board.insertPiece(new Rook(new Position(6, 0), board));
        assertEquals(1, board.getWhiteQueenCount());
        assertEquals(1, board.getBlackRookCount());
        assertEquals(5, board.getPiece(1, 0).legalMoves().size());

        System.out.println("Queen Position: " + board.getPiece(1, 0).getPosition());
        for (Position position : board.getPiece(1, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST QUEEN'S MOVEMENT WHEN BLOCKED BY A FRIENDLY PIECE.
    */
    @Test
    public void testQueenMovementBlockedFriendly() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Queen(new Position(1, 0), true, board));
        board.insertPiece(new Bishop(new Position(2, 0), true, board));
        assertEquals(1, board.getWhiteQueenCount());
        assertEquals(1, board.getWhiteBishopCount());
        assertEquals(14, board.getPiece(1, 0).legalMoves().size());

        System.out.println("Queen Position: " + board.getPiece(1, 0).getPosition());
        for (Position position : board.getPiece(1, 0).legalMoves()) {
            System.out.println(position);
        }
    }

}