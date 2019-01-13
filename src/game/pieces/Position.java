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
        String rowNumber = Integer.toString(8 - row);
        switch(column) {
            case 0: return 'A' + rowNumber;
            case 1: return 'B' + rowNumber;
            case 2: return 'C' + rowNumber;
            case 3: return 'D' + rowNumber;
            case 4: return 'E' + rowNumber;
            case 5: return 'F' + rowNumber;
            case 6: return 'G' + rowNumber;
            case 7: return 'H' + rowNumber;
            default: return "Invalid position.";
        }
    }

    /*
        OVERRIDING EQUALS METHOD SO THAT TWO POSITIONS ARE EQUAL IF THEY HAVE
        THE SAME ROW AND COLUMN VALUES.
    */
    @Override
    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;

        return position.row == row && position.column == column;
    }
}