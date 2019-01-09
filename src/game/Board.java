package game;

import game.pieces.*;

public class Board {
    private static final int BOARD_LENGTH = 8;
    private Piece[][] chessBoard;
    private int blackPawnCount, blackRookCount, blackKnightCount, blackBishopCount, blackQueenCount, whitePawnCount, whiteRookCount, whiteKnightCount, whiteBishopCount, whiteQueenCount;
    private King whiteKing, blackKing;

    public Board() {
        chessBoard = new Piece[BOARD_LENGTH][BOARD_LENGTH];
        blackPawnCount = whitePawnCount = 8;
        blackRookCount = blackKnightCount = blackBishopCount = whiteRookCount = whiteKnightCount = whiteBishopCount = 2;
        blackQueenCount = whiteQueenCount = 1;

        /*
            POPULATE THE CHESS BOARD WITH THE PIECES IN THEIR INITIAL POSITIONS.
        */
        for (int i = 0; i < BOARD_LENGTH; i++) {
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
                    chessBoard[7][i] = whiteKing = new King(7, i, this);
                    chessBoard[0][i] = blackKing = new King(0, i, true, this);
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
        RETURN FALSE;
    */
    public boolean isOccupied(int row, int column) {
        return inBoard(row, column) && chessBoard[row][column] != null;
    }

    /*
        RETURNS WHETHER OR NOT THE POSITION IS CONTAINED IN THE BOARD.
    */
    public boolean inBoard(int row, int column) {
        return !(row < 0 || row > 7 || column < 0 || column > 7);
    }

    /*
        MOVES THE SPECIFIED PIECE TO THE DESIGNATED LOCATION.
    */
    public void move(Piece piece, int row, int column) {
        chessBoard[piece.getRow()][piece.getColumn()] = null;
        chessBoard[row][column] = piece;
    }

    /*
        GET THE PIECE AT THE SPECIFIED LOCATION.
    */
    public Piece getPiece(int row, int column) {
        return chessBoard[row][column];
    }

    public int getBlackPawnCount() {
        return blackPawnCount;
    }

    public int getWhitePawnCount() {
        return whitePawnCount;
    }

    public void loseBlackPawn() {
        blackPawnCount--;
    }

    public void loseWhitePawn() {
        whitePawnCount--;
    }

    public int getBlackRookCount() {
        return blackRookCount;
    }
    
    public int getWhiteRookCount() {
        return whiteRookCount;
    }

    public void loseBlackRook() {
        blackRookCount--;
    }

    public void loseWhiteRook() {
        whiteRookCount--;
    }

    public void gainBlackRook() {
        blackRookCount++;
    }

    public void gainWhiteRook() {
        whiteRookCount++;
    }

    public int getBlackKnightCount() {
        return blackKnightCount;
    }

    public int getWhiteKnightCount() {
        return whiteKnightCount;
    }

    public void loseBlackKnight() {
        blackKnightCount--;
    }

    public void loseWhiteKnight() {
        whiteKnightCount--;
    }

    public void gainBlackKnight() {
        blackKnightCount++;
    }

    public void gainWhiteKnight() {
        whiteKnightCount++;
    }

    public int getBlackBishopCount() {
        return blackBishopCount;
    }

    public int getWhiteBishopCount() {
        return whiteBishopCount;
    }

    public void loseBlackBishop() {
        blackBishopCount--;
    }

    public void loseWhiteBishop() {
        whiteBishopCount--;
    }

    public void gainBlackBishop() {
        blackBishopCount++;
    }

    public void gainWhiteBishop() {
        whiteBishopCount++;
    }

    public int getBlackQueenCount() {
        return blackQueenCount;
    }

    public int getWhiteQueenCount() {
        return whiteQueenCount;
    }

    public void loseBlackQueen() {
        blackQueenCount--;
    }

    public void loseWhiteQueen() {
        whiteQueenCount--;
    }

    public void gainBlackQueen() {
        blackQueenCount++;
    }

    public void gainWhiteQueen() {
        whiteQueenCount++;
    }

    public Position getBlackKingPosition() {
        return blackKing.getPosition();
    }

    public Position getWhiteKingPosition() {
        return whiteKing.getPosition();
    }

    public boolean blackKingInCheck() {
        // Check for white king.
        if (kingsTouching()) {
            return true;
        }

        Position kingPosition = getBlackKingPosition();
        int kingRow = kingPosition.row;
        int kingColumn = kingPosition.column;

        // Look down the column for white rook or queen.
        for (int i = 1; i < BOARD_LENGTH - kingRow; i++) {
            if (isOccupied(kingRow + i, kingColumn)) {
                Piece piece = getPiece(kingRow + i, kingColumn);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    }
                }
            }
        }

        // Look up the column for white rook or queen.
        for (int i = 1; i < kingRow; i++) {
            if (isOccupied(kingRow - i, kingColumn)) {
                Piece piece = getPiece(kingRow - i, kingColumn);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    }
                }
            }
        }

        // Look left for white rook or queen.
        for (int i = 1; i < kingColumn; i++) {
            if (isOccupied(kingRow, kingColumn - i)) {
                Piece piece = getPiece(kingRow, kingColumn - i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    }
                }
            }
        }

        // Look right for white rook or queen.
        for (int i = 1; i < BOARD_LENGTH - kingColumn; i++) {
            if (isOccupied(kingRow, kingColumn - i)) {
                Piece piece = getPiece(kingRow, kingColumn - i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    }
                }
            }
        }

        // CHECK FOR DIAGONALS (BISHOP AND QUEEN, PLUS PAWN FOR SPECIFIC SCENARIO)

        return false;
    }

    // public boolean whiteKingInCheck() {

    // }

    private boolean kingsTouching() {
        Position blackKingPosition = getBlackKingPosition();
        int blackKingRow = blackKingPosition.row;
        int blackKingColumn = blackKingPosition.column;
        Position whiteKingPosition = getWhiteKingPosition();
        int whiteKingRow = whiteKingPosition.row;
        int whiteKingColumn = whiteKingPosition.column;

        return whiteKingRow == blackKingRow && (whiteKingColumn == blackKingColumn + 1 || whiteKingColumn == blackKingColumn - 1) ||
            whiteKingRow == blackKingRow - 1 && (whiteKingColumn == blackKingColumn + 1 || whiteKingColumn == blackKingColumn || whiteKingColumn == blackKingColumn - 1) ||
            whiteKingRow == blackKingRow + 1 && (whiteKingColumn == blackKingColumn + 1 || whiteKingColumn == blackKingColumn || whiteKingColumn == blackKingColumn - 1)
        ;
    }
}