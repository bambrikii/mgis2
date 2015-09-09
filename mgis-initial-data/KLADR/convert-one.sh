#!/bin/bash

FILE_NAME=$1
echo $FILE_NAME
dbview $FILE_NAME.DBF > $FILE_NAME.struct
iconv -f cp866 $FILE_NAME.struct > $FILE_NAME.struct.2
awk -f $FILE_NAME.awk $FILE_NAME.struct.2 > $FILE_NAME.sql

rm $FILE_NAME.DBF
rm $FILE_NAME.struct
rm $FILE_NAME.struct.2