package graphics;

import game.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndMenu {
    JFrame frame;
    JPanel popup;

    EndMenu(JFrame graphics, boolean whiteWon) {
        frame = new JFrame("Game Over");
        frame.setAlwaysOnTop(true);
        popup = new JPanel();

        String message = whiteWon ? "Checkmate. White wins!" : "Checkmate. Black wins!";
        JLabel label = new JLabel(message);
        popup.add(label);

        // New game with one board.
        JButton newGameOneBoardButton = new JButton("New Game (One Board)");
        newGameOneBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Board board = new Board(true);
                Graphics.createAndShowGUI(board);
                graphics.dispose();
                frame.dispose();
            }
        });
        popup.add(newGameOneBoardButton);

        // New game with two boards.
        JButton newGameTwoBoardsButton = new JButton("New Game (Two Boards)");
        newGameTwoBoardsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Board board = new Board(true);
                GraphicsTwoBoards.createAndShowGUI(board);
                graphics.dispose();
                frame.dispose();
            }
        });
        popup.add(newGameTwoBoardsButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 125);
        frame.add(popup);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}