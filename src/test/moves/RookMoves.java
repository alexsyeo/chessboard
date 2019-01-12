package test.moves;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookMoves {

    /*
        TEST ROOK'S MOVEMENT FROM CORNER OF THE BOARD.
    */
    @Test
    public void testRookMovementFromCorner() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Rook(new Position(0, 0), true, board));
        assertEquals(1, board.getWhiteRookCount());
        assertEquals(14, board.getPiece(0, 0).legalMoves().size());

        System.out.println("Rook Position: " + board.getPiece(0, 0).getPosition());
        for (Position position : board.getPiece(0, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST ROOK'S MOVEMENT WHEN PINNED BY AN ENEMY BISHOP.
    */
    @Test
    public void testRookMovementBishopPin() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Rook(new Position(5, 5), true, board));
        board.insertPiece(new Bishop(new Position(6, 6), board));
        assertEquals(1, board.getWhiteRookCount());
        assertEquals(1, board.getBlackBishopCount());
        assertEquals(0, board.getPiece(5, 5).legalMoves().size());

        System.out.println("Rook Position: " + board.getPiece(5, 5).getPosition());
        for (Position position : board.getPiece(5, 5).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST ROOK'S MOVEMENT WHEN PINNED BY AN ENEMY ROOK.
    */
    @Test
    public void testRookMovementRookPin() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Rook(new Position(1, 0), true, board));
        board.insertPiece(new Rook(new Position(6, 0), board));
        assertEquals(1, board.getWhiteRookCount());
        assertEquals(1, board.getBlackRookCount());
        assertEquals(5, board.getPiece(1, 0).legalMoves().size());

        System.out.println("Rook Position: " + board.getPiece(1, 0).getPosition());
        for (Position position : board.getPiece(1, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST ROOK'S MOVEMENT WHEN BLOCKED BY A FRIENDLY PIECE.
    */
    @Test
    public void testRookMovementBlockedFriendly() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Rook(new Position(1, 0), true, board));
        board.insertPiece(new Bishop(new Position(2, 0), true, board));
        assertEquals(1, board.getWhiteRookCount());
        assertEquals(1, board.getWhiteBishopCount());
        assertEquals(7, board.getPiece(1, 0).legalMoves().size());

        System.out.println("Rook Position: " + board.getPiece(1, 0).getPosition());
        for (Position position : board.getPiece(1, 0).legalMoves()) {
            System.out.println(position);
        }
    }

}