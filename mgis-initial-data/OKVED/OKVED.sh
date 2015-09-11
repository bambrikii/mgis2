awk ' {print;} NR % 2 == 0 { print ""; }' OKVED.txt > OKVED.tmp
awk -f OKVED.awk OKVED.tmp > OKVED.sql