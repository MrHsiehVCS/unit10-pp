package u10pp;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class IOHelper {

    private static List<List<String>> validYesNoInputs = null; 

    private static List<List<String>> getValidYesNoInputs() {
        if(IOHelper.validYesNoInputs == null) {
            IOHelper.validYesNoInputs = new ArrayList<>(); 
            List<String> validYesInputs = Arrays.asList("Y", "ye", "yes", "yess", "ya", "yea");
            List<String> validNoInputs = Arrays.asList("N", "no", "na", "nah");
            IOHelper.validYesNoInputs.add(validYesInputs);
            IOHelper.validYesNoInputs.add(validNoInputs);
        }
        return IOHelper.validYesNoInputs; 
    }

    /**
     * Repeatedly asks the user the `prompt` until they give a yes or no answer.
     * @return either `"Y"` or `"N"`
     */
    public static String getValidYesNoInput(Scanner scanner, String prompt) {
        return getValidInput(scanner, prompt, getValidYesNoInputs());
    }

    /**
     * Repeatedly asks the user the `prompt` until they input a string that is found in `validInputs`.
     * @param scanner
     * @param prompt 
     * @param validInputs - When creating `validInputs` make each inner list different possible iterations of the same response. 
     *      e.g. When given [["Y", "Yes"]], both of these inputs are accepted, and both would return `"Y"`. 
     *      Case insensitive.
     * @return the first element of the List that the matching valid input was found in. 
     */
    public static String getValidInput(Scanner scanner, String prompt, List<List<String>> validInputs) {
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toUpperCase();
            
            // if input is found in any of the 2d arraylist, 
            // return the .toUpperCase value of the first element of the arraylist it's in
            for(List<String> possibleInputs : validInputs) {
                for(String possibleInput : possibleInputs ) {
                    if(possibleInput.trim().toUpperCase().equals(input)) {
                        return possibleInputs.get(0).trim().toUpperCase();
                    }
                }
            }
            
            System.out.println("Invalid Input. Please try again.");
        }
    }
    
    /**
     * Repeatedly asks the user the `prompt` until they input a number between `min` and `max` (inclusive).
     * @param scanner
     * @param prompt
     * @param min - inclusive
     * @param max - inclusive
     * @return
     */
    public static int getValidNumberInput(Scanner scanner, String prompt, int min, int max) {
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if(input == null || input.length() == 0) {
                System.out.println("No input. Please Try again.");
                continue;
            }
            String numbersOnly = input.replaceAll("[^0-9]", "").trim();
            if(numbersOnly.length() == 0) {
                System.out.println("Please enter a number using the digits 0-9.");
                continue;
            }
            int x = Integer.parseInt(numbersOnly);
            if(x >= min && x <= max) {
                return x;
            } else{
                System.out.println("That number is out of bounds. Please Try again.");
            }
        }
    }

    /**
     * Parses file and returns int[][] representations of the puzzles in the file.
     * Works for both Sudoku and Numbrix
     * @param fileName
     * @return
     */
    public static List<int[][]> getPuzzlesFromFile(String fileName) {
        return getFormattedFileData(fileName).stream()
            .map(IOHelper::convertPuzzle)
            .collect(Collectors.toList());
    }

    private static List<List<String>> getFormattedFileData(String fileName) {
        List<List<String>> fileData = new ArrayList<List<String>>();

        try {
            File file = new File(normalizeFilepath(fileName));
            Scanner fileScanner = new Scanner(file);
            List<String> puzzleData = new ArrayList<String>();
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                if(data.trim().equals("NEW LINE")) {
                    fileData.add(puzzleData);
                    puzzleData = new ArrayList<String>();
                } else
                    puzzleData.add(data.trim());
            }
            fileData.add(puzzleData);
            fileScanner.close();
        } catch (FileNotFoundException error) {
            System.out.println(fileName + " not found");
        }

        return fileData;
    }

    private static String normalizeFilepath(String filename) {
        filename = filename.replace("\\", File.separator);
        filename = filename.replace("/", File.separator);
        return filename;
    }

    private static int[][] convertPuzzle(List<String> puzzle) {
        int[][] retArr = new int[puzzle.size()][puzzle.get(0).split(" ").length];

        for(int r = 0; r < puzzle.size(); r++) {
            String[] row = puzzle.get(r).split(" ");
            for(int c = 0; c < row.length; c++) {
                retArr[r][c] = Integer.parseInt(row[c]);
            }
        }

        return retArr;
    }

    /**
     * Returns a formatted grid from an int[][] puzzle.
     * General purpose.
     * @param puzzle
     * @return
     */
    public static String formatPuzzle(int[][] puzzle) {
        String retStr = " ";
        int largestNum = getLargestNum(puzzle);
        int neededSpaces = (largestNum + " ").length();

        for(int[] row : puzzle) {
            String line = "";
            for(int num : row) {
                String digits = (""+ num);
                line += " ".repeat(neededSpaces - digits.length() + 1) + num;
            }
            line = line.trim();
            retStr += line + "\n";
        }
            
        return retStr.trim();
    }

    /**
     * returns a formatted grid of a SudokuPuzzle.
     * @param puzzle
     * @return
     */
    public static String formatSudokuPuzzle(int[][] puzzle) {
        String retStr = " ";
        int largestNum = getLargestNum(puzzle);
        int blockWidth = (int)Math.sqrt(puzzle.length);
        int neededSpaces = (largestNum+" ").length();

        String dashedLine = ("+" + "-".repeat(neededSpaces*blockWidth-1)).repeat(blockWidth) + "+" + "\n";
        for(int r = 0; r < puzzle.length; r++) {
            String line = "";
            if(r % blockWidth == 0) {
                retStr += dashedLine;
            }
            for(int c = 0; c < puzzle[r].length; c++) {
                if(c % blockWidth == 0) {
                    line += "|";
                } else {
                    line += " ";
                }
                String digits = (""+ puzzle[r][c]);
                line += " ".repeat(neededSpaces - digits.length()-1) + puzzle[r][c];
            }
            line += "|";
            line = line.trim();
            retStr += line;
            if (r != puzzle.length - 1) {
                retStr += "\n";
            }
        }
        retStr += dashedLine ;            
        return retStr.trim();
    }

    /**
     * returns a formatted grid of a numbrix puzzle.
     * Includes lines to connect adjacent answers. 
     * @param puzzle
     * @return
     */
    public static String formatNumbrixPuzzle(int[][] puzzle) {
        int largestNum = getLargestNum(puzzle);
        int digitsPer = Integer.toString(largestNum).length();
        String output = "";
        for(int r = 0; r < puzzle.length; r++) {

            String gapLine = "";
            String numbersLine = "";

            for(int c = 0; c < puzzle[r].length; c++) {
                // gap between rows
                if(r != 0) {
                    if(Math.abs(puzzle[r][c] - puzzle[r-1][c]) == 1
                        && puzzle[r][c] > 0
                        && puzzle[r-1][c] > 0) {
                        // create line
                        gapLine +=  " ".repeat(digitsPer) + "|";
                    } else {
                        gapLine += " ".repeat(digitsPer + 1);
                    }
                }

                // numbers
                int currNumLength = Integer.toString(puzzle[r][c]).length();
                String filler = " ";
                if(c != 0 
                    && Math.abs(puzzle[r][c] - puzzle[r][c-1]) == 1
                    && puzzle[r][c] > 0
                    && puzzle[r][c-1] > 0) {
                    filler = "-";
                }
                numbersLine += filler.repeat(digitsPer - currNumLength + 1);
                numbersLine += puzzle[r][c];
            }
            if(!gapLine.isEmpty()) {
                output += gapLine + "\n";
            }
            output += numbersLine;
            if(r != puzzle.length-1) {
                output += "\n";
            }
        }
        return output;
    }

    private static int getLargestNum(int[][] nums) {
        return Arrays.stream(nums).flatMapToInt(Arrays::stream).max().orElse(0);
    }
}
