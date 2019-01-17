package graphics;

import game.Board;
import game.pieces.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromoteMenu {
    JFrame frame;
    JPanel popup;
    JButton knightButton, bishopButton, rookButton, queenButton;

    PromoteMenu(Position position, boolean isWhite, JButton button, JButton reverseButton, Board board) {
        frame = new JFrame("Pawn Promotion");
        frame.setAlwaysOnTop(true);

        popup = new JPanel();

        // Promote to knight.
        knightButton = new JButton();
        Knight knight = new Knight(position, isWhite, board);
        GraphicsTools.setIcon(knight, knightButton);
        knightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.removePiece(position.row, position.column);
                board.insertPiece(knight, position);
                GraphicsTools.setIcon(knight, button);
                if (reverseButton != null) {
                    GraphicsTools.setIcon(knight, reverseButton);
                }
                frame.dispose();
            }
        });
        popup.add(knightButton);

        // Promote to bishop.
        bishopButton = new JButton();
        Bishop bishop = new Bishop(position, isWhite, board);
        GraphicsTools.setIcon(bishop, bishopButton);
        bishopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.removePiece(position.row, position.column);
                board.insertPiece(bishop, position);
                GraphicsTools.setIcon(bishop, button);
                if (reverseButton != null) {
                    GraphicsTools.setIcon(bishop, reverseButton);
                }
                frame.dispose();
            }
        });
        popup.add(bishopButton);

        // Promote to rook.
        rookButton = new JButton();
        Rook rook = new Rook(position, isWhite, board);
        GraphicsTools.setIcon(rook, rookButton);
        rookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.removePiece(position.row, position.column);
                board.insertPiece(rook, position);
                GraphicsTools.setIcon(rook, button);
                if (reverseButton != null) {
                    GraphicsTools.setIcon(rook, reverseButton);
                }
                frame.dispose();
            }
        });
        popup.add(rookButton);

        // Promote to rook.
        queenButton = new JButton();
        Queen queen = new Queen(position, isWhite, board);
        GraphicsTools.setIcon(queen, queenButton);
        queenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.removePiece(position.row, position.column);
                board.insertPiece(queen, position);
                GraphicsTools.setIcon(queen, button);
                if (reverseButton != null) {
                    GraphicsTools.setIcon(queen, reverseButton);
                }
                frame.dispose();
            }
        });
        popup.add(queenButton);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(415, 100);
        frame.add(popup);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}