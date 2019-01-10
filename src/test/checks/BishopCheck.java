package test.checks;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BishopCheck {

    /*
        ENSURES THAT A WHITE BISHOP NORTHWEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNorthwest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Bishop(new Position(1, 1), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE BISHOP NORTHEAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNortheast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new King(new Position(0, 2), true, board));
        board.insertPiece(new Bishop(new Position(0, 7), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE BISHOP SOUTHWEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 7), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Bishop(new Position(6, 1), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE BISHOP SOUTHEAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), board));
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new Bishop(new Position(6, 6), true, board));
        assertTrue(board.blackKingInCheck());
    }
}