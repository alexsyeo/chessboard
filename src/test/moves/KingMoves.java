package test.moves;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingMoves {

    /*
        TEST KING'S MOVEMENT FROM CENTER OF THE BOARD.
    */
    @Test
    public void testKingMovementFromCenter() {
        Board board = new Board();
        board.insertPiece(new King(new Position(4, 4), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        assertEquals(8, board.getPiece(4, 4).legalMoves().size());

        System.out.println("King Position: " + board.getPiece(4, 4).getPosition());
        for (Position position : board.getPiece(4, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST KING'S MOVEMENT FROM CORNER OF THE BOARD.
    */
    @Test
    public void testKingMovementFromCorner() {
        Board board = new Board();
        board.insertPiece(new King(new Position(7, 7), true, board));
        board.insertPiece(new King(new Position(0, 0), board));
        board.getPiece(7, 7).updateHasMoved(true);
        assertEquals(3, board.getPiece(7, 7).legalMoves().size());

        System.out.println("King Position: " + board.getPiece(7, 7).getPosition());
        for (Position position : board.getPiece(7, 7).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST WHITE KING CASTLE KING-SIDE.
    */
    @Test
    public void testWhiteKingCastleKingside() {
        Board board = new Board(true);
        // NF3.
        board.move(board.getPiece(7, 6), new Position(5, 5));
        // E4.
        board.move(board.getPiece(6, 4), new Position(4, 4));
        // BC4.
        board.move(board.getPiece(7, 5), new Position(4, 2));

        assertEquals(3, board.getPiece(7, 4).legalMoves().size());

        System.out.println("King Position: " + board.getPiece(7, 4).getPosition());
        for (Position position : board.getPiece(7, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST WHITE KING CASTLE QUEEN-SIDE.
    */
    @Test
    public void testWhiteKingCastleQueenside() {
        Board board = new Board(true);
        // NC3.
        board.move(board.getPiece(7, 1), new Position(5, 2));
        // D4.
        board.move(board.getPiece(6, 3), new Position(4, 3));
        // BF4.
        board.move(board.getPiece(7, 2), new Position(4, 5));
        // QD2.
        board.move(board.getPiece(7, 3), new Position(6, 3));

        assertEquals(2, board.getPiece(7, 4).legalMoves().size());

        System.out.println("King Position: " + board.getPiece(7, 4).getPosition());
        for (Position position : board.getPiece(7, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
      TEST BLACK KING CASTLE KING-SIDE.
  */
    @Test
    public void testBlackKingCastleKingside() {
        Board board = new Board(true);
        // NF6.
        board.move(board.getPiece(0, 6), new Position(2, 5));
        // E5.
        board.move(board.getPiece(1, 4), new Position(3, 4));
        // BC5.
        board.move(board.getPiece(0, 5), new Position(3, 2));

        assertEquals(3, board.getPiece(0, 4).legalMoves().size());

        System.out.println("King Position: " + board.getPiece(0, 4).getPosition());
        for (Position position : board.getPiece(0, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BLACK KING CASTLE QUEEN-SIDE.
    */
    @Test
    public void testBlackKingCastleQueenside() {
        Board board = new Board(true);
        // NC6.
        board.move(board.getPiece(0, 1), new Position(2, 2));
        // D5.
        board.move(board.getPiece(1, 3), new Position(3, 3));
        // BF5.
        board.move(board.getPiece(0, 2), new Position(3, 5));
        // QD7.
        board.move(board.getPiece(0, 3), new Position(1, 3));

        assertEquals(2, board.getPiece(0, 4).legalMoves().size());

        System.out.println("King Position: " + board.getPiece(0, 4).getPosition());
        for (Position position : board.getPiece(0, 4).legalMoves()) {
            System.out.println(position);
        }
    }
}