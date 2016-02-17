#!/bin/bash

let TOT=0
let TIMES=20
let seed=481183
PROCESSORS='16'

echo "*** Launching the sequential program $TIMES times and averaging the execution times"

for (( i = 0; i < TIMES; i=i+1 )); do
	echo $i
	OUT=`java -jar ./target/GameOfLife-1.0-jar-with-dependencies.jar -s $seed`
	let TOT=$TOT+$OUT
done

let TOT=$TOT/$TIMES
echo "sequential" >> results.txt
echo $TOT >> results.txt