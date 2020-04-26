public class Sudoku {

    public static void main(String[] args) throws Exception {
        Sudoku demo = new Sudoku();
        int[][] sudoku = demo.new_sudoku();
        demo.print_sudoku(sudoku);
        try {
            System.out.println(demo.fill_sudoku(sudoku, 0, 0));
        } catch (Exception e) {
            demo.print_sudoku(sudoku);
        }
    }

    private boolean fill_sudoku(int[][] sudoku, int i, int j) throws Exception {
        if (sudoku[i][j] == 0) {
            boolean flag = false;
            for (int num = 1; num <= 9; num++) {
                if (can_fill(sudoku, i, j, num)) {
                    sudoku[i][j] = num;
                    flag = fill_sudoku(sudoku, next_i(i, j), next_j(i, j));
                }
            }
            if (!flag) {
                sudoku[i][j] = 0;
            }
            return flag;
        } else {
            return fill_sudoku(sudoku, next_i(i, j), next_j(i, j));
        }
    }

    private int next_i(int i, int j) throws Exception {
        if (i == 8 && j == 8) {
            throw new Exception("not match");
        } else if (i == 8) {
            return 0;
        } else {
            return i + 1;
        }
    }

    private int next_j(int i, int j) throws Exception {
        if (i == 8 && j == 8) {
            throw new Exception("not match");
        } else if (i == 8) {
            return j + 1;
        } else {
            return j;
        }
    }

    private boolean can_fill(int[][] sudoku, int i, int j, int num) {
        for (int index = 0; index < 9; index++) {
            // row
            if (sudoku[i][index] == num) return false;
            // col
            if (sudoku[index][j] == num) return false;
        }
        // inside
        for (int _i = i / 3 * 3; _i < i / 3 * 3 + 3; _i++) {
            for (int _j = j / 3 * 3; _j < j / 3 * 3 + 3; _j++) {
                if (sudoku[_i][_j] == num) return false;
            }
        }
        return true;
    }

    private int[][] new_sudoku() {
        return new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 2, 0},
                {0, 8, 0, 3, 0, 0, 7, 0, 1},
                {0, 6, 0, 8, 2, 0, 0, 5, 0},
                // --------------------------
                {0, 0, 0, 7, 0, 0, 0, 0, 0},
                {0, 0, 5, 0, 0, 0, 9, 0, 4},
                {0, 0, 0, 0, 9, 5, 0, 6, 0},
                // --------------------------
                {0, 0, 0, 0, 0, 3, 0, 0, 2},
                {0, 0, 0, 0, 1, 7, 8, 0, 0},
                {4, 0, 3, 0, 0, 0, 0, 0, 5},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                // --------------------------
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                // --------------------------
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
    }

    private void print_sudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            if  (i % 3 == 0) {
                System.out.println("-------------------------------");
            }
            for (int j = 0; j < sudoku[i].length; j++) {
                if (j % 3 == 0) {
                    System.out.print("|");
                }
                System.out.print(" " + sudoku[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-------------------------------");
    }

}
