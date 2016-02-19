package edu.spm.stefano.gameoflife;

import java.util.Random;

/**
 * The Board class which represents the World of this Game of Life
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * @author stefano
 */
public class Board {

    public final static byte ALIVE = 1;
    public final static byte DEAD = 0;

    private byte[][] boardFrom;
    private byte[][] boardTo;
    private final int M, N;

    public Board(int m, int n) {
        M = m;
        N = n;
        boardFrom = new byte[m][n];
        boardTo = new byte[m][n];
    }

    /**
     * Returns the number of rows of the Board.
     *
     * @return M
     */
    public int getHeight() {
        return M;
    }

    /**
     * Returns the number of columns of the Board.
     *
     * @return N
     */
    public int getWidth() {
        return N;
    }

    /**
     * Returns the element in position (i,j) in the Board.
     *
     * @param i row identifier
     * @param j column identifier
     * @return
     */
    public byte getElement(int i, int j) {
        return boardFrom[i][j];
    }

    /**
     * Sets the element (i,j) of the Board to a specific value.
     *
     * @param value the value to be set
     * @param i row identifier
     * @param j column identifier
     */
    public void setElement(byte value, int i, int j) {
        boardFrom[i][j] = value;
    }

    /**
     * Initialises the Board to a random configuration.
     */
    public void initializeBoard() {
        Random r = new Random();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                byte number = (byte) (Math.abs(r.nextInt()) % 2);
                boardFrom[i][j] = number;
            }
        }
    }

    /**
     * Initialises the Board to a pseudorandom configuration, dependent on the
     * input seed.
     *
     * @param seed the seed for the pseudorandom generator
     */
    public void initializeBoard(long seed) {
        Random r = new Random(seed);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                byte number = (byte) (Math.abs(r.nextInt()) % 2);
                boardFrom[i][j] = number;
            }
        }
    }

    /**
     * Initialises the Board to the glider configuration.
     */
    public void initializeGlider() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                boardFrom[i][j] = 0;
            }
        }
        boardFrom[0][1] = 1;
        boardFrom[1][2] = 1;
        boardFrom[2][0] = 1;
        boardFrom[2][1] = 1;
        boardFrom[2][2] = 1;
    }

    /**
     * Prints some rows of the board on the textual interface, starting from a
     * given row.
     *
     * @param fromRow row from which to start printing
     * @param nRows number of rows to print
     */
    public void printBoard(int fromRow, int nRows) {
        for (int i = fromRow; i < fromRow + nRows; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(boardFrom[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Prints the whole Board.
     */
    public void printBoard() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(boardFrom[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Optimised modulo operation for this Game of Life.
     *
     * @param i the object of the modulo
     * @param m the base of the modulo
     * @return i%m
     */
    private int modulo(int i, int m) {
        int result;
        if (i == -1) {
            result = m - 1;
        } else if (i == m) {
            result = 0;
        } else {
            result = i;
        }
        return result;
    }

    /**
     * A non-optimised function that counts the alive neighbours of cell (i,j).
     * It makes use of the a modulo function.
     *
     * @param i row identifier
     * @param j column identifier
     * @return the number of alive cells around (i,j)
     */
    private int countAliveNeighbours(int i, int j) {
        int result
                = boardFrom[modulo(i - 1, M)][modulo(j - 1, N)]
                + boardFrom[modulo(i - 1, M)][modulo(j, N)]
                + boardFrom[modulo(i - 1, M)][modulo(j + 1, N)]
                + boardFrom[modulo(i, M)][modulo(j - 1, N)]
                + boardFrom[modulo(i, M)][modulo(j + 1, N)]
                + boardFrom[modulo(i + 1, M)][modulo(j - 1, N)]
                + boardFrom[modulo(i + 1, M)][modulo(j, N)]
                + boardFrom[modulo(i + 1, M)][modulo(j + 1, N)];
        return result;
    }

    /**
     * A optimised function that counts the alive neighbours of a cell without
     * using any modulo operation.
     *
     * @param i row identifier
     * @param j column identifier
     * @return the number of alive cells around (i,j)
     */
    private int optimisedCountAliveNeighbours(int i, int j) {
        int result = 0;
        int prevRow, succRow, prevColumn, succColumn;
        
        if (i == 0){
            prevRow = M - 1;
        } else {
            prevRow = i - 1;
        }
        if (j == 0){
            prevColumn = N - 1;
        } else {
            prevColumn = j - 1;
        }
        if (j == N - 1){
            succColumn = 0;
        } else {
            succColumn = j + 1;
        }
        if (i == M - 1){
            succRow = 0;
        } else {
            succRow = i + 1;
        }

        result = boardFrom[prevRow][prevColumn]
                + boardFrom[prevRow][j]
                + boardFrom[prevRow][succColumn]
                + boardFrom[i][prevColumn]
                + boardFrom[i][succColumn]
                + boardFrom[succRow][prevColumn]
                + boardFrom[succRow][j]
                + boardFrom[succRow][succColumn];
       
        return result;
    }

    /**
     * A function that swaps the read board with the write board, to be called
     * at the end of each iteration.
     */
    public void swapBoards() {
        byte[][] tmp = boardFrom;
        boardFrom = boardTo;
        boardTo = tmp;
    }

    /**
     * A function performing n cycles within the specified interval. Swaps the
     * boards at the end of each cycle.
     *
     * @param n number of cycles
     * @param startRow first row to be updated
     * @param nRows number of rows to be updated starting from startRow
     */
    public void makeSteps(int n, int startRow, int nRows) {
        for (int i = 0; i < n; i++) {
            makeStep(0, nRows);
            swapBoards();
        }

    }

    /**
     * Performs a single cycle within the specified interval. Does not swap the
     * boards.
     *
     * @param startRow first row to be updated
     * @param nRows number of rows to be updated starting from startRow
     */
    public void makeStep(int startRow, int nRows) {
        for (int i = startRow; i < startRow + nRows; i++) {
            for (int j = 0; j < N; j++) {
                int aliveNeighbours = optimisedCountAliveNeighbours(i, j);
                
                if (boardFrom[i][j] == ALIVE){
                    switch(aliveNeighbours){
                        case 2: boardTo[i][j] = ALIVE;
                        break;
                        case 3: boardTo[i][j] = ALIVE;
                        break;
                        default: boardTo[i][j] = DEAD;
                        break;
                    }
                } else {
                    switch (aliveNeighbours){
                        case 3: boardTo[i][j] = ALIVE;
                        break;
                        default: boardTo[i][j] = DEAD;
                        break;
                    }
                }
            }
        }
    }
    
    public Interval[] splitBoard(int NTHREADS){
        int step = M / (NTHREADS);
        int extra = M % NTHREADS;
        int start = 0;
        int chunk = 0;
        Interval[] bounds = new Interval[NTHREADS];

        for (int j = 0; j < NTHREADS; j++) {
            start = start + chunk;
            chunk = step + (extra-- > 0 ? 1:0);            
            bounds[j] = new Interval(start, chunk);
        }
        
        return bounds;
    }
}
