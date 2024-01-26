public class Board {
    private String[][] squares;
    public Board() {
        squares = new String[10][10];

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                squares[r][c] = "-";
            }
        }
    }

    public String toString() {
        String board = "";
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                board += String.format(" %s ", squares[r][c]);
            }
            board += "\n";
        }
        return board;
    }

    public boolean addShip(int row, int col, int len, boolean horizontal) {
        if ((horizontal && (col + len) > 10) || (!horizontal && (row + len) > 10)) {
            return false;
        }

        if (horizontal) {
            for (int c = col; c < col + len; c++) {
                squares[row][c] = "b";
            }
        } else {
            for (int r = row; r < row + len; r++) {
                squares[r][col] = "b";
            }
        }
        return true;
    }

    public boolean foundShip(int len) {
        int found = 0;

        // scan rows
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (squares[r][c].equals("b")) {
                    found++;
                    if (found >= len) {
                        return true;
                    }
                } else {
                    found = 0;
                }
            }
        }

        // scan columns
        found = 0;
        for (int c = 0; c < 10; c++) {
            for (int r = 0; r < 10; r++) {
                if (squares[r][c].equals("b")) {
                    found++;
                    if (found >= len) {
                        return true;
                    }
                } else {
                    found = 0;
                }
            }
        }
        return false;
    }

    public int shoot(int row, int col) {
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            return -1;
        }

        if (squares[row][col].equals("-")) {
            squares[row][col] = "m";
            return 0;
        } else if (squares[row][col].equals("b")) {
            squares[row][col] = "x";
        } else {
            return 2;
        }
        return -1; // never reached, but this is for the IDE to not scream
    }

    public boolean gameOver() {
        return !foundShip(1);
    }
}