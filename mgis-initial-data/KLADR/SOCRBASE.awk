@include "convert-includes.awk"
BEGIN { RS = "" ; FS = "\n" }
{
	level = extract_value($1)
	scname = extract_value($2)
	socrname = extract_value($3)
	kod_t_st = extract_value($4)
	print "INSERT INTO kladr_flat (id, level, scname, socrname, kod_t_st) VALUES (nextval('kladr_seq'), '"level"','"scname"',\t'"socrname"',\t'"kod_t_st"')";
}
