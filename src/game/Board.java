package game;

import game.pieces.*;

public class Board {
    Piece[][] chessBoard;

    public Board() {
        chessBoard = new Piece[8][8];

        /*
            POPULATE THE CHESS BOARD WITH THE PIECES IN THEIR INITIAL POSITIONS.
        */
        for (int i = 0; i < 8; i++) {
            chessBoard[6][i] = new Pawn(6, i, this);
            chessBoard[1][i] = new Pawn(1, i, true, this);

            switch(i) {
                case 0:
                    chessBoard[7][i] = new Rook(7, i, this);
                    chessBoard[0][i] = new Rook(0, i, true, this);
                    break;
                case 1:
                    chessBoard[7][i] = new Knight(7, i, this);
                    chessBoard[0][i] = new Knight(0, i, true, this);
                    break;
                case 2:
                    chessBoard[7][i] = new Bishop(7, i, this);
                    chessBoard[0][i] = new Bishop(0, i, true, this);
                    break;
                case 3:
                    chessBoard[7][i] = new Queen(7, i, this);
                    chessBoard[0][i] = new Queen(0, i, true, this);
                    break;
                case 4:
                    chessBoard[7][i] = new King(7, i, this);
                    chessBoard[0][i] = new King(0, i, true, this);
                    break;
                case 5:
                    chessBoard[7][i] = new Bishop(7, i, this);
                    chessBoard[0][i] = new Bishop(0, i, true, this);
                    break;
                case 6:
                    chessBoard[7][i] = new Knight(7, i, this);
                    chessBoard[0][i] = new Knight(0, i, true, this);
                    break;
                case 7:
                    chessBoard[7][i] = new Rook(7, i, this);
                    chessBoard[0][i] = new Rook(0, i, true, this);
                    break;
                default:
                    break;
            }
        }
    }

    /*
        RETURNS WHETHER OR NOT THE SQUARE IS OCCUPIED BY A PIECE.
        IF THE SQUARE IS OUTSIDE THE BOUNDARIES OF THE BOARD, THEN AUTOMATICALLY
        RETURN TRUE;
    */
    public boolean isOccupied(int row, int column) {
        return (row < 0 || row > 7 || column < 0 || column > 7) ||
            chessBoard[row][column] != null;
    }


    /*
        MOVES THE SPECIFIED PIECE TO THE DESIGNATED LOCATION.
    */
    public void move(Piece piece, int row, int column) {
        chessBoard[piece.getRow()][piece.getColumn()] = null;
        chessBoard[row][column] = piece;
    }
}