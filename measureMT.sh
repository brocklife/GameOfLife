#!/bin/bash

let TOT=0
let TIMES=20
PROCESSORS='20'
let seed=481183

echo "*** Launching the multithreaded program $TIMES times and averaging the execution times"

for (( k = 1; k <= PROCESSORS; k=k+1 )); do 
	let TOT=0
	for (( i = 0; i < TIMES; i=i+1 )); do
        echo $i
		OUT=`java -jar ./target/GameOfLife-1.0-jar-with-dependencies.jar -f mt -N $k -s $seed`
        echo $OUT >> mt_${k}.txt
		let TOT=$TOT+$OUT 
	done
	let TOT=$TOT/$k
	echo "Multithreaded Java ($k threads)" >> results.txt
    echo $TOT >> results.txt
done
