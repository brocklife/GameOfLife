#!/bin/bash

let TOT=0
let TIMES=20
PROCESSORS='16'
let seed=481183

echo "*** Launching the Muskel2 program $TIMES times and averaging the execution times"

for (( k = 1; k <= PROCESSORS; k=k+1 )); do 
	let TOT=0
	for (( i = 0; i < TIMES; i=i+1 )); do
		echo $i
		OUT=`java -jar ./target/GameOfLife-1.0-jar-with-dependencies.jar -f mu2 -N $k -s $seed`
		let TOT=$TOT+$OUT
	done
	let TOT=$TOT/$TIMES
	echo "Multithreaded Muskel2 ($k threads): $TOT" >> results.txt
done
