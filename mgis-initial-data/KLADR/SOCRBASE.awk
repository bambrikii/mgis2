@include "convert-includes.awk"
BEGIN { RS = "" ; FS = "\n" }
{
	level = extract_value($1)
	scname = extract_value($2)
	socrname = extract_value($3)
	kod_t_st = extract_value($4)
	print "INSERT INTO kladr_socrbase (id, level, scname, socrname, kod_t_st) VALUES (nextval('kladr_seq'),\t'"level"',\t'"scname"',\t'"socrname"',\t'"kod_t_st"');";
}
