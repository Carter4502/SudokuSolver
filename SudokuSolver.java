package com.company;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{{'8','.','.','.','.','.','.','.','.'},
                                      {'.','.','3','6','.','.','.','.','.'},
                                      {'.','7','.','.','9','.','2','.','.'},
                                      {'.','5','.','.','.','7','.','.','.'},
                                      {'.','.','.','.','4','5','7','.','.'},
                                      {'.','.','.','1','.','.','.','3','.'},
                                      {'.','.','1','.','.','.','.','6','8'},
                                      {'.','.','8','5','.','.','.','1','.'},
                                      {'.','9','.','.','.','.','4','.','.'}};
        if (solve(board)) {
            printSudoku(board);
        } else {
            System.out.println("Impossible board.");
        }
    }

    public static void printSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            System.out.print("[");
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("]\n");
        }
    }

    public static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    for (int k = 1; k <= 9; k++) {
                        char c = (char) (k + '0');
                        if (validInsertion(c, board, i, j)) {
                            board[i][j] = c;
                            if (solve(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean inRow(char digit, char[][] board, int rowNum) {
        for (int i = 0; i < 9; i++) {
            if (board[rowNum][i] == digit) {
                return true;
            }
        }
        return false;
    }

    public static boolean inCol(char digit, char[][] board, int colNum) {
        for (int i = 0; i < 9; i++) {
            if (board[i][colNum] == digit) {
                return true;
            }
        }
        return false;
    }

    public static boolean inBox(char digit, char[][] board, int x, int y) {

        x *= 3;
        y *= 3;

        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == digit) {
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean validInsertion(char digit, char[][] board, int i, int j) {
        return !inRow(digit, board, i) && !inCol(digit, board, j) && !inBox(digit, board, i / 3, j / 3);
    }

}
