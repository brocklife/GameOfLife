#!/bin/bash

SEED=481183
PROCESSORS=15
TIMES=5 #30 would allow to apply the Central Limit Theorem
ITS=500

DIM=4000

./measureAll.sh "mu2" $SEED $DIM $PROCESSORS $TIMES $ITS