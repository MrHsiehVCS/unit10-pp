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

    public static String getValidYesNoInput(Scanner scanner, String prompt) {
        return getValidInput(scanner, prompt, getValidYesNoInputs());
    }

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
     * 
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

    public static List<int[][]> getPuzzlesFromFile(String fileName) {
        return getFormattedFileData(fileName).stream()
            .map(IOHelper::convertPuzzle)
            .collect(Collectors.toList());
    }

    private static List<List<String>> getFormattedFileData(String fileName) {
        List<List<String>> fileData = new ArrayList<List<String>>();

        try {
            File file = new File(fileName);
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
                    if(Math.abs(puzzle[r][c] - puzzle[r-1][c]) == 1) {
                        // create line
                        gapLine +=  " ".repeat(digitsPer) + "|";
                    } else {
                        gapLine += " ".repeat(digitsPer + 1);
                    }
                }

                // numbers
                int currNumLength = Integer.toString(puzzle[r][c]).length();
                String filler = " ";
                if(c != 0 && Math.abs(puzzle[r][c] - puzzle[r][c-1]) == 1) {
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
