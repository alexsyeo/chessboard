package test.checks;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class QueenCheck {

    /*
        ENSURES THAT A WHITE QUEEN NORTHWEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNorthwest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Queen(new Position(1, 1), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN NORTHEAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckNortheast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new King(new Position(0, 2), true, board));
        board.insertPiece(new Queen(new Position(0, 7), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN SOUTHWEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 7), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Queen(new Position(6, 1), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN SOUTHEAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), board));
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new Queen(new Position(6, 6), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
    ENSURES THAT A WHITE QUEEN NORTH OF THE BLACK KING IS A CHECK.
*/
    @Test
    public void testCheckNorth() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new King(new Position(7, 0), true, board));
        board.insertPiece(new Queen(new Position(5, 0), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN SOUTH OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouth() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 0), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Queen(new Position(7, 0), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN WEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckWest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Queen(new Position(5, 0), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN EAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckEast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Queen(new Position(5, 7), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN BLOCKED BY A WHITE PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockWhite() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new Pawn(new Position(5, 6), true, board));
        board.insertPiece(new Queen(new Position(5, 7), true, board));
        assertFalse(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE QUEEN BLOCKED BY A BLACK PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockBlack() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new Knight(new Position(5, 6), board));
        board.insertPiece(new Queen(new Position(5, 7), true, board));
        assertFalse(board.blackKingInCheck());
    }




        ///////////////////////////////////////////
       //      THE BELOW TESTS ARE THE          //
      //       SAME AS THE ABOVE EXCEPT        //
     //        THE COLORS ARE REVERSED.       //
    ///////////////////////////////////////////




    /*
        ENSURES THAT A BLACK QUEEN NORTHWEST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckNorthwest1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Queen(new Position(1, 1), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN NORTHEAST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckNortheast1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 0), true, board));
        board.insertPiece(new King(new Position(0, 2), board));
        board.insertPiece(new Queen(new Position(0, 7), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN SOUTHWEST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwest1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 7), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Queen(new Position(6, 1), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN SOUTHEAST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheast1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(1, 1), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Queen(new Position(6, 6), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN NORTH OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckNorth1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 0), board));
        board.insertPiece(new Queen(new Position(5, 0), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN SOUTH OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckSouth1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 0), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Queen(new Position(7, 0), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN WEST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckWest1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Queen(new Position(5, 0), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN EAST OF THE WHITE KING IS A CHECK.
    */
    @Test
    public void testCheckEast1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new Queen(new Position(5, 7), board));
        assertTrue(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN BLOCKED BY A BLACK PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockBlack1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new King(new Position(5, 5), true, board));
        board.insertPiece(new Pawn(new Position(5, 6), board));
        board.insertPiece(new Queen(new Position(5, 7), board));
        assertFalse(board.whiteKingInCheck());
    }

    /*
        ENSURES THAT A BLACK QUEEN BLOCKED BY A WHITE PIECE IS NOT A CHECK.
    */
    @Test
    public void testCheckBlockWhite1() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), board));
        board.insertPiece(new King(new Position(5, 5), true, board));
        board.insertPiece(new Knight(new Position(5, 6), true, board));
        board.insertPiece(new Queen(new Position(5, 7), board));
        assertFalse(board.whiteKingInCheck());
    }
}