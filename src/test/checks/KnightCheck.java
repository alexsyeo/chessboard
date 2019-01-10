package test.checks;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class KnightCheck {

    /*
        ENSURES THAT A WHITE KNIGHT NORTHWEST (HIGH) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNorthwestHigh() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(3, 4), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT NORTHEAST (HIGH) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNortheastHigh() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(3, 6), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT NORTHWEST (LOW) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNorthwestLow() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(4, 3), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT NORTHEAST (LOW) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNortheastLow() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(4, 7), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT SOUTHWEST (HIGH) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwestHigh() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(7, 4), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT SOUTHEAST (HIGH) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheastHigh() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(7, 6), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT SOUTHWEST (LOW) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwestLow() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(6, 3), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE KNIGHT SOUTHEAST (LOW) OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheastLow() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Knight(new Position(6, 7), true, board));
        assertTrue(board.blackKingInCheck());
    }
}