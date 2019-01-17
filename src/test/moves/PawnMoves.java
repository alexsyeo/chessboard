package test.moves;

import game.Board;
import game.pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnMoves {

    /*
        TEST WHITE PAWN MOVE FORWARD.
    */
    @Test
    public void testWhiteForward() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Pawn(new Position(6, 4), true, board));
        assertEquals(2, board.getPiece(6, 4).legalMoves().size());

        System.out.println("Pawn Position: " + board.getPiece(6, 4).getPosition());
        for (Position position : board.getPiece(6, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BLACK PAWN MOVE FORWARD.
    */
    @Test
    public void testBlackForward() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Pawn(new Position(1, 4), board));
        assertEquals(2, board.getPiece(1, 4).legalMoves().size());

        System.out.println("Pawn Position: " + board.getPiece(1, 4).getPosition());
        for (Position position : board.getPiece(1, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST WHITE PAWN CAPTURE NORTHWEST.
    */
    @Test
    public void testWhiteCaptureNorthwest() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Pawn(new Position(4, 4), true, board));
        board.getPiece(4, 4).updateHasMoved(true);
        board.insertPiece(new Rook(new Position(3, 3), board));
        assertEquals(2, board.getPiece(4, 4).legalMoves().size());

        System.out.println("Pawn Position: " + board.getPiece(4, 4).getPosition());
        for (Position position : board.getPiece(4, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST WHITE PAWN CAPTURE NORTHEAST.
    */
    @Test
    public void testWhiteCaptureNortheast() {
        Board board = new Board();
        board.insertPiece(new King(new Position(0, 0), true, board));
        board.insertPiece(new King(new Position(7, 7), board));
        board.insertPiece(new Pawn(new Position(4, 4), true, board));
        board.getPiece(4, 4).updateHasMoved(true);
        board.insertPiece(new Rook(new Position(3, 5), board));
        assertEquals(2, board.getPiece(4, 4).legalMoves().size());

        System.out.println("Pawn Position: " + board.getPiece(4, 4).getPosition());
        for (Position position : board.getPiece(4, 4).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST WHITE PAWN EN PASSANT NORTHEAST.
    */
    @Test
    public void testWhiteEnPassantNortheast() {
        Board board = new Board(true);
        board.move(board.getPiece(6, 0), new Position(4, 0));
        board.move(board.getPiece(4, 0), new Position(3, 0));
        board.move(board.getPiece(1, 1), new Position(3, 1));

        assertEquals(2, board.getPiece(3, 0).legalMoves().size());
        System.out.println("Pawn Position: " + board.getPiece(3, 0).getPosition());
        for (Position position : board.getPiece(3, 0).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST WHITE PAWN EN PASSANT NORTHWEST.
    */
    @Test
    public void testWhiteEnPassantNorthwest() {
        Board board = new Board(true);
        board.move(board.getPiece(6, 1), new Position(4, 1));
        board.move(board.getPiece(4, 1), new Position(3, 1));
        board.move(board.getPiece(1, 0), new Position(3, 0));

        assertEquals(2, board.getPiece(3, 1).legalMoves().size());
        System.out.println("Pawn Position: " + board.getPiece(3, 1).getPosition());
        for (Position position : board.getPiece(3, 1).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BLACK PAWN EN PASSANT SOUTHWEST.
    */
    @Test
    public void testBlackEnPassantSouthwest() {
        Board board = new Board(true);
        board.move(board.getPiece(1, 1), new Position(3, 1));
        board.move(board.getPiece(3, 1), new Position(4, 1));
        board.move(board.getPiece(6, 0), new Position(4, 0));

        assertEquals(2, board.getPiece(4, 1).legalMoves().size());
        System.out.println("Pawn Position: " + board.getPiece(4, 1).getPosition());
        for (Position position : board.getPiece(4, 1).legalMoves()) {
            System.out.println(position);
        }
    }

    /*
        TEST BLACK PAWN EN PASSANT SOUTHEAST.
    */
    @Test
    public void testBlackEnPassantSoutheast() {
        Board board = new Board(true);
        board.move(board.getPiece(1, 1), new Position(3, 1));
        board.move(board.getPiece(3, 1), new Position(4, 1));
        board.move(board.getPiece(6, 2), new Position(4, 2));

        assertEquals(2, board.getPiece(4, 1).legalMoves().size());
        System.out.println("Pawn Position: " + board.getPiece(4, 1).getPosition());
        for (Position position : board.getPiece(4, 1).legalMoves()) {
            System.out.println(position);
        }
    }

}