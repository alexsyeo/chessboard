package test.checks;

import game.Board;
import game.pieces.King;
import game.pieces.Pawn;
import game.pieces.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnCheck {

    /*
        ENSURES THAT A WHITE PAWN SOUTHWEST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSouthwest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Pawn(new Position(6, 4), true, board));
        assertTrue(board.blackKingInCheck());
    }

    /*
        ENSURES THAT A WHITE PAWN SOUTHEAST OF THE BLACK KING IS A CHECK.
    */
    @Test
    public void testCheckSoutheast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(5, 5), board));
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new Pawn(new Position(6, 6), true, board));
        assertTrue(board.blackKingInCheck());
    }
}
