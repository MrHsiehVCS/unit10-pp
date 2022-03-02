package u10pp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.List;

public class SudokuSolverTests {
    private List<int[][]> testPuzzles = IOHelper.getPuzzlesFromFile("src\\test\\java\\sudokuPuzzlesTests.txt");
    private int[][] 
    puzzle1Sol = {{1}},
    puzzle2Sol = {{1}}, 
    puzzle3Sol = {{2, 3, 4, 1}, 
                  {1, 4, 3, 2},
                  {4, 2, 1, 3},
                  {3, 1, 2, 4}}, 
    puzzle4Sol = {{9, 2, 6, 5, 1, 4, 3, 7, 8},
                  {1, 8, 5, 3, 7, 9, 6, 2, 4},
                  {4, 7, 3, 6, 2, 8, 9, 1, 5},
                  {7, 3, 2, 4, 9, 5, 1, 8, 6},
                  {6, 4, 9, 1, 8, 3, 7, 5, 2},
                  {8, 5, 1, 2, 6, 7, 4, 9, 3},
                  {2, 6, 7, 8, 3, 1, 5, 4, 9},
                  {3, 9, 4, 7, 5, 2, 8, 6, 1},
                  {5, 1, 8, 9, 4, 6, 2, 3, 7}},
    puzzle5Sol = {{5, 3, 1, 9, 7, 4, 8, 2, 6},
                  {2, 4, 9, 6, 8, 3, 5, 1, 7},
                  {8, 7, 6, 1, 5, 2, 9, 3, 4},
                  {7, 2, 3, 5, 6, 8, 4, 9, 1},
                  {1, 8, 4, 3, 9, 7, 2, 6, 5},
                  {9, 6, 5, 4, 2, 1, 3, 7, 8},
                  {3, 5, 8, 2, 1, 6, 7, 4, 9},
                  {6, 9, 2, 7, 4, 5, 1, 8, 3},
                  {4, 1, 7, 8, 3, 9, 6, 5, 2}};


    @Test
    public void puzzle1_SudokuSolve_returnsCorrectAnswer() {
        int[][] solution = SudokuSolver.solve(testPuzzles.get(0));
        assertArrayEquals(puzzle1Sol, solution);
    }

    @Test
    public void puzzle2_SudokuSolve_returnsCorrectAnswer() {
        int[][] solution = SudokuSolver.solve(testPuzzles.get(1));
        assertArrayEquals(puzzle2Sol, solution);
    }

    @Test
    public void puzzle3_SudokuSolve_returnsCorrectAnswer() {
        int[][] solution = SudokuSolver.solve(testPuzzles.get(2));
        assertArrayEquals(puzzle3Sol, solution);
    }

    @Test
    public void puzzle4_SudokuSolve_returnsCorrectAnswer() {
        int[][] solution = SudokuSolver.solve(testPuzzles.get(3));
        assertArrayEquals(puzzle4Sol, solution);
    }

    @Test
    public void puzzle5_SudokuSolve_returnsCorrectAnswer() {
        int[][] solution = SudokuSolver.solve(testPuzzles.get(4));
        assertArrayEquals(puzzle5Sol, solution);
    }

}
