package graphics;

import game.Board;

import javax.swing.*;
import java.awt.event.*;

public class StartMenu {
    private Board board;
    public StartMenu(Board board) {
        this.board = board;

        final JFrame frame = new JFrame("Start");
        final JPanel popup = new JPanel();

        // Single board.
        JButton menuItem = new JButton("Single board");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graphics.createAndShowGUI(board);
                frame.dispose();
            }
        });
        popup.add(menuItem);

        // Double board.
        menuItem = new JButton("Two boards");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GraphicsTwoBoards.createAndShowGUI(board);
                frame.dispose();
            }
        });
        popup.add(menuItem);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 60);
        frame.add(popup);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



}
