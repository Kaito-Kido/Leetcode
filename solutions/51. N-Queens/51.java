class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        backtrack(solutions, board, 0);

        return solutions;
    }

    private void backtrack(List<List<String>> solutions, char[][] board, int row) {
        int n = board.length;
        if (row == n) {
            pushToSolution(solutions, board);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (!isValid(board, row, j)) continue;
            board[row][j] = 'Q';

            backtrack(solutions, board, row+1);

            board[row][j] = '.';
        }
    }

    private void pushToSolution(List<List<String>> solutions, char[][] board) {
        List<String> solution = new ArrayList<>();
        int n = board.length;

        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(board[i][j]);
            }
            solution.add(row.toString());
        }

        solutions.add(solution);
    }

    private boolean isValid(char[][] board, int row, int j) {
        int n = board.length;

        for(int r = row; r >=0; r--) {
            if (board[r][j] == 'Q') return false;
        }

        for (int c = j, r = row; c >=0 && r >=0; c--, r--) {
            if (board[r][c] == 'Q') return false;
        }

        for (int c = j, r = row; c < n && r >=0; c++, r--) {
            if (board[r][c] == 'Q') return false;
        }

        return true;
    }
}