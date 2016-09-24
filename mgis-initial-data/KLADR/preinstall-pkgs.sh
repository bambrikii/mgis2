#!/bin/bash

SEVENZIP_PKG="p7zip-full"
DBVIEW_PKG="dbview"

if [ "0" == "`dpkg -l | grep $SEVENZIP_PKG | wc -l`" ]; then
    echo "Installing 7zip, need sudo access..."
    sudo apt-get install $SEVENZIP_PKG || true
fi

if [ "0" == "`dpkg -l | grep $DBVIEW_PKG | wc -l`" ]; then
    echo "Installing dbview, need sudo access..."
    sudo apt-get install $DBVIEW_PKG || true
fi
