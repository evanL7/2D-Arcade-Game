package Helpers;


public class BoardData {
    private int width;
    private int length;
    private char[][] board; // Assuming the board contains characters, adjust data type as needed
    
    public BoardData(int width, int length) {
        this.width = width;
        this.length = length;
        this.board = new char[width][length];
        initializeBoard(); // Call method to initialize the board
    }
    
    // Method to initialize the board with default values
    private void initializeBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                // Initialize with default value, for example, empty space
                board[i][j] = ' ';
            }
        }
    }
    
    // Getter for width
    public int getWidth() {
        return width;
    }
    
    // Getter for length
    public int getLength() {
        return length;
    }
    
    // Method to update the value at a specific position on the board
    public void updateBoard(int row, int col, char value) {
        if (row >= 0 && row < width && col >= 0 && col < length) {
            board[row][col] = value;
        } else {
            // Handle invalid position
            System.out.println("Invalid position!");
        }
    }
    
    // Method to print the current state of the board
    public void printBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }
}
