#!/bin/bash

wget http://www.vsepro1c.ru/files/download/KLADR_20.08.2015.7z
#sudo apt-get install p7zip-full
7z x -y KLADR_20.08.2015.7z

./convert-one.sh KLADR
./convert-one.sh STREET
./convert-one.sh DOMA
./convert-one.sh FLAT
./convert-one.sh SOCRBASE
./convert-one.sh ALTNAMES

#rm *.7z