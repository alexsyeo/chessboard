package game;

public class Player {
    private int pawnCount, rookCount, knightCount, bishopCount, queenCount;

    public Player() {
        pawnCount = 8;
        rookCount = 2;
        knightCount = 2;
        bishopCount = 2;
        queenCount = 1;
    }

    public int getPawnCount() {
        return pawnCount;
    }

    public void losePawn() {
        pawnCount--;
    }

    public int getRookCount() {
        return rookCount;
    }

    public void loseRook() {
        rookCount--;
    }

    public void gainRook() {
        rookCount++;
    }

    public int getKnightCount() {
        return knightCount;
    }

    public void loseKnight() {
        knightCount--;
    }

    public void gainKnight() {
        knightCount++;
    }

    public int getBishopCount() {
        return bishopCount;
    }

    public void loseBishop() {
        bishopCount--;
    }

    public void gainBishop() {
        bishopCount++;
    }

    public int getQueenCount() {
        return queenCount;
    }

    public void loseQueen() {
        queenCount--;
    }

    public void gainQueen() {
        queenCount++;
    }
}