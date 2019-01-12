package test.moves;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopMoves {

    /*
        TEST BISHOP'S MOVEMENT FROM CORNER OF THE BOARD.
    */
    @Test
    public void testBishopMovementFromCorner() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 7), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Bishop(new Position(0, 0), true, board));
        assertEquals(1, board.getWhiteBishopCount());
        assertEquals(7, board.getPiece(0, 0).legalMoves().size());

        System.out.println("Bishop Position: " + board.getPiece(0, 0).getPosition());
        for (Position position : board.getPiece(0, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BISHOP'S MOVEMENT WHEN PINNED BY AN ENEMY BISHOP.
    */
    @Test
    public void testBishopMovementBishopPin() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Bishop(new Position(6, 6), board));
        board.insertPiece(new Bishop(new Position(1, 1), true, board));
        assertEquals(1, board.getWhiteBishopCount());
        assertEquals(1, board.getBlackBishopCount());
        assertEquals(5, board.getPiece(1, 1).legalMoves().size());

        System.out.println("Bishop Position: " + board.getPiece(1, 1).getPosition());
        for (Position position : board.getPiece(1, 1).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BISHOP'S MOVEMENT WHEN PINNED BY AN ENEMY ROOK.
    */
    @Test
    public void testBishopMovementRookPin() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Bishop(new Position(1, 0), true, board));
        board.insertPiece(new Rook(new Position(6, 0), board));
        assertEquals(1, board.getWhiteBishopCount());
        assertEquals(1, board.getBlackRookCount());
        assertEquals(0, board.getPiece(1, 0).legalMoves().size());

        System.out.println("Bishop Position: " + board.getPiece(1, 0).getPosition());
        for (Position position : board.getPiece(1, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BISHOP'S MOVEMENT WHEN BLOCKED BY A FRIENDLY PIECE.
    */
    @Test
    public void testBishopMovementBlockedFriendly() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Bishop(new Position(1, 0), true, board));
        board.insertPiece(new Bishop(new Position(2, 1), true, board));
        assertEquals(2, board.getWhiteBishopCount());
        assertEquals(1, board.getPiece(1, 0).legalMoves().size());

        System.out.println("Bishop Position: " + board.getPiece(1, 0).getPosition());
        for (Position position : board.getPiece(1, 0).legalMoves()) {
            System.out.println(position);
        }
    }

}