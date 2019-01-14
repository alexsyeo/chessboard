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
*/

@SuppressWarnings("serial")
public class GraphicsTwoBoards extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JButton[][] chessBoardSquaresWhite = new JButton[8][8];
    private final JButton[][] chessBoardSquaresBlack = new JButton[8][8];
    private Board board;
    private List<Position> potentialMoves;
    private Piece pressedPiece;
    private boolean whiteTurn;
    private final ImageIcon whitePawn = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_pawn.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon blackPawn = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_pawn.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon whiteKnight = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_knight.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon blackKnight = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_knight.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon whiteBishop = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_bishop.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon blackBishop = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_bishop.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon whiteRook = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_rook.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon blackRook = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_rook.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon whiteKing = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_king.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon blackKing = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_king.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon whiteQueen = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/white_queen.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon blackQueen = new ImageIcon(new ImageIcon("/Users/alex/Desktop/chess/src/img/black_queen.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
    private final ImageIcon emptySquare = new ImageIcon(new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB));

    public GraphicsTwoBoards(String name, Board board) {
        super(name);
        this.board = board;
        this.potentialMoves = new ArrayList<>();
        this.whiteTurn = true;
        setResizable(false);
    }

    private void addComponentsToPane(final Container pane) {
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.X_AXIS));
        masterPanel.setPreferredSize(new Dimension(1200, 600));
        masterPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel whiteBoardPanel = new JPanel();
        JPanel blackBoardPanel = new JPanel();
        whiteBoardPanel.setLayout(new GridLayout(8, 8));
        whiteBoardPanel.setPreferredSize(new Dimension(480, 480));
        whiteBoardPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK)));
        masterPanel.add(whiteBoardPanel, BorderLayout.WEST);
        blackBoardPanel.setLayout(new GridLayout(8, 8));
        blackBoardPanel.setPreferredSize(new Dimension(480, 480));
        blackBoardPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), new LineBorder(Color.BLACK)));
        masterPanel.add(blackBoardPanel, BorderLayout.EAST);
        Insets buttonMargin = new Insets(0,0,0,0);

        for (int row = 0; row < chessBoardSquaresWhite.length; row++) {
            for (int column = 0; column < chessBoardSquaresWhite.length; column++) {
                int reversedRow = 7 - row;
                int reversedColumn = 7 - column;

                JButton whiteButton = new JButton();
                whiteButton.setMargin(buttonMargin);
                JButton blackButton = new JButton();
                blackButton.setMargin(buttonMargin);

                Piece piece = board.getPiece(row, column);
                setIcon(piece, whiteButton);
                Position buttonPosition = new Position(row, column);
                Position reverseButtonPosition = new Position(reversedRow, reversedColumn);
                whiteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pressedPiece == null) {
                            Piece potentialPiece = board.getPiece(buttonPosition.row, buttonPosition.column);

                            if (whiteTurn && potentialPiece != null && potentialPiece.isWhite()) {
                                potentialMoves = potentialPiece.legalMoves();
                                for (Position position : potentialMoves) {
                                    chessBoardSquaresWhite[position.row][position.column].setContentAreaFilled(false);
                                }
                                pressedPiece = potentialPiece;
                            }
                        }
                        else {
                            for (Position position : potentialMoves) {
                                chessBoardSquaresWhite[position.row][position.column].setContentAreaFilled(true);
                            }
                            if (potentialMoves.contains(buttonPosition)) {
                                Position piecePosition = pressedPiece.getPosition();
                                setIcon(null, chessBoardSquaresWhite[piecePosition.row][piecePosition.column]);
                                setIcon(null, chessBoardSquaresBlack[7 - piecePosition.row][7 - piecePosition.column]);
                                setIcon(pressedPiece, whiteButton);
                                setIcon(pressedPiece, chessBoardSquaresBlack[reverseButtonPosition.row][reverseButtonPosition.column]);
                                board.move(pressedPiece, buttonPosition);

                                // Update icon above/below pawn to account for en passant. Also account for pawn promotion.
                                if (pressedPiece.isPawn()) {
                                    Piece pawn = board.getPiece(buttonPosition.row, buttonPosition.column);
                                    setIcon(pawn, chessBoardSquaresWhite[buttonPosition.row][buttonPosition.column]);
                                    setIcon(pawn, chessBoardSquaresBlack[reverseButtonPosition.row][reverseButtonPosition.column]);
                                    if (pawn.isWhite()) {
                                        Piece pieceBelow = board.getPiece(buttonPosition.row + 1, buttonPosition.column);
                                        setIcon(pieceBelow, chessBoardSquaresWhite[buttonPosition.row + 1][buttonPosition.column]);
                                        setIcon(pieceBelow, chessBoardSquaresBlack[reverseButtonPosition.row - 1][reverseButtonPosition.column]);
                                    } else {
                                        Piece pieceBelow = board.getPiece(buttonPosition.row - 1, buttonPosition.column);
                                        setIcon(pieceBelow, chessBoardSquaresWhite[buttonPosition.row - 1][buttonPosition.column]);
                                        setIcon(pieceBelow, chessBoardSquaresBlack[reverseButtonPosition.row + 1][reverseButtonPosition.column]);
                                    }
                                }

                                // Update icon of rook after castling.
                                if (pressedPiece.isKing()) {
                                    if (buttonPosition.column == piecePosition.column + 2) {
                                        setIcon(null, chessBoardSquaresWhite[buttonPosition.row][buttonPosition.column + 1]);
                                        setIcon(null, chessBoardSquaresBlack[reverseButtonPosition.row][reverseButtonPosition.column - 1]);
                                        Piece rook = board.getPiece(buttonPosition.row, buttonPosition.column - 1);
                                        setIcon(rook, chessBoardSquaresWhite[buttonPosition.row][buttonPosition.column - 1]);
                                        setIcon(rook, chessBoardSquaresBlack[reverseButtonPosition.row][reverseButtonPosition.column + 1]);
                                    } else if (buttonPosition.column == piecePosition.column - 2) {
                                        setIcon(null, chessBoardSquaresWhite[buttonPosition.row][buttonPosition.column - 2]);
                                        setIcon(null, chessBoardSquaresBlack[reverseButtonPosition.row][reverseButtonPosition.column + 2]);
                                        Piece rook = board.getPiece(buttonPosition.row, buttonPosition.column + 1);
                                        setIcon(rook, chessBoardSquaresWhite[buttonPosition.row][buttonPosition.column + 1]);
                                        setIcon(rook, chessBoardSquaresBlack[reverseButtonPosition.row][reverseButtonPosition.column - 1]);
                                    }
                                }

                                pressedPiece = null;
                                whiteTurn = !whiteTurn;
                            } else {
                                Piece potentialPiece = board.getPiece(buttonPosition.row, buttonPosition.column);
                                // The piece has been pressed twice in a row.
                                if (pressedPiece.equals(potentialPiece)) {
                                    for (Position position : potentialMoves) {
                                        chessBoardSquaresWhite[position.row][position.column].setContentAreaFilled(true);
                                    }
                                    pressedPiece = null;
                                    potentialMoves.clear();
                                } else {
                                    if (whiteTurn && potentialPiece != null && potentialPiece.isWhite()) {
                                        potentialMoves = potentialPiece.legalMoves();
                                        for (Position position : potentialMoves) {
                                            chessBoardSquaresWhite[position.row][position.column].setContentAreaFilled(false);
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
                    whiteButton.setBackground(new Color(255, 255, 153));
                }
                // DARK BROWN
                else {
                    whiteButton.setBackground(new Color(153, 102, 0));
                }
                whiteButton.setOpaque(true);
                whiteButton.setBorderPainted(false);

                chessBoardSquaresWhite[row][column] = whiteButton;
                whiteBoardPanel.add(whiteButton);


                // Now we must do the same for the black button.
                piece = board.getPiece(reversedRow, reversedColumn);
                setIcon(piece, blackButton);
                blackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (pressedPiece == null) {
                            Piece potentialPiece = board.getPiece(reverseButtonPosition.row, reverseButtonPosition.column);

                            if (!whiteTurn && potentialPiece != null && !potentialPiece.isWhite()) {
                                potentialMoves = potentialPiece.legalMoves();
                                for (Position position : potentialMoves) {
                                    chessBoardSquaresBlack[7 - position.row][7 - position.column].setContentAreaFilled(false);
                                }
                                pressedPiece = potentialPiece;
                            }
                        }
                        else {
                            for (Position position : potentialMoves) {
                                chessBoardSquaresBlack[7 - position.row][7 - position.column].setContentAreaFilled(true);
                            }
                            if (potentialMoves.contains(reverseButtonPosition)) {
                                Position piecePosition = pressedPiece.getPosition();
                                setIcon(null, chessBoardSquaresWhite[piecePosition.row][piecePosition.column]);
                                setIcon(null, chessBoardSquaresBlack[7 - piecePosition.row][7 - piecePosition.column]);
                                setIcon(pressedPiece, blackButton);
                                setIcon(pressedPiece, chessBoardSquaresWhite[reverseButtonPosition.row][reverseButtonPosition.column]);
                                board.move(pressedPiece, reverseButtonPosition);

                                // Update icon above/below pawn to account for en passant. Also account for pawn promotion.
                                if (pressedPiece.isPawn()) {
                                    Piece pawn = board.getPiece(reverseButtonPosition.row, reverseButtonPosition.column);
                                    setIcon(pawn, chessBoardSquaresBlack[buttonPosition.row][buttonPosition.column]);
                                    setIcon(pawn, chessBoardSquaresWhite[reverseButtonPosition.row][reverseButtonPosition.column]);
                                    if (pawn.isWhite()) {
                                        Piece pieceBelow = board.getPiece(reverseButtonPosition.row + 1, reverseButtonPosition.column);
                                        setIcon(pieceBelow, chessBoardSquaresWhite[reverseButtonPosition.row + 1][reverseButtonPosition.column]);
                                        setIcon(pieceBelow, chessBoardSquaresBlack[buttonPosition.row - 1][buttonPosition.column]);
                                    } else {
                                        Piece pieceBelow = board.getPiece(reverseButtonPosition.row - 1, reverseButtonPosition.column);
                                        setIcon(pieceBelow, chessBoardSquaresWhite[reverseButtonPosition.row - 1][reverseButtonPosition.column]);
                                        setIcon(pieceBelow, chessBoardSquaresBlack[buttonPosition.row + 1][buttonPosition.column]);
                                    }
                                }


                                // Update icon of rook after castling.
                                if (pressedPiece.isKing()) {
                                    if (reverseButtonPosition.column == piecePosition.column + 2) {
                                        setIcon(null, chessBoardSquaresWhite[reverseButtonPosition.row][reverseButtonPosition.column + 1]);
                                        setIcon(null, chessBoardSquaresBlack[buttonPosition.row][buttonPosition.column - 1]);
                                        Piece rook = board.getPiece(reverseButtonPosition.row, reverseButtonPosition.column - 1);
                                        setIcon(rook, chessBoardSquaresWhite[reverseButtonPosition.row][reverseButtonPosition.column - 1]);
                                        setIcon(rook, chessBoardSquaresBlack[buttonPosition.row][buttonPosition.column + 1]);
                                    } else if (reverseButtonPosition.column == piecePosition.column - 2) {
                                        setIcon(null, chessBoardSquaresWhite[reverseButtonPosition.row][reverseButtonPosition.column - 2]);
                                        setIcon(null, chessBoardSquaresBlack[buttonPosition.row][buttonPosition.column + 2]);
                                        Piece rook = board.getPiece(reverseButtonPosition.row, reverseButtonPosition.column + 1);
                                        setIcon(rook, chessBoardSquaresWhite[reverseButtonPosition.row][reverseButtonPosition.column + 1]);
                                        setIcon(rook, chessBoardSquaresBlack[buttonPosition.row][buttonPosition.column - 1]);
                                    }
                                }

                                pressedPiece = null;
                                whiteTurn = !whiteTurn;
                            } else {
                                Piece potentialPiece = board.getPiece(reverseButtonPosition.row, reverseButtonPosition.column);
                                // The piece has been pressed twice in a row.
                                if (pressedPiece.equals(potentialPiece)) {
                                    for (Position position : potentialMoves) {
                                        chessBoardSquaresBlack[7 - position.row][7 - position.column].setContentAreaFilled(true);
                                    }
                                    pressedPiece = null;
                                    potentialMoves.clear();
                                } else {
                                    if (!whiteTurn && potentialPiece != null && !potentialPiece.isWhite()) {
                                        potentialMoves = potentialPiece.legalMoves();
                                        for (Position position : potentialMoves) {
                                            chessBoardSquaresBlack[7 - position.row][7 - position.column].setContentAreaFilled(false);
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
                if ((reversedRow % 2 == 1 && reversedColumn % 2 == 1) || (reversedRow % 2 == 0 && reversedColumn % 2 == 0)) {
                    blackButton.setBackground(new Color(255, 255, 153));
                }
                // DARK BROWN
                else {
                    blackButton.setBackground(new Color(153, 102, 0));
                }
                blackButton.setOpaque(true);
                blackButton.setBorderPainted(false);

                chessBoardSquaresBlack[row][column] = blackButton;
                blackBoardPanel.add(blackButton);
            }
        }

        pane.add(masterPanel);//, BorderLayout.CENTER);
    }

    public static void createAndShowGUI(Board board) {
        GraphicsTwoBoards graphics = new GraphicsTwoBoards("Chess", board);

        graphics.setLocation(screenSize.width / 4, (screenSize.height - (screenSize.width / 2)) / 2);
        graphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphics.addComponentsToPane(graphics.getContentPane());

        graphics.pack();
        graphics.setVisible(true);
    }

    public void setIcon(Piece piece, JButton button) {
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