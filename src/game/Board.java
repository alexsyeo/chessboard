package game;

import game.pieces.Pawn;

public class Board {
    Piece[][] chessBoard;

    public Board() {
        chessBoard = new Piece[8][8];

        /*
            POPULATE THE CHESS BOARD WITH THE PIECES IN THEIR INITIAL POSITIONS.
        */
        for (int i = 0; i < 8; i++) {
            chessBoard[6][i] = new Pawn(6, i);
            chessBoard[1][i] = new Pawn(1, i, true);

            switch(i) {
                case 0:
                    chessBoard[7][i] = new Rook(7, i);
                    chessBoard[0][i] = new Rook(0, i, true);
                    break;
                case 1:
                    chessBoard[7][i] = new Knight(7, i);
                    chessBoard[0][i] = new Knight(0, i, true);
                    break;
                case 2:
                    chessBoard[7][i] = new Bishop(7, i);
                    chessBoard[0][i] = new Bishop(0, i, true);
                    break;
                case 3:
                    chessBoard[7][i] = new Queen(7, i);
                    chessBoard[0][i] = new Queen(0, i, true);
                    break;
                case 4:
                    chessBoard[7][i] = new King(7, i);
                    chessBoard[0][i] = new King(0, i, true);
                    break;
                case 5:
                    chessBoard[7][i] = new Bishop(7, i);
                    chessBoard[0][i] = new Bishop(0, i, true);
                    break;
                case 6:
                    chessBoard[7][i] = new Knight(7, i);
                    chessBoard[0][i] = new Knight(0, i, true);
                    break;
                case 7:
                    chessBoard[7][i] = new Rook(7, i);
                    chessBoard[0][i] = new Rook(0, i, true);
                    break;
                default:
                    break;
            }
        }
    }

    public boolean isOccupied(int row, int column) {
        return chessBoard[row][column] != null;
    }
}