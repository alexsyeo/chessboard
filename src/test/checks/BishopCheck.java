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

    /*
        ENSURES THAT A WHITE BISHOP BLOCKED BY A WHITE PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockWhite() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), board));
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new Bishop(new Position(6, 6), true, board));
        board.insertPiece(new Pawn(new Position(3, 3), true, board));
        assertFalse(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE BISHOP BLOCKED BY A BLACK PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockBlack() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), board));
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new Bishop(new Position(6, 6), true, board));
        board.insertPiece(new Pawn(new Position(3, 3), board));
        assertFalse(board.blackKingInCheck());
    }




        ///////////////////////////////////////////
       //      THE BELOW TESTS ARE THE          //
      //       SAME AS THE ABOVE EXCEPT        //
     //        THE COLORS ARE REVERSED.       //
    ///////////////////////////////////////////




    /*
        ENSURES THAT A BLACK BISHOP NORTHWEST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckNorthwest1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Bishop(new Position(1, 1), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK BISHOP NORTHEAST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckNortheast1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 0), true, board));
        board.insertPiece(new King(new Position(0, 2), board));
        board.insertPiece(new Bishop(new Position(0, 7), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK BISHOP SOUTHWEST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwest1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 7), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Bishop(new Position(6, 1), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK BISHOP SOUTHEAST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheast1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Bishop(new Position(6, 6), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK BISHOP BLOCKED BY A BLACK PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockBlack1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Bishop(new Position(6, 6), board));
        board.insertPiece(new Pawn(new Position(3, 3), board));
        assertFalse(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK BISHOP BLOCKED BY A WHITE PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockWhite1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Bishop(new Position(6, 6), board));
        board.insertPiece(new Pawn(new Position(3, 3), true, board));
        assertFalse(board.blackKingInCheck());
    }
}