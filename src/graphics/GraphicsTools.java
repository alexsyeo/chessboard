package graphics;

import game.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsTools {
    public static final ImageIcon whitePawn = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon blackPawn = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteKnight = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_knight.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon blackKnight = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_knight.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteBishop = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_bishop.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon blackBishop = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_bishop.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteRook = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_rook.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon blackRook = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_rook.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteKing = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_king.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon blackKing = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_king.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon whiteQueen = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_queen.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon blackQueen = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_queen.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
    public static final ImageIcon emptySquare = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

    public static void setIcon(Piece piece, JButton button) {
        ImageIcon icon;
        if (piece == null) {
            icon = emptySquare;
        } else {
            if (piece.isPawn()) {
                if (piece.isWhite()) {
                    icon = whitePawn;
                } else {
                    icon = blackPawn;
                }
            } else if (piece.isKnight()) {
                if (piece.isWhite()) {
                    icon = whiteKnight;
                } else {
                    icon = blackKnight;
                }
            } else if (piece.isBishop()) {
                if (piece.isWhite()) {
                    icon = whiteBishop;
                } else {
                    icon = blackBishop;
                }
            } else if (piece.isRook()) {
                if (piece.isWhite()) {
                    icon = whiteRook;
                } else {
                    icon = blackRook;
                }
            } else if (piece.isQueen()) {
                if (piece.isWhite()) {
                    icon = whiteQueen;
                } else {
                    icon = blackQueen;
                }
            } else {
                if (piece.isWhite()) {
                    icon = whiteKing;
                } else {
                    icon = blackKing;
                }
            }
        }

        button.setIcon(icon);
    }

}
