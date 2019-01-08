package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Graphics extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Graphics(String name) {
        super(name);
        setResizable(false);
    }

    private void addComponentsToPane(final Container pane) {
        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.width / 2));

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.width / 2));

        masterPanel.add(boardPanel, BorderLayout.CENTER);


        pane.add(masterPanel, BorderLayout.NORTH);
        // this.getContentPane().add(masterPanel);
    }

    public static void createAndShowGUI() {
        Graphics graphics = new Graphics("Chess");

        graphics.setLocation(screenSize.width / 4, (screenSize.height - (screenSize.width / 2)) / 2);
        graphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        graphics.addComponentsToPane(graphics.getContentPane());
        
        graphics.pack();
        graphics.setVisible(true);
    }
}