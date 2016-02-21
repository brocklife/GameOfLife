#!/bin/bash

SEED=481183
PROCESSORS=16
TIMES=10 #30 would allow to apply the Central Limit Theorem
ITS=500

DIM=250

./measureAll.sh "seq" $SEED $DIM 1 $TIMES $ITS
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES $ITS

DIM=500

./measureAll.sh "seq" $SEED $DIM 1 $TIMES $ITS
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES $ITS

DIM=1000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES $ITS
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES $ITS

DIM=2000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES $ITS
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES $ITS

DIM=4000

./measureAll.sh "seq" $SEED $DIM 1 $TIMES $ITS 
./measureAll.sh "mt" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "sk" $SEED $DIM $PROCESSORS $TIMES $ITS
./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES $ITS







