import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        boolean cont = true;

        // set up game
        while (cont) {
            System.out.println("Enter \"a\" to add a new ship, \"b\" to see the board, \"p\" to play, or \"q\" to quit.");
            String userOption = scanner.nextLine();

            if (userOption.equalsIgnoreCase("a")) {
                int row = InputHelper.getRangedInt(scanner, "Enter a row: [1-10]", 0, 9) + 1;
                int col = InputHelper.getRangedInt(scanner, "Enter a column: [1-10] ", 0, 9) + 1;
                int len = InputHelper.getRangedInt(scanner, "Enter a length: [3-5] ", 3, 5);
                boolean horizontal = InputHelper.getYNConfirm(scanner, "Is it horizontal [Y/N]?");
                if (board.addShip(row, col, len, horizontal)) {
                    System.out.println("New ship added!");
                } else {
                    System.out.println("Can't put a ship there!");
                }
            } else if (userOption.equalsIgnoreCase("b")) {
                System.out.println(board.toString());
            } else if (userOption.equalsIgnoreCase("p")) {
                if (board.foundShip(3) && board.foundShip(4)) {
                    System.out.println("OK, let's play!");
                } else {
                    System.out.println("You need a ship of length 3 and 4 before you can play!");
                }
            } else if (userOption.equalsIgnoreCase("q")) {
                cont = false;
            }
        }

        // play game
        while (cont) {
            System.out.println("Enter \"s\" to shoot at a square, \"b\" to see the board, \"q\" to quit.");
            String userOption = scanner.nextLine();

            if (userOption.equalsIgnoreCase("s")) {
                int row = InputHelper.getRangedInt(scanner, "Enter a row: [1-10]", 0, 9) + 1;
                int col = InputHelper.getRangedInt(scanner, "Enter a column: [1-10] ", 0, 9) + 1;
                int result = board.shoot(row, col);

                if (result == 0) {
                    System.out.println("Miss!");
                } else if (result == 1) {
                    System.out.println("Hit!");
                } else if (result == 2) {
                    System.out.println("You forgot to shoot somewhere else!");
                } else {
                    System.out.println("Invalid coordinates");
                }
            } else if (userOption.equalsIgnoreCase("b")) {
                System.out.println(board.toString());
            } if (userOption.equalsIgnoreCase("q")) {
                cont = false;
            }
        }
    }
}