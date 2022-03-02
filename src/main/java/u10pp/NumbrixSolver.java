// Optional class, implement for extra credit

import java.util.*;

public class NumbrixSolver{

    public static int[][] solve(int[][] puzzle) {
        int largestNum = puzzle.length * puzzle[0].length;

        // get all preplaced nums
        Set<Integer> preplacedNums = new HashSet<>();
        for(int[] line : puzzle) {
            for(int n : line) {
                if (n != 0) {
                    preplacedNums.add(n);
                }
            }
        }
        
        // try recursive solution, starting from each space. 
        for(int r = 0; r < puzzle.length; r++) {
            for(int c = 0; c < puzzle[r].length; c++) {
                if(isValid(puzzle, r, c, 1, largestNum, preplacedNums)) {
                    return puzzle;
                }
            }
        }
        return puzzle;
    }

    private static boolean isValid(int[][] puzzle, int row, int col, int val, int largestNum, Set<Integer> preplacedNums) {
        
        if(val > largestNum) {
            return true;
        }

        if(row >= puzzle.length || row < 0 || col >= puzzle[row].length || col < 0) {
            return false;
        }

        if(preplacedNums.contains(val)) {
            // must pick the right spot for this preplaced number
            if( puzzle[row][col] != val) {
                return false;
            }
        }
        else if(puzzle[row][col] != 0) {
            // should never reach this case, if preplacedNums is created correctly
            return false;
        }

        int oldVal = puzzle[row][col];
        puzzle[row][col] = val;

        if (isValid(puzzle, row+1, col, val+1, largestNum, preplacedNums)) {
            return true;
        }

        if (isValid(puzzle, row-1, col, val+1, largestNum, preplacedNums)) {
            return true;
        }

        if (isValid(puzzle, row, col+1, val+1, largestNum, preplacedNums)) {
            return true;
        }

        if (isValid(puzzle, row, col-1, val+1, largestNum, preplacedNums)) {
            return true;
        }

        puzzle[row][col] = oldVal;
        return false;
    }
}