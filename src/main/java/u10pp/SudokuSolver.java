package u10pp;

import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    public static int[][] solve(int[][] puzzle) {
        int maxNumAllowed = puzzle.length;
        solveHelper(puzzle, maxNumAllowed);
        return puzzle;
    }

    public static boolean solveHelper(int[][] puzzle, int maxNumAllowed) {

        for(int r = 0; r < puzzle.length; r++) {
            for(int c = 0; c < puzzle.length; c++) {
                if(puzzle[r][c] != 0) {
                    continue;
                }
                for(int i = 1; i <= maxNumAllowed; i++) {
                    boolean attemptValid = isValidPlacement(puzzle, maxNumAllowed, r, c, i);
                    if(attemptValid) {
                        puzzle[r][c] = i;
                        attemptValid = solveHelper(puzzle, maxNumAllowed);
                        if(attemptValid) {
                            return true;
                        }
                        puzzle[r][c] = 0;
                    }
                }
                return false;
                
            }
        }
        return true;
    }
    
    private static boolean isValidPlacement(int[][] puzzle, int maxNumAllowed, int row, int col, int val) {
        int oldVal = puzzle[row][col];
        puzzle[row][col] = val;
        boolean isValid = isRowValid(puzzle, maxNumAllowed, row) 
            && isColValid(puzzle, maxNumAllowed, col) 
            && isBlockValid(puzzle, maxNumAllowed, row, col);
        puzzle[row][col] = oldVal;
        return isValid;
    }
    
    private static boolean isRowValid(int[][] puzzle, int maxNumAllowed, int row) {
        List<Integer> list = new ArrayList<>();
        for(int i : puzzle[row]) {
            list.add(i);
        }
        return isNumListValid(list, maxNumAllowed);
    }
    
    private static boolean isColValid(int[][]puzzle, int maxNumAllowed, int col) {
        List<Integer> list = new ArrayList<>();
        for(int[] line : puzzle) {
            list.add(line[col]);
        }
        return isNumListValid(list, maxNumAllowed);
    }
    
    private static boolean isBlockValid(int[][] puzzle, int maxNumAllowed, int row, int col) {
        List<Integer> list = new ArrayList<Integer>();
        
        int blockSize = (int)Math.sqrt(maxNumAllowed);
        int startRow = row/blockSize*blockSize; 
        int startCol = col/blockSize*blockSize;

        for(int r = startRow; r < startRow + blockSize; r++) {
            for (int c = startCol; c < startCol + blockSize; c++) {
                list.add(puzzle[r][c]);
            }
        }
        
        return isNumListValid(list, maxNumAllowed);
    }
    
    private static boolean isNumListValid(List<Integer> list, int maxNumAllowed) {
        List<Integer> numbers = list.stream().filter(i -> i > 0).collect(Collectors.toList());
        List<Integer> numbersWithoutDuplicates = numbers.stream().distinct().collect(Collectors.toList());

        if(numbers.size() != numbersWithoutDuplicates.size()) {
            return false;
        }
        for(int i : list) {
            if(i < 0 || i > maxNumAllowed) {
                return false;
            }
        }
        return true;
    }
}