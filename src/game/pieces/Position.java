package game.pieces;

public class Position {
    public final int row, column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /*
        RETURN THE POSITION OF THE PIECE AS A STRING. THE COLUMN VALUE MUST BE
        SUBTRACTED BY 1 SINCE CHESS ROWS ARE ONE-INDEXED.

        EXAMPLE: A KNIGHT ON B1 HAS POSITION VALUE OF (0, 1);
    */
    public String toString() {
        switch(row) {
            case 0: return 'A' + Integer.toString(column - 1);
            case 1: return 'B' + Integer.toString(column - 1);
            case 2: return 'C' + Integer.toString(column - 1);
            case 3: return 'D' + Integer.toString(column - 1);
            case 4: return 'E' + Integer.toString(column - 1);
            case 5: return 'F' + Integer.toString(column - 1);
            case 6: return 'G' + Integer.toString(column - 1);
            case 7: return 'H' + Integer.toString(column - 1);
            default: return "Invalid position.";
        }
    }
}