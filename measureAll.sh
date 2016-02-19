#!/bin/bash

IMPLEM=$1
SEED=$2
DIM=$3
PROCESSORS=$4
TIMES=$5 #30 allows to apply the Central Limit Theorem
ITS=$6

let TOT=0

echo "*** Launching the $IMPLEM program $TIMES times ($ITS cycles) and averaging the execution times.\
Matrix dimension: $DIM. Seed: $SEED. Max Threads: $PROCESSORS."

echo "$IMPLEM" >> results_${DIM}.txt

for (( k = 1; k <= PROCESSORS; k=k+1 )); do 
	let TOT=0
	for (( i = 0; i < TIMES; i=i+1 )); do
        echo $i
        OUT=`java -jar ./target/GameOfLife-1.0-jar-with-dependencies.jar -m $DIM -n $DIM -f $IMPLEM -N $k -s $SEED -t $ITS`
        #echo $OUT >> ${IMPLEM}_${k}_${DIM}.txt
        let TOT=$TOT+$OUT 
	done
	let TOT=$TOT/$TIMES
    echo $TOT >> results_${DIM}.txt
done