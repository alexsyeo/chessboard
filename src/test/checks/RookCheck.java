package test.checks;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RookCheck {

    /*
        ENSURES THAT A WHITE ROOK NORTH OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNorth() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new King(new Position(7, 0), true, board));
        board.insertPiece(new Rook(new Position(5, 0), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE ROOK SOUTH OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouth() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 0), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Rook(new Position(7, 0), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE ROOK WEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckWest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Rook(new Position(5, 0), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE ROOK EAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckEast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Rook(new Position(5, 7), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE ROOK BLOCKED BY A WHITE PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockWhite() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new Pawn(new Position(5, 6), true, board));
        board.insertPiece(new Rook(new Position(5, 7), true, board));
        assertFalse(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE ROOK BLOCKED BY A BLACK PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockBlack() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new Knight(new Position(5, 6), board));
        board.insertPiece(new Rook(new Position(5, 7), true, board));
        assertFalse(board.blackKingInCheck());
    }
}