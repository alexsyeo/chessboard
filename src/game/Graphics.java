package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class Graphics extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel boardPanel;


    public Graphics(String name) {
        super(name);
        setResizable(false);
    }

    private void addComponentsToPane(final Container pane) {
        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.width / 2));
        masterPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        boardPanel.setBorder(new LineBorder(Color.BLACK));

        masterPanel.add(boardPanel, BorderLayout.CENTER);

        Insets buttonMargin = new Insets(0,0,0,0);
        int buttonSideLength =  64; // DOES THIS MATTER?
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares.length; j++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);

                ImageIcon icon = new ImageIcon(
                    new BufferedImage(buttonSideLength, buttonSideLength, BufferedImage.TYPE_INT_ARGB)
                );
                button.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    button.setBackground(Color.BLACK);
                } else {
                    button.setBackground(Color.WHITE);
                }
                button.setOpaque(true);
                button.setBorderPainted(false);
                chessBoardSquares[j][i] = button;
                boardPanel.add(button);
            }
        }

        pane.add(masterPanel, BorderLayout.NORTH);
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