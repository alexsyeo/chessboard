package graphics;

import game.Board;
import game.pieces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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

    public Graphics(Board board) {
        this("Chess", board);
    }

    public Graphics(String name, Board board) {
        super(name);
        this.board = board;
        this.potentialMoves = new ArrayList<>();
        this.whiteTurn = true;
        setResizable(false);
    }

    private void addComponentsToPane(final Container pane) {
        Graphics graphics = this;
        JPanel masterPanel = new JPanel(new BorderLayout());
        masterPanel.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.width / 2));
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));
        masterPanel.add(boardPanel, BorderLayout.CENTER);
        Insets buttonMargin = new Insets(0,0,0,0);

        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int column = 0; column < chessBoardSquares.length; column++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);
                Piece piece = board.getPiece(row, column);
                GraphicsTools.setIcon(piece, button);
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
                                GraphicsTools.setIcon(null, chessBoardSquares[piecePosition.row][piecePosition.column]);
                                GraphicsTools.setIcon(pressedPiece, button);
                                board.move(pressedPiece, buttonPosition);

                                if (pressedPiece.isPawn()) {
                                    // Update icon above/below pawn to account for en passant.
                                    GraphicsTools.setIcon(board.getPiece(buttonPosition.row, buttonPosition.column), button);
                                    if (pressedPiece.isWhite()) {
                                        GraphicsTools.setIcon(board.getPiece(buttonPosition.row + 1, buttonPosition.column), chessBoardSquares[buttonPosition.row + 1][buttonPosition.column]);
                                    } else {
                                        GraphicsTools.setIcon(board.getPiece(buttonPosition.row - 1, buttonPosition.column), chessBoardSquares[buttonPosition.row - 1][buttonPosition.column]);
                                    }

                                    // Handle pawn promotion to queen.
                                    if (buttonPosition.row == 0 || buttonPosition.row == 7) {
                                        new PromoteMenu(buttonPosition, pressedPiece.isWhite(), button, null, board);
                                    }
                                }

                                // Update icon of rook after castling.
                                if (pressedPiece.isKing()) {
                                    if (buttonPosition.column == piecePosition.column + 2) {
                                        GraphicsTools.setIcon(null, chessBoardSquares[buttonPosition.row][buttonPosition.column + 1]);
                                        GraphicsTools.setIcon(board.getPiece(buttonPosition.row, buttonPosition.column - 1), chessBoardSquares[buttonPosition.row][buttonPosition.column - 1]);
                                    } else if (buttonPosition.column == piecePosition.column - 2) {
                                        GraphicsTools.setIcon(null, chessBoardSquares[buttonPosition.row][buttonPosition.column - 2]);
                                        GraphicsTools.setIcon(board.getPiece(buttonPosition.row, buttonPosition.column + 1), chessBoardSquares[buttonPosition.row][buttonPosition.column + 1]);
                                    }
                                }

                                if (whiteTurn && board.blackKingInCheckmate()) {
                                    new EndMenu(graphics, true);
                                } else if (!whiteTurn && board.whiteKingInCheckmate()) {
                                    new EndMenu(graphics, false);
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
}