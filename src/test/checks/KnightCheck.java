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
}