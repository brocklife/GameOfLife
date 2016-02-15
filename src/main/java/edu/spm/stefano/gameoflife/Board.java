package edu.spm.stefano.gameoflife;

import java.util.Random;

/**
 * The Board class which represents the World of this Game of Life
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
     * @return M
     */
    public int getHeight() {
        return M;
    }
    
    /**
     * Returns the number of columns of the Board.
     * @return N
     */
    public int getWidth() {
        return N;
    }
    
    /**
     * Returns the element in position (i,j) in the Board.
     * @param i row identifier
     * @param j column identifier
     * @return 
     */
    public byte getElement(int i, int j) {
        return boardFrom[i][j];
    }
    
    /**
     * Sets the element (i,j) of the Board to a specific value.
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
     * Initialises the Board to a pseudorandom configuration, dependent
     * on the input seed.
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
     * Prints some rows of the board on the textual interface, starting from a given row.
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
     * @param i the object of the modulo
     * @param m the base of the modulo
     * @return i%m
     */
    private int modulo(int i, int m) {
        int result;
        if (i == -1)
            result = m - 1;
        else if ( i == m)
            result = 0;
        else result = i;
        return result;
    }

    public int countAliveNeighbours(int i, int j) {
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

    public int optimisedCountAliveNeighbours(int i, int j) {
        int result = 0;
        if (i > 0 && i < M - 1 && j > 0 && j < N - 1) {
            result = boardFrom[i - 1][j - 1]
                    + boardFrom[i - 1][j]
                    + boardFrom[i - 1][j + 1]
                    + boardFrom[i][j - 1]
                    + boardFrom[i][j + 1]
                    + boardFrom[i + 1][j - 1]
                    + boardFrom[i + 1][j]
                    + boardFrom[i + 1][j + 1];
        } else if (i == 0 && j == 0) { //(0,0)
            result = boardFrom[M - 1][N - 1]
                    + boardFrom[M - 1][0]
                    + boardFrom[M - 1][1]
                    + boardFrom[0][N - 1]
                    + boardFrom[0][1]
                    + boardFrom[1][N - 1]
                    + boardFrom[1][0]
                    + boardFrom[1][1];
        } else if (i == 0 && j == N - 1) { //(0,N-1)
            result =  boardFrom[M - 1][N - 2]
                    + boardFrom[M - 1][N - 1]
                    + boardFrom[M - 1][0]
                    + boardFrom[0][N - 2]
                    + boardFrom[0][0]
                    + boardFrom[1][N - 2]
                    + boardFrom[1][N - 1]
                    + boardFrom[1][0];
        } else if (i == M - 1 && j == N - 1) { //(M-1,N-1)
            result =  boardFrom[M - 2][N - 2]
                    + boardFrom[M - 2][N - 1]
                    + boardFrom[M - 2][0]
                    + boardFrom[M - 1][N - 2]
                    + boardFrom[M - 1][0]
                    + boardFrom[0][N - 2]
                    + boardFrom[0][N - 1]
                    + boardFrom[0][0];
        } else if (i == M - 1 && j == 0) { //(M-1,0)
            result =  boardFrom[M - 2][N - 1]
                    + boardFrom[M - 2][0]
                    + boardFrom[M - 2][1]
                    + boardFrom[M - 1][N - 1]
                    + boardFrom[M - 1][1]
                    + boardFrom[0][N - 1]
                    + boardFrom[0][0]
                    + boardFrom[0][1];
        } else if (j == 0) {
            result =  boardFrom[i - 1][N - 1]
                    + boardFrom[i - 1][j]
                    + boardFrom[i - 1][j + 1]
                    + boardFrom[i][N - 1]
                    + boardFrom[i][j + 1]
                    + boardFrom[i + 1][N - 1]
                    + boardFrom[i + 1][j]
                    + boardFrom[i + 1][j + 1];
        } else if (j == N - 1) {
            result =  boardFrom[i - 1][j - 1]
                    + boardFrom[i - 1][j]
                    + boardFrom[i - 1][0]
                    + boardFrom[i][j - 1]
                    + boardFrom[i][0]
                    + boardFrom[i + 1][j - 1]
                    + boardFrom[i + 1][j]
                    + boardFrom[i + 1][0];
        } else if (i == 0) {
            result = boardFrom[M-1][j - 1]
                    + boardFrom[M-1][j]
                    + boardFrom[M-1][j + 1]
                    + boardFrom[i][j - 1]
                    + boardFrom[i][j + 1]
                    + boardFrom[i + 1][j - 1]
                    + boardFrom[i + 1][j]
                    + boardFrom[i + 1][j + 1];
        } else if (i == M - 1) {
            result = boardFrom[i - 1][j - 1]
                    + boardFrom[i - 1][j]
                    + boardFrom[i - 1][j + 1]
                    + boardFrom[i][j - 1]
                    + boardFrom[i][j + 1]
                    + boardFrom[0][j - 1]
                    + boardFrom[0][j]
                    + boardFrom[0][j + 1];
        }
        return result;
    }

    public void swapBoards() {
        byte[][] tmp = boardFrom;
        boardFrom = boardTo;
        boardTo = tmp;
    }

    public void makeSteps(int n, int startRow, int nRows) {
        for (int i = 0; i < n; i++) {
            makeStep(0, nRows);
            swapBoards();
        }
    }

    public void makeStep(int startRow, int nRows) {
        for (int i = startRow; i < startRow + nRows; i++) {
            for (int j = 0; j < N; j++) {
                int aliveNeighbours = optimisedCountAliveNeighbours(i, j);
                if (boardFrom[i][j] == ALIVE) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        boardTo[i][j] = DEAD;
                    } else {
                        boardTo[i][j] = ALIVE;
                    }
                } else if (aliveNeighbours == 3) {
                    boardTo[i][j] = ALIVE;
                } else {
                    boardTo[i][j] = DEAD;
                }
            }
        }

    }

}
