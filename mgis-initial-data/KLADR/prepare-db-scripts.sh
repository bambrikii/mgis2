#!/bin/bash

source ./preinstall-pkgs.sh
source ./dl-kladr.sh

check_kladr
KLADR_ARCH=`get_kladr_arch`
echo "KLADR Archive: $KLADR_ARCH"

7z x -y "./$KLADR_ARCH"

./convert-one.sh KLADR
./convert-one.sh STREET
./convert-one.sh DOMA
./convert-one.sh FLAT
./convert-one.sh SOCRBASE
./convert-one.sh ALTNAMES
