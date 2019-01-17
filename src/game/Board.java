package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import game.pieces.*;

public class Board {
    private static final int BOARD_LENGTH = 8;
    private Piece[][] chessBoard;
    private static final List<Pawn> blackPawns = new ArrayList<>();
    private static final List<Pawn> whitePawns = new ArrayList<>();
    private static final List<Knight> blackKnights = new ArrayList<>();
    private static final List<Knight> whiteKnights = new ArrayList<>();
    private static final List<Bishop> blackBishops = new ArrayList<>();
    private static final List<Bishop> whiteBishops = new ArrayList<>();
    private static final List<Rook> blackRooks = new ArrayList<>();
    private static final List<Rook> whiteRooks = new ArrayList<>();
    private static final List<Queen> blackQueens = new ArrayList<>();
    private static final List<Queen> whiteQueens = new ArrayList<>();
    private King whiteKing, blackKing;
    private LinkedList<Move> moves;

    public Board() {
        this(false);
    }

    public Board(boolean fill) {
        chessBoard = new Piece[BOARD_LENGTH][BOARD_LENGTH];
        moves = new LinkedList<>();

        if (!fill) {
            return;
        }

        /*
            POPULATE THE CHESS BOARD WITH THE PIECES IN THEIR INITIAL POSITIONS.
        */
        for (int i = 0; i < BOARD_LENGTH; i++) {
            chessBoard[6][i] = new Pawn(new Position(6, i), true, this);
            chessBoard[1][i] = new Pawn(new Position(1, i), this);

            switch (i) {
                case 0:
                    chessBoard[7][i] = new Rook(new Position(7, i), true, this);
                    chessBoard[0][i] = new Rook(new Position(0, i), this);
                    break;
                case 1:
                    chessBoard[7][i] = new Knight(new Position(7, i), true,this);
                    chessBoard[0][i] = new Knight(new Position(0, i), this);
                    break;
                case 2:
                    chessBoard[7][i] = new Bishop(new Position(7, i), true,this);
                    chessBoard[0][i] = new Bishop(new Position(0, i), this);
                    break;
                case 3:
                    chessBoard[7][i] = new Queen(new Position(7, i), true,this);
                    chessBoard[0][i] = new Queen(new Position(0, i), this);
                    break;
                case 4:
                    chessBoard[7][i] = whiteKing = new King(new Position(7, i), true,this);
                    chessBoard[0][i] = blackKing = new King(new Position(0, i), this);
                    break;
                case 5:
                    chessBoard[7][i] = new Bishop(new Position(7, i), true,this);
                    chessBoard[0][i] = new Bishop(new Position(0, i), this);
                    break;
                case 6:
                    chessBoard[7][i] = new Knight(new Position(7, i), true,this);
                    chessBoard[0][i] = new Knight(new Position(0, i), this);
                    break;
                case 7:
                    chessBoard[7][i] = new Rook(new Position(7, i), true,this);
                    chessBoard[0][i] = new Rook(new Position(0, i), this);
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
    public void move(Piece piece, Position target) {
        Position sourcePosition = piece.getPosition();
        Piece previousSourcePiece = piece;
        Piece previousDestinationPiece = getPiece(target.row, target.column);
        boolean previousHasMoved = piece.hasMoved();

        Move newMove = new Move(previousSourcePiece, previousDestinationPiece, sourcePosition, target, previousHasMoved);
        moves.add(newMove);

        // Update the position variable of the piece.
        piece.move(target);

        // The square the piece used to occupy should now be empty.
        chessBoard[sourcePosition.row][sourcePosition.column] = null;

        // The piece that used to occupy the target square (if any) should
        // now be removed.
        Piece removedPiece = removePiece(target.row, target.column);

        // The target square should now be occupied by the piece.
        chessBoard[target.row][target.column] = piece;

        // Handle castling.
        if (piece.isKing()) {
            if (target.column == sourcePosition.column + 2) {
                // King-side castle.
                Piece rook = getPiece(target.row, target.column + 1);
                rook.move(new Position(target.row, target.column - 1));
                chessBoard[target.row][target.column + 1] = null;
                chessBoard[target.row][target.column - 1] = rook;
            } else if (target.column == sourcePosition.column - 2) {
                // Queen-side castle.
                Piece rook = getPiece(target.row, target.column - 2);
                rook.move(new Position(target.row, target.column + 1));
                chessBoard[target.row][target.column - 2] = null;
                chessBoard[target.row][target.column + 1] = rook;
            }
        }

        // Handle en passant.
        if (piece.isPawn() && removedPiece == null && sourcePosition.column != target.column) {
            if (piece.isWhite()) {
                removePiece(target.row + 1, target.column);
            } else {
                removePiece(target.row - 1, target.column);
            }
        }
    }

    /*
        REVERTS THE PREVIOUS MOVE.
    */
    public void revertPreviousMove() {
        Move previousMove = moves.removeLast();

        Position sourcePosition = previousMove.sourcePosition;
        Position targetPosition = previousMove.targetPosition;
        Piece previousSourcePiece = previousMove.previousSourcePiece;
        Piece previousDestinationPiece = previousMove.previousDestinationPiece;
        boolean previousHasMoved = previousMove.previousHasMoved;

        // Put the piece back to its original square.
        chessBoard[sourcePosition.row][sourcePosition.column] = previousSourcePiece;

        // Update the position variable of the piece.
        previousSourcePiece.move(sourcePosition);

        // Update the piece's hasMoved variable to its previous value.
        previousSourcePiece.updateHasMoved(previousHasMoved);

        // Insert the piece that used to be on the target square to that square.
        insertPiece(previousDestinationPiece, targetPosition);
    }

    /*
        GET THE PIECE AT THE SPECIFIED LOCATION.
    */
    public Piece getPiece(int row, int column) {
        return chessBoard[row][column];
    }

    /*
        MOVE THE PIECE WITHOUT CHANGING ANY OTHER STATE.
    */
    public void sneakyMove(Position source, Position destination) {
        chessBoard[destination.row][destination.column] = chessBoard[source.row][source.column];
        chessBoard[source.row][source.column] = null;
    }

    /*
        INSERT A PIECE ON THE BOARD.
    */
    public void insertPiece(Piece piece) {
        if (piece != null) {
            insertPiece(piece, piece.getPosition());
        }
    }

    /*
        INSERT A PIECE AT A LOCATION ON THE BOARD IN THE SPECIFIED POSITION, IF IT IS NOT OCCUPIED.
    */
    public void insertPiece(Piece piece, Position position) {
        int row = position.row;
        int column = position.column;

        if (piece == null) {
            chessBoard[row][column] = null;
            return;
        }

        if (!inBoard(row, column)) {
            return;
        }

        if (piece.isKing()) {
            if (piece.isWhite()) {
                if (whiteKing == null) {
                    whiteKing = (King) piece;
                }
            } else {
                if (blackKing == null) {
                    blackKing = (King) piece;
                }
            }
        }  else {
            gainPiece(piece);
        }

        chessBoard[row][column] = piece;
    }

    /*
        REMOVE A PIECE AT A LOCATION ON THE BOARD, IF THE SQUARE IS OCCUPIED.
    */
    public Piece removePiece(int row, int column) {
        // The square is not occupied, so we are done.
        if (!isOccupied(row, column)) {
            return null;
        }

        Piece piece = getPiece(row, column);

        if (piece.isKing()) {
            if (piece.isWhite()) {
                whiteKing = null;
            } else {
                blackKing = null;
            }
        } else {
            losePiece(piece);
        }

        chessBoard[row][column] = null;
        return piece;
    }

    private void gainPiece(Piece piece) {
        if (piece.isPawn()) {
            if (piece.isWhite()) {
                whitePawns.add((Pawn)piece);
            } else {
                blackPawns.add((Pawn)piece);
            }
        } else if (piece.isKnight()) {
            if (piece.isWhite()) {
                whiteKnights.add((Knight)piece);
            } else {
                blackKnights.add((Knight)piece);
            }
        } else if (piece.isBishop()) {
            if (piece.isWhite()) {
                whiteBishops.add((Bishop)piece);
            } else {
                blackBishops.add((Bishop)piece);
            }
        } else if (piece.isRook()) {
            if (piece.isWhite()) {
                whiteRooks.add((Rook)piece);
            } else {
                blackRooks.add((Rook)piece);
            }
        } else if (piece.isQueen()) {
            if (piece.isWhite()) {
                whiteQueens.add((Queen)piece);
            } else {
                blackQueens.add((Queen)piece);
            }
        }
    }

    private void losePiece(Piece piece) {
        if (piece.isPawn()) {
            if (piece.isWhite()) {
                whitePawns.remove(piece);
            } else {
                blackPawns.remove(piece);
            }
        } else if (piece.isKnight()) {
            if (piece.isWhite()) {
                whiteKnights.remove(piece);
            } else {
                blackKnights.remove(piece);
            }
        } else if (piece.isBishop()) {
            if (piece.isWhite()) {
                whiteBishops.remove(piece);
            } else {
                blackBishops.remove(piece);
            }
        } else if (piece.isRook()) {
            if (piece.isWhite()) {
                whiteRooks.remove(piece);
            } else {
                blackRooks.remove(piece);
            }
        } else if (piece.isQueen()) {
            if (piece.isWhite()) {
                whiteQueens.remove(piece);
            } else {
                blackQueens.remove(piece);
            }
        }
    }

    public Position getBlackKingPosition() {
        return blackKing == null ? null : blackKing.getPosition();
    }

    public Position getWhiteKingPosition() {
        return whiteKing == null ? null : whiteKing.getPosition();
    }

    public void clearBoard() {
        for (int row = 0; row < BOARD_LENGTH; row++) {
            for (int column = 0; column < BOARD_LENGTH; column++) {
                removePiece(row, column);
            }
        }
    }

    /*
        RETURNS WHETHER OR NOT THE BLACK KING IS IN CHECK.
    */
    public boolean blackKingInCheck() {
        // Check for white king.
        if (kingsTouching()) {
            return true;
        }

        Position kingPosition = getBlackKingPosition();
        int kingRow = kingPosition.row;
        int kingColumn = kingPosition.column;

        // Look down the column for white rook or queen.
        for (int i = 1; i <= BOARD_LENGTH - kingRow; i++) {
            if (isOccupied(kingRow + i, kingColumn)) {
                Piece piece = getPiece(kingRow + i, kingColumn);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Look up the column for white rook or queen.
        for (int i = 1; i <= kingRow; i++) {
            if (isOccupied(kingRow - i, kingColumn)) {
                Piece piece = getPiece(kingRow - i, kingColumn);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Look left for white rook or queen.
        for (int i = 1; i <= kingColumn; i++) {
            if (isOccupied(kingRow, kingColumn - i)) {
                Piece piece = getPiece(kingRow, kingColumn - i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Look right for white rook or queen.
        for (int i = 1; i < BOARD_LENGTH - kingColumn; i++) {
            if (isOccupied(kingRow, kingColumn + i)) {
                Piece piece = getPiece(kingRow, kingColumn + i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isRook() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Check for white knight.
        if (isOccupied(kingRow + 1, kingColumn + 2)) {
            Piece potentialKnight = getPiece(kingRow + 1, kingColumn + 2);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow + 1, kingColumn - 2)) {
            Piece potentialKnight = getPiece(kingRow + 1, kingColumn - 2);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow + 2, kingColumn + 1)) {
            Piece potentialKnight = getPiece(kingRow + 2, kingColumn + 1);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow + 2, kingColumn - 1)) {
            Piece potentialKnight = getPiece(kingRow + 2, kingColumn - 1);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow - 1, kingColumn + 2)) {
            Piece potentialKnight = getPiece(kingRow - 1, kingColumn + 2);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow - 1, kingColumn - 2)) {
            Piece potentialKnight = getPiece(kingRow - 1, kingColumn - 2);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow - 2, kingColumn + 1)) {
            Piece potentialKnight = getPiece(kingRow - 2, kingColumn + 1);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        } else if (isOccupied(kingRow - 2, kingColumn - 1)) {
            Piece potentialKnight = getPiece(kingRow - 2, kingColumn - 1);
            if (potentialKnight.isKnight() && potentialKnight.isWhite()) {
                return true;
            }
        }

        // Look northwest for white bishop or queen.
        for (int i = 1; i <= BOARD_LENGTH; i++) {
            // Outside of the board, so break.
            if (!inBoard(kingRow - i, kingColumn - i)) {
                break;
            }

            if (isOccupied(kingRow - i, kingColumn - i)) {
                Piece piece = getPiece(kingRow - i, kingColumn - i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isBishop() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Look northeast for white bishop or queen.
        for (int i = 1; i <= BOARD_LENGTH; i++) {
            // Outside of the board, so break.
            if (!inBoard(kingRow - i, kingColumn + i)) {
                break;
            }

            if (isOccupied(kingRow - i, kingColumn + i)) {
                Piece piece = getPiece(kingRow - i, kingColumn + i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isBishop() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Look southwest for white bishop or queen.
        for (int i = 1; i <= BOARD_LENGTH; i++) {
            // Outside of the board, so break.
            if (!inBoard(kingRow + i, kingColumn - i)) {
                break;
            }

            if (isOccupied(kingRow + i, kingColumn - i)) {
                Piece piece = getPiece(kingRow + i, kingColumn - i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isBishop() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Look southeast for white bishop or queen.
        for (int i = 1; i <= BOARD_LENGTH; i++) {
            // Outside of the board, so break.
            if (!inBoard(kingRow + i, kingColumn + i)) {
                break;
            }

            if (isOccupied(kingRow + i, kingColumn + i)) {
                Piece piece = getPiece(kingRow + i, kingColumn + i);
                // If we encounter a black piece, then we are done.
                if (!piece.isWhite()) {
                    break;
                } else {
                    if (piece.isBishop() || piece.isQueen()) {
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }

        // Check for white pawn.
        if (isOccupied(kingRow + 1, kingColumn + 1)) {
            Piece piece = getPiece(kingRow + 1, kingColumn + 1);
            if (piece.isWhite() && piece.isPawn()) {
                return true;
            }
        } else if (isOccupied(kingRow + 1, kingColumn - 1)) {
            Piece piece = getPiece(kingRow + 1, kingColumn - 1);
            if (piece.isWhite() && piece.isPawn()) {
                return true;
            }
        }

        return false;
    }


    /*
        RETURNS WHETHER OR NOT THE WHITE KING IS IN CHECK.
    */
     public boolean whiteKingInCheck() {
         // Check for black king.
         if (kingsTouching()) {
             return true;
         }

         Position kingPosition = getWhiteKingPosition();
         int kingRow = kingPosition.row;
         int kingColumn = kingPosition.column;

         // Look down the column for black rook or queen.
         for (int i = 1; i <= BOARD_LENGTH - kingRow; i++) {
             if (isOccupied(kingRow + i, kingColumn)) {
                 Piece piece = getPiece(kingRow + i, kingColumn);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isRook() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Look up the column for black rook or queen.
         for (int i = 1; i <= kingRow; i++) {
             if (isOccupied(kingRow - i, kingColumn)) {
                 Piece piece = getPiece(kingRow - i, kingColumn);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isRook() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Look left for black rook or queen.
         for (int i = 1; i <= kingColumn; i++) {
             if (isOccupied(kingRow, kingColumn - i)) {
                 Piece piece = getPiece(kingRow, kingColumn - i);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isRook() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Look right for black rook or queen.
         for (int i = 1; i < BOARD_LENGTH - kingColumn; i++) {
             if (isOccupied(kingRow, kingColumn + i)) {
                 Piece piece = getPiece(kingRow, kingColumn + i);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isRook() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Check for black knight.
         if (isOccupied(kingRow + 1, kingColumn + 2)) {
             Piece potentialKnight = getPiece(kingRow + 1, kingColumn + 2);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow + 1, kingColumn - 2)) {
             Piece potentialKnight = getPiece(kingRow + 1, kingColumn - 2);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow + 2, kingColumn + 1)) {
             Piece potentialKnight = getPiece(kingRow + 2, kingColumn + 1);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow + 2, kingColumn - 1)) {
             Piece potentialKnight = getPiece(kingRow + 2, kingColumn - 1);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow - 1, kingColumn + 2)) {
             Piece potentialKnight = getPiece(kingRow - 1, kingColumn + 2);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow - 1, kingColumn - 2)) {
             Piece potentialKnight = getPiece(kingRow - 1, kingColumn - 2);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow - 2, kingColumn + 1)) {
             Piece potentialKnight = getPiece(kingRow - 2, kingColumn + 1);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         } else if (isOccupied(kingRow - 2, kingColumn - 1)) {
             Piece potentialKnight = getPiece(kingRow - 2, kingColumn - 1);
             if (potentialKnight.isKnight() && !potentialKnight.isWhite()) {
                 return true;
             }
         }

         // Look northwest for black bishop or queen.
         for (int i = 1; i <= BOARD_LENGTH; i++) {
             // Outside of the board, so break.
             if (!inBoard(kingRow - i, kingColumn - i)) {
                 break;
             }

             if (isOccupied(kingRow - i, kingColumn - i)) {
                 Piece piece = getPiece(kingRow - i, kingColumn - i);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isBishop() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Look northeast for black bishop or queen.
         for (int i = 1; i <= BOARD_LENGTH; i++) {
             // Outside of the board, so break.
             if (!inBoard(kingRow - i, kingColumn + i)) {
                 break;
             }

             if (isOccupied(kingRow - i, kingColumn + i)) {
                 Piece piece = getPiece(kingRow - i, kingColumn + i);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isBishop() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Look southwest for black bishop or queen.
         for (int i = 1; i <= BOARD_LENGTH; i++) {
             // Outside of the board, so break.
             if (!inBoard(kingRow + i, kingColumn - i)) {
                 break;
             }

             if (isOccupied(kingRow + i, kingColumn - i)) {
                 Piece piece = getPiece(kingRow + i, kingColumn - i);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isBishop() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Look southeast for black bishop or queen.
         for (int i = 1; i <= BOARD_LENGTH; i++) {
             // Outside of the board, so break.
             if (!inBoard(kingRow + i, kingColumn + i)) {
                 break;
             }

             if (isOccupied(kingRow + i, kingColumn + i)) {
                 Piece piece = getPiece(kingRow + i, kingColumn + i);
                 // If we encounter a white piece, then we are done.
                 if (piece.isWhite()) {
                     break;
                 } else {
                     if (piece.isBishop() || piece.isQueen()) {
                         return true;
                     } else {
                         break;
                     }
                 }
             }
         }

         // Check for black pawn.
         if (isOccupied(kingRow - 1, kingColumn + 1)) {
             Piece piece = getPiece(kingRow - 1, kingColumn + 1);
             if (!piece.isWhite() && piece.isPawn()) {
                 return true;
             }
         } else if (isOccupied(kingRow - 1, kingColumn - 1)) {
             Piece piece = getPiece(kingRow - 1, kingColumn - 1);
             if (!piece.isWhite() && piece.isPawn()) {
                 return true;
             }
         }

         return false;
     }


    /*
        RETURNS WHETHER OR NOT THE WHITE KING IS IN CHECKMATE.
    */
    public boolean whiteKingInCheckmate() {
        if (!whiteKingInCheck()) {
            return false;
        }
        // If any white piece has a valid move, then return false since this is not checkmate.
        if (whiteKing.legalMoves().size() > 0) {
            return false;
        }
        for (Pawn pawn : whitePawns) {
            if (pawn.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Knight knight : whiteKnights) {
            if (knight.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Bishop bishop : whiteBishops) {
            if (bishop.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Rook rook : whiteRooks) {
            if (rook.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Queen queen : whiteQueens) {
            if (queen.legalMoves().size() > 0) {
                return false;
            }
        }

        // Return true since white has no valid moves.
        return true;
    }

    /*
        RETURNS WHETHER OR NOT THE BLACK KING IS IN CHECKMATE.
    */
    public boolean blackKingInCheckmate() {
        if (!blackKingInCheck()) {
            return false;
        }
        // If any black piece has a valid move, then return false since this is not checkmate.
        if (blackKing.legalMoves().size() > 0) {
            return false;
        }
        for (Pawn pawn : blackPawns) {
            if (pawn.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Knight knight : blackKnights) {
            if (knight.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Bishop bishop : blackBishops) {
            if (bishop.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Rook rook : blackRooks) {
            if (rook.legalMoves().size() > 0) {
                return false;
            }
        }
        for (Queen queen : blackQueens) {
            if (queen.legalMoves().size() > 0) {
                return false;
            }
        }

        // Return true since black has no valid moves.
        return true;
    }


    /*
        RETURNS WHETHER OR NOT THE WHITE AND BLACK KINGS ARE TOUCHING.
        NOTE: THIS SHOULD NEVER RETURN TRUE FOR ANY LEGAL POSITION IN THE GAME.
    */
    private boolean kingsTouching() {
        if (blackKing == null || whiteKing == null) {
            return false;
        }

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

    /*
        RETURNS THE LAST MOVE PLAYED, IF ONE EXISTS.
    */
    public Move getLastMove() {
        return moves.getLast();
    }

    public class Move {
        Piece previousSourcePiece, previousDestinationPiece;
        Position sourcePosition, targetPosition;
        boolean previousHasMoved;

        Move(Piece previousSourcePiece, Piece previousDestinationPiece, Position sourcePosition, Position targetPosition,
             boolean previousHasMoved) {

            this.previousSourcePiece = previousSourcePiece;
            this.previousDestinationPiece = previousDestinationPiece;
            this.sourcePosition = sourcePosition;
            this.targetPosition = targetPosition;
            this.previousHasMoved = previousHasMoved;
        }

        public Piece getPreviousSourcePiece() {
            return previousSourcePiece;
        }

        public Piece getPreviousDestinationPiece() {
            return previousDestinationPiece;
        }

        public Position getSourcePosition() {
            return sourcePosition;
        }

        public Position getTargetPosition() {
            return targetPosition;
        }

        public boolean getPreviousHasMoved() {
            return previousHasMoved;
        }
    }
}