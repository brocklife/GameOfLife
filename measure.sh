#!/bin/bash

SEED=481183
PROCESSORS=25
TIMES=$5 #30 allows to apply the Central Limit Theorem

DIM=500

./measureAll.sh "seq" $SEED $DIM 1 $TIMES
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES

DIM=1000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES

DIM=2000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES

DIM=5000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES

DIM=10000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES


