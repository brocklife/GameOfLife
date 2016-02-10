# GameOfLife
A Java implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) sequential, multithreaded and built using the [Skandium](https://github.com/mleyton/Skandium) framework for parallel skeletons.

A working version of the [Skandium](https://github.com/mleyton/Skandium) library can be downloaded from here [Skandium.jar](http://stefanoforti.altervista.org/Skandium).

The sequential program was tested on my i7 laptop and so were the multithreaded and the Skandium versions. In addition to that,
the parallel program has been run on a 8 cores Xeon machine. These are the obtained timings for computing 1000 steps
over a 1000x1000 board:

![alt tag](https://github.com/teto1992/GameOfLife/blob/master/plot.PNG)

1 core :                70 s

2 cores (4 contexts):   37 s

8 cores (16 contexts):  9 s

