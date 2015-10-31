/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.Random;

/**
 *
 * @author stefa
 */
public class Board {

    public final static byte ALIVE = 1;
    public final static byte DEAD = 0;

    private final byte[][] boardA;
    private final byte[][] boardB;
    boolean A;
    private final int M, N;

    public Board(int m, int n) {
        M = m;
        N = n;
        A = true;
        boardA = new byte[m][n];
        boardB = new byte[m][n];
    }

    public int getHeight() {
        return M;
    }

    public int getWidth() {
        return N;
    }

    public byte getElement(int i, int j) {
        if (A)
            return boardA[i][j];
        else 
            return boardB[i][j];
    }

    public void setElement(byte value, int i, int j) {
        if(A)
            boardA[i][j] = value;
        else 
            boardB[i][j] = value;
    }

    public void initializeBoard() {
        Random r = new Random();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                byte number = (byte) (Math.abs(r.nextInt()) % 2);
                boardA[i][j] = number;
            }
        }
    }

    public void printBoard() {
        if (A) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(boardA[i][j] + "\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        } else {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(boardB[i][j] + "\t");
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }

    }

    private int modulo(int i, int m) {
        return (i % m + m) % m;
    }

    private int countAliveNeighbours(int i, int j) {
        int result = 0;
        byte[][] board;

        if (A) {
            board = boardA;
        } else {
            board = boardB;
        }
        if (board[modulo(i - 1, M)][modulo(j - 1, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i - 1, M)][modulo(j, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i - 1, M)][modulo(j + 1, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i, M)][modulo(j - 1, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i, M)][modulo(j + 1, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i + 1, M)][modulo(j - 1, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i + 1, M)][modulo(j, N)] == ALIVE) {
            result++;
        }
        if (board[modulo(i + 1, M)][modulo(j + 1, N)] == ALIVE) {
            result++;
        }
        return result;
    }
    
    public synchronized void swapBoards(){
        A = !A;
    }

    public void makeStep(int startRow, int nRows) {
        byte[][] boardStart, boardEnd;
        if (A) {
            boardStart = boardA;
            boardEnd = boardB;
        } else {
            boardStart = boardB;
            boardEnd = boardA;           
        }
        for (int i = startRow; i < nRows; i++) {
            for (int j = 0; j < N; j++) {
                int aliveNeighbours = countAliveNeighbours(i, j);
                if (boardStart[i][j] == ALIVE) {
                    if (aliveNeighbours < 2 || aliveNeighbours > 3) {
                        boardEnd[i][j] = DEAD;
                    } else {
                        boardEnd[i][j] = ALIVE;
                    }
                } else {
                    if (aliveNeighbours == 3) {
                        boardEnd[i][j] = ALIVE;
                    } else {
                        boardEnd[i][j] = DEAD;
                    }
                }
            }
        }
        
    }

}
