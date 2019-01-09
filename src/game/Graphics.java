package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/*
    TOOK COMPONENTS FROM https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
*/

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
        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int column = 0; column < chessBoardSquares.length; column++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);
                
                ImageIcon icon;
                if (row == 1) {
                    Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_pawn.png").getImage();
                    Image newimg = img.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newimg);
                } else if (row == 6) {
                    Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_pawn.png").getImage();
                    Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newimg);
                } else if (row == 0) {
                    if (column == 0 || column == 7) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_rook.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else if (column == 1 || column == 6) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_knight.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else if (column == 2 || column == 5) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_bishop.png").getImage();
                        Image newimg = img.getScaledInstance(65, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else if (column == 3) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_queen.png").getImage();
                        Image newimg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_king.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    }
                } else if (row == 7) {
                    if (column == 0 || column == 7) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_rook.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else if (column == 1 || column == 6) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_knight.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else if (column == 2 || column == 5) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_bishop.png").getImage();
                        Image newimg = img.getScaledInstance(65, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else if (column == 3) {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_queen.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    } else {
                        Image img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_king.png").getImage();
                        Image newimg = img.getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                    }
                } else {
                    icon = new ImageIcon(
                        new BufferedImage(buttonSideLength, buttonSideLength, BufferedImage.TYPE_INT_ARGB)
                    );
                }
                button.setIcon(icon);
                
                // LIGHT YELLOW
                if ((row % 2 == 1 && column % 2 == 1) || (row % 2 == 0 && column % 2 == 0)) {
                    button.setBackground(new Color(255, 255, 153));
                }
                // DARK BROWN
                else {
                    button.setBackground(new Color(153, 102, 0));
                }
                button.setOpaque(true);
                button.setBorderPainted(false);
                chessBoardSquares[column][row] = button;
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