package game;

import game.pieces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;

/*
    TOOK COMPONENTS FROM https://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel

    TOOK PIECE DESIGNS FROM https://github.com/ornicar/lila/tree/master/public/piece
*/

@SuppressWarnings("serial")
public class Graphics extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel boardPanel;
    private Board board;
    private List<Position> potentialMoves;
    private Piece pressedPiece;
    private boolean whiteTurn;
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

    public Graphics(String name, Board board) {
        super(name);
        this.board = board;
        this.potentialMoves = new ArrayList<>();
        this.whiteTurn = true;
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

        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int column = 0; column < chessBoardSquares.length; column++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);
                Piece piece = board.getPiece(row, column);
                Graphics.setIcon(piece, button);
                Position buttonPosition = new Position(row, column);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pressedPiece == null) {
                            Piece potentialPiece = board.getPiece(buttonPosition.row, buttonPosition.column);

                            if (potentialPiece != null && potentialPiece.isWhite() == whiteTurn) {
                                potentialMoves = potentialPiece.legalMoves();
                                for (Position position : potentialMoves) {
                                    chessBoardSquares[position.row][position.column].setContentAreaFilled(false);
                                }
                                pressedPiece = potentialPiece;
                            }
                        }
                        else {
                            for (Position position : potentialMoves) {
                                chessBoardSquares[position.row][position.column].setContentAreaFilled(true);
                            }
                            if (potentialMoves.contains(buttonPosition)) {
                                Position piecePosition = pressedPiece.getPosition();
                                setIcon(null, chessBoardSquares[piecePosition.row][piecePosition.column]);
                                setIcon(pressedPiece, button);
                                board.move(pressedPiece, buttonPosition);

                                if (pressedPiece.isPawn()) {
                                    // Update icon above/below pawn to account for en passant.
                                    setIcon(board.getPiece(buttonPosition.row, buttonPosition.column), button);
                                    if (pressedPiece.isWhite()) {
                                        setIcon(board.getPiece(buttonPosition.row + 1, buttonPosition.column), chessBoardSquares[buttonPosition.row + 1][buttonPosition.column]);
                                    } else {
                                        setIcon(board.getPiece(buttonPosition.row - 1, buttonPosition.column), chessBoardSquares[buttonPosition.row - 1][buttonPosition.column]);
                                    }

                                    // Handle pawn promotion to queen.
                                    if (buttonPosition.row == 0 || buttonPosition.row == 7) {
                                        new PromoteMenu(buttonPosition, pressedPiece.isWhite(), button, null, board);
                                    }
                                }

                                // Update icon of rook after castling.
                                if (pressedPiece.isKing()) {
                                    if (buttonPosition.column == piecePosition.column + 2) {
                                        setIcon(null, chessBoardSquares[buttonPosition.row][buttonPosition.column + 1]);
                                        setIcon(board.getPiece(buttonPosition.row, buttonPosition.column - 1), chessBoardSquares[buttonPosition.row][buttonPosition.column - 1]);
                                    } else if (buttonPosition.column == piecePosition.column - 2) {
                                        setIcon(null, chessBoardSquares[buttonPosition.row][buttonPosition.column - 2]);
                                        setIcon(board.getPiece(buttonPosition.row, buttonPosition.column + 1), chessBoardSquares[buttonPosition.row][buttonPosition.column + 1]);
                                    }
                                }

                                pressedPiece = null;
                                whiteTurn = !whiteTurn;
                            } else {
                                Piece potentialPiece = board.getPiece(buttonPosition.row, buttonPosition.column);
                                // The piece has been pressed twice in a row.
                                if (pressedPiece.equals(potentialPiece)) {
                                    for (Position position : potentialMoves) {
                                        chessBoardSquares[position.row][position.column].setContentAreaFilled(true);
                                    }
                                    pressedPiece = null;
                                    potentialMoves.clear();
                                } else {
                                    if (potentialPiece != null && potentialPiece.isWhite() == whiteTurn) {
                                        potentialMoves = potentialPiece.legalMoves();
                                        for (Position position : potentialMoves) {
                                            chessBoardSquares[position.row][position.column].setContentAreaFilled(false);
                                        }
                                        pressedPiece = potentialPiece;
                                    } else {
                                        pressedPiece = null;
                                        potentialMoves.clear();
                                    }
                                }
                            }
                        }

                    }
                });

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

                chessBoardSquares[row][column] = button;
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



    public static class PromoteMenu {
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
            Graphics.setIcon(knight, knightButton);
            knightButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    board.removePiece(position.row, position.column);
                    board.insertPiece(knight, position);
                    setIcon(knight, button);
                    if (reverseButton != null) {
                        setIcon(knight, reverseButton);
                    }
                    frame.dispose();
                }
            });
            popup.add(knightButton);

            // Promote to bishop.
            bishopButton = new JButton();
            Bishop bishop = new Bishop(position, isWhite, board);
            Graphics.setIcon(bishop, bishopButton);
            bishopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    board.removePiece(position.row, position.column);
                    board.insertPiece(bishop, position);
                    setIcon(bishop, button);
                    if (reverseButton != null) {
                        setIcon(bishop, reverseButton);
                    }
                    frame.dispose();
                }
            });
            popup.add(bishopButton);

            // Promote to rook.
            rookButton = new JButton();
            Rook rook = new Rook(position, isWhite, board);
            Graphics.setIcon(rook, rookButton);
            rookButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    board.removePiece(position.row, position.column);
                    board.insertPiece(rook, position);
                    setIcon(rook, button);
                    if (reverseButton != null) {
                        setIcon(rook, reverseButton);
                    }
                    frame.dispose();
                }
            });
            popup.add(rookButton);

            // Promote to rook.
            queenButton = new JButton();
            Queen queen = new Queen(position, isWhite, board);
            Graphics.setIcon(queen, queenButton);
            queenButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    board.removePiece(position.row, position.column);
                    board.insertPiece(queen, position);
                    setIcon(queen, button);
                    if (reverseButton != null) {
                        setIcon(queen, reverseButton);
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

}