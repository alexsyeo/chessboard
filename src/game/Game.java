package game;

public class Game {
    public static void main(String[] args) {
        Board board = new Board(true);
//        Graphics.createAndShowGUI(board);
        GraphicsTwoBoards.createAndShowGUI(board);
    }
}