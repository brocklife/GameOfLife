#!/bin/bash

./measureAll.sh "seq" 481183 500 18
./measureAll.sh "mt" 481183 500 18
./measureAll.sh "sk" 481183 500 18
./measureAll.sh "mu2" 481183 500 18

./measureAll.sh "seq" 481183 1000 18
./measureAll.sh "mt" 481183 1000 18
./measureAll.sh "sk" 481183 1000 18
./measureAll.sh "mu2" 481183 1000 18

./measureAll.sh "seq" 481183 2000 18
./measureAll.sh "mt" 481183 2000 18
./measureAll.sh "sk" 481183 2000 18
./measureAll.sh "mu2" 481183 2000 18

./measureAll.sh "seq" 481183 4000 18
./measureAll.sh "mt" 481183 4000 18
./measureAll.sh "sk" 481183 4000 18
./measureAll.sh "mu2" 481183 4000 18

./measureAll.sh "seq" 481183 8000 18
./measureAll.sh "mt" 481183 8000 18
./measureAll.sh "sk" 481183 8000 18
./measureAll.sh "mu2" 481183 8000 18

./measureAll.sh "seq" 481183 10000 18
./measureAll.sh "mt" 481183 10000 18
./measureAll.sh "sk" 481183 10000 18
./measureAll.sh "mu2" 481183 10000 18
