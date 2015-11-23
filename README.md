# GameOfLife
A Java implementation of Game of Life, both sequential and parallel.

The program sequential program was tested on my i7 laptop and so was the multithreaded version. In addition to that,
the parallel program has been run on a 8 cores Xeon machine. These are the obtained timing for computing 1000 steps
over a 1000x1000 board:

1 core :                70 s

2 cores (4 contexts):   37 s

8 cores (16 contexts):  9 s

![alt tag](https://github.com/teto1992/GameOfLife/blob/master/plot.PNG)

