package game;

public class Game {
    public static void main(String[] args) {
        Board board = new Board(true);
        StartMenu menu = new StartMenu(board);
    }
}