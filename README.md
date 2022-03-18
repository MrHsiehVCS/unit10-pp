# Unit 10 Programming Project

In this programming project you are going create a recursive program that solves Sudoku, as well as solve a few smaller recursive programming challenges.

## Allowed Resources for the Project

You are allowed to use any of the following to help you with your project:

- any online Java API documentation
- Group discssion in class or online
- Online concept tutorials (Examples: *How does Recursion Work?* *How to Parse a String into an int in Java?*)
- Tutorials on what a Sudoku or Nubrix puzzle is and how it works

You are not permitted to use any of the following:

- Any tutorials on how to code solutions to the problems presented in this programming project, in any programming language. 

## Grading Breakdown

- Attempt code for all test cases: 30 points
- JavaDoc all code: 20 points
- Pass all test cases (without hard-coding solutions): 50 points
- Extra Credit for coding `NumbrixSolver`

## Part 1: `RecursiveMath`
In a public class called `RecursiveMath`, implement the following functions: 
- `public static double pow(double base, int exponent)` 
  - returns `base` raised to the `exponent`. (e.g. `pow(2,4)` returns `16`)
  - *Precondition*: `exponent` is non-negative
- `public static BigInteger getFactorial(int n)` 
  - returns `n` factorial.
  - *Precondition*: `n` is non-negative
  - Google the documentation for BigInteger. You will only really need `BigInteger.multiply()` and `BigInteger.valueOf()`.
    - It is not necessary to know the `BigInteger` documentation for the AP Test, and it will not be tested in this class. The point of using `BigInteger` is more to develop your ability to find, understand, and utilize preexisting tools. 
- `public static int getFibonacciNumber(int n)` 
  - returns the `n`th number in the fibonacci series
    - Each fibonacci number is the sum of the two fibonacci numbers preceding it.
    - The first and second fibonacci number are both 1.  
  - *Precondition*: `n` is positive
  - **Note!!!** Running this number with anything above 50 will take a long time. It is recomended to test with 40 and below. 
  - Here is a mapping of the early fibonacci numbers: 

    | n | fib |
    | - | -- |
    | 1 | 1  |
    | 2 | 1  |
    | 3 | 2  |
    | 4 | 3  |
    | 5 | 5  |
    | 6 | 8  |
    | 7 | 13 |

It is recommended to implement the math functions in the order they are given above.

## Part 2: `RecursivePalindromeChecker`
Implement the following function in the `RecursivePalindromeChecker` class:
- `public static boolean isPalindrome(String s)` 
  - returns `true` if the `String` is a palindrome. 
  - Preconditions: input will only contain lowercase letters, from a-z. No capitals/numbers/symbols/whitespace.

## Part 3: `SudokuSolver`

### What is Sudoku?

A Sudoku puzzle is a puzzle where you are given an `n x n` board and you have to fill it is so that each space contains a number `1 -  n`. Every row, column, and block must not have any repeating digits. A block is a square group of spaces that is `sqrt(n)` by `sqrt(n)`, without overlap. There are `n` blocks, and they are arranged in a non-overlapping grid.

Examples can be found at <https://www.websudoku.com/>

### `SudokuSolver` Details

You are given a set of puzzles in `sudokuPuzzles.txt`. Your job is to implement the class `SudokuSolver`.  
In your class, you must have the following `public` method:

- `public static int[][] solve(int[][] puzzle)` 
  - returns a solved puzzle
  - *Precondition*: puzzle will always have a solution

You may create any instance variables or methods that you want in order to solve this project
Your solution must use recursion.

### `SudokuSolver` Example Run

A file with the following initialBoards

    0
    NEW LINE
    2 0 4 0
    0 3 0 1
    0 2 0 4
    1 0 3 0
    NEW LINE
    8 0 0 0 0 0 0 0 0 
    0 0 3 6 0 0 0 0 0 
    0 7 0 0 9 0 2 0 0 
    0 5 0 0 0 7 0 0 0 
    0 0 0 0 4 5 7 0 0 
    0 0 0 1 0 0 0 3 0 
    0 1 0 0 0 0 6 8 0
    0 0 8 5 0 0 0 1 0 
    0 9 0 0 0 0 4 0 0

Would result in the following solved boards

    +-+
    |1|
    +-+
    +---+---+
    |2 1|4 3|
    |4 3|2 1|
    +---+---+
    |3 2|1 4|
    |1 4|3 2|
    +---+---+
    +-----+-----+-----+
    |8 2 1|7 5 4|3 6 9|
    |9 4 3|6 1 2|8 5 7|
    |5 7 6|8 9 3|2 4 1|
    +-----+-----+-----+
    |3 5 4|9 6 7|1 2 8|
    |1 8 2|3 4 5|7 9 6|
    |7 6 9|1 2 8|5 3 4|
    +-----+-----+-----+
    |2 1 7|4 3 9|6 8 5|
    |4 3 8|5 7 6|9 1 2|
    |6 9 5|2 8 1|4 7 3|
    +-----+-----+-----+

## Extra Credit: `NumbrixSolver`

### What is Numberix?

A Numbrix puzzle is a puzzle where you are given an `n x n` board and you have to fill it in so that the numbers `1 -  (n * n)` are all on the board. Easy you might say. Well, the trick is that consecutive numbers have to be adjacent to each other, and diagonals do not count. Also, some boards might have some values already filled in.
Examples can be found at <https://parade.com/numbrix/>

### `NumbrixSolver` Details

You are given a set of puzzles in `numbrixPuzzles.txt`. Your job is to implement the class `NumbrixSolver`. In your class, you must have the following `public` method:

- `public static int[][] solve(int[][] puzzle)` 
  - returns a solved puzzle
  - *Precondition*: the puzzle will always have a solution

You may create any instance variables or methods that you want in order to solve this project
Your solution must use recursion.

Hint: Make sure to check whether a number already exists before recursing with a temporary version of it. If you don't, your code will take forever to run.

### `NumbrixSolver` Example Run

A file with the following initialBoards

    1 0 3
    0 0 0
    9 0 0
    NEW LINE
    67 00 00 00 51 00 00 00 15
    00 00 00 61 00 53 00 00 00
    00 00 00 00 00 00 00 00 00
    00 71 00 00 00 00 00 19 00
    77 00 00 00 00 00 00 00 11
    00 79 00 00 00 00 00 09 00
    00 00 00 00 00 00 00 00 00
    00 00 00 29 00 25 00 00 00
    35 00 00 00 27 00 00 00 03
    NEW LINE
    000 000 000 000 000 000 000 000 000 000 000 000
    000 000 094 095 000 000 000 000 032 027 000 000
    000 098 000 000 000 084 083 000 000 000 025 000
    000 105 000 107 000 000 000 000 036 000 018 000
    000 000 000 000 000 000 000 000 000 000 000 000
    000 000 118 000 000 000 000 000 000 039 000 000
    000 000 119 000 000 000 000 000 000 042 000 000
    000 000 000 000 000 000 000 000 000 000 000 000
    000 132 000 122 000 000 000 000 003 000 007 000
    000 131 000 000 000 069 052 000 000 000 010 000
    000 000 125 126 000 000 000 000 055 056 000 000
    000 000 000 000 000 000 000 000 000 000 000 000

Would result in the following solved boards

    1-2-3
        |
    8-7 4
    | | |
    9 6-5
    
    67-66 63-62 51-50-49 16-15
     |  |  |  |  |     |  |  |
    68 65-64 61 52-53 48 17 14
     |        |     |  |  |  |
    69 72-73 60 55-54 47 18 13
     |  |  |  |  |     |  |  |
    70-71 74 59 56 45-46 19 12
           |  |  |  |     |  |
    77-76-75 58-57 44 21-20 11
     |              |  |     |
    78-79-80-81 42-43 22  9-10
                 |     |  |
    37-38-39-40-41 24-23  8  1
     |              |     |  |
    36 33-32 29-28 25  6--7  2
     |  |  |  |  |  |  |     |
    35-34 31-30 27-26  5--4--3
    
    101-100  93--92--91--90--89  30--29--28  23--22
      |   |   |               |   |       |   |   |
    102  99  94--95  86--87--88  31--32  27  24  21
      |   |       |   |               |   |   |   |
    103  98--97--96  85--84--83  34--33  26--25  20
      |                       |   |               |
    104-105-106-107-108  81--82  35--36--37  18--19
                      |   |               |   |
    137-136 117-116 109  80--79  46--45  38  17--16
      |   |   |   |   |       |   |   |   |       |
    138 135 118 115 110  77--78  47  44  39--40  15
      |   |   |   |   |   |       |   |       |   |
    139 134 119 114 111  76--75  48  43--42--41  14
      |   |   |   |   |       |   |               |
    140 133 120 113-112  73--74  49   4---5---6  13
      |   |   |           |       |   |       |   |
    141 132 121-122  71--72  51--50   3   8---7  12
      |   |       |   |       |       |   |       |
    142 131 124-123  70--69  52   1---2   9--10--11
      |   |   |           |   |
    143 130 125-126  67--68  53--54--55--56--57--58
      |   |       |   |                           |
    144 129-128-127  66--65--64--63--62--61--60--59


