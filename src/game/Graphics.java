package game;

import game.pieces.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

/*
    TOOK COMPONENTS FROM https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
*/

@SuppressWarnings("serial")
public class Graphics extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel boardPanel;
    private Board board;


    public Graphics(String name, Board board) {
        super(name);
        this.board = board;
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
                Image img = null;
                ImageIcon icon;
                Piece piece = board.getPiece(row, column);
                if (piece == null) {
                    icon = new ImageIcon(
                            new BufferedImage(buttonSideLength, buttonSideLength, BufferedImage.TYPE_INT_ARGB)
                    );
                } else {
                    if (piece.isPawn()) {
                        if (piece.isWhite()) {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        } else {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_pawn.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        }
                    } else if (piece.isKnight()) {
                        if (piece.isWhite()) {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_knight.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        } else {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_knight.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        }
                    } else if (piece.isBishop()) {
                        if (piece.isWhite()) {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_bishop.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        } else {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_bishop.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        }
                    } else if (piece.isRook()) {
                        if (piece.isWhite()) {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_rook.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        } else {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_rook.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        }
                    } else if (piece.isQueen()) {
                        if (piece.isWhite()) {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_queen.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        } else {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_queen.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        }
                    } else if (piece.isKing()) {
                        if (piece.isWhite()) {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/white_king.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        } else {
                            img = new ImageIcon("/Users/alex/Desktop/chess/src/img/black_king.png").getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                        }
                    }
                    icon = new ImageIcon(img);
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

    public static void createAndShowGUI(Board board) {
        Graphics graphics = new Graphics("Chess", board);

        graphics.setLocation(screenSize.width / 4, (screenSize.height - (screenSize.width / 2)) / 2);
        graphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        graphics.addComponentsToPane(graphics.getContentPane());
        
        graphics.pack();
        graphics.setVisible(true);
    }
}