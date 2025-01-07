import java.util.Scanner;

// Game interface, declaring the method play() that all games must implement(if there are multiple games)
interface Game {
    void play();
}

// Abstract class BoardGame implements the Game interface
// If there are multiple boardgames this will be halpful
abstract class BoardGame implements Game {
    // can be accesses in class and subclass only
    // Encapsulation used here
    protected String[] board;
    protected String turn;
    // Abstract methods that must be implemented in subclasses
    abstract String checkWinner();
    abstract void printBoard();
    // abstract return type method()
}

class TicTacToe extends BoardGame {

    // Constructor to initialize the board and set "X" as the first player
    TicTacToe() {
        board = new String[9];
        // creates an array of string of size 9
        turn = "X";
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
    }

    // Method to start and control the gameplay loop
    public void play() {
        Scanner sc = new Scanner(System.in);
        String winner = null;

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();  // Display the initial board
        System.out.println("X will play first. Enter a slot number to place X in:");


        while (winner == null) {
            int numInput;

            try {
                numInput = sc.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
                if (board[numInput - 1].equals(String.valueOf(numInput))) {
                    board[numInput - 1] = turn;

                    turn = turn.equals("X") ? "O" : "X";
                    printBoard();

                    // Check if there's a winner after the move
                    winner = checkWinner();
                } else {
                    // If the selected slot is already taken, prompt again
                    System.out.println("Slot already taken; re-enter slot number:");
                }
            } catch (Exception e) {
                // Handle invalid input (non-integer values) by prompting again
                System.out.println("Invalid input; please enter a number between 1 and 9.");
                 sc.next();
            }
        }

        // Print the result of the game
        if ("draw".equalsIgnoreCase(winner)) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
        }
        sc.close();  // Close the scanner resource after the game ends
    }

    // Method to check the current state of the board and determine if there's a winner
    String checkWinner() {
        // List of all possible win conditions (rows, columns, diagonals)
        for (int i = 0; i < 8; i++) {
            String line = null;

            // Determine the line (row, column, or diagonal) to check
            switch (i) {
                case 0: line = board[0] + board[1] + board[2]; break;
                case 1: line = board[3] + board[4] + board[5]; break;
                case 2: line = board[6] + board[7] + board[8]; break;
                case 3: line = board[0] + board[3] + board[6]; break;
                case 4: line = board[1] + board[4] + board[7]; break;
                case 5: line = board[2] + board[5] + board[8]; break;
                case 6: line = board[0] + board[4] + board[8]; break;
                case 7: line = board[2] + board[4] + board[6]; break;
            }

            // Check if any line is filled with "XXX" or "OOO", indicating a winner
            if (line.equals("XXX")) return "X";
            else if (line.equals("OOO")) return "O";
        }

        // If all cells are filled and there's no winner, return "draw"
        for (int i = 0; i < 9; i++) {
            if (board[i].equals(String.valueOf(i + 1))) break;
            else if (i == 8) return "draw";
        }

        return null;
    }

    // Method to print the current board state to the console
    void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }
}

public class project {
    public static void main(String[] args) {
        // ticTacToe is object of type game(interface) that TicTacToe implements
        Game ticTacToe = new TicTacToe();
        ticTacToe.play();
    }
}
