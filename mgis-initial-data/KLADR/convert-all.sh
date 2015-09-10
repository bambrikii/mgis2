#!/bin/bash

if [ ! -f ./KLADR_20.08.2015.7z ]; then
    wget http://www.vsepro1c.ru/files/download/KLADR_20.08.2015.7z
fi
#sudo apt-get install p7zip-full
#sudo apt-get install dbview

7z x -y KLADR_20.08.2015.7z

./convert-one.sh KLADR
./convert-one.sh STREET
./convert-one.sh DOMA
./convert-one.sh FLAT
./convert-one.sh SOCRBASE
./convert-one.sh ALTNAMES

#rm *.DBF
#rm *.struct
#rm *.struct.2
#rm *.7z