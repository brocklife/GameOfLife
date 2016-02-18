#!/bin/bash

./measureAll.sh "seq" 481183 500 1
./measureAll.sh "mt" 481183 500 20
./measureAll.sh "sk" 481183 500 20
./measureAll.sh "mu2" 481183 500 20

./measureAll.sh "seq" 481183 1000 1
./measureAll.sh "mt" 481183 1000 20
./measureAll.sh "sk" 481183 1000 20
./measureAll.sh "mu2" 481183 1000 20

./measureAll.sh "seq" 481183 2000 1
./measureAll.sh "mt" 481183 2000 20
./measureAll.sh "sk" 481183 2000 20
./measureAll.sh "mu2" 481183 2000 20

./measureAll.sh "seq" 481183 4000 1
./measureAll.sh "mt" 481183 4000 20
./measureAll.sh "sk" 481183 4000 20
./measureAll.sh "mu2" 481183 4000 20

./measureAll.sh "seq" 481183 8000 1
./measureAll.sh "mt" 481183 8000 20
./measureAll.sh "sk" 481183 8000 20
./measureAll.sh "mu2" 481183 8000 20

./measureAll.sh "seq" 481183 10000 1
./measureAll.sh "mt" 481183 10000 20
./measureAll.sh "sk" 481183 10000 20
./measureAll.sh "mu2" 481183 10000 20
