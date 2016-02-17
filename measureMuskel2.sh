#!/bin/bash

let TOT=0
let TIMES=30
PROCESSORS='20'
let seed=481183

echo "*** Launching the Muskel2 program $TIMES times and averaging the execution times"

for (( k = 1; k <= PROCESSORS; k=k+1 )); do 
	let TOT=0
	for (( i = 0; i < TIMES; i=i+1 )); do
		echo $i
		OUT=`java -jar ./target/GameOfLife-1.0-jar-with-dependencies.jar -o -f mu2 -N $k -s $seed`
        echo $OUT >> mu2_${k}.txt
		let TOT=$TOT+$OUT
	done
	let TOT=$TOT/$TIMES
	echo "Multithreaded Muskel2 ($k threads):" >> results.txt
    echo $TOT >> results.txt
done
