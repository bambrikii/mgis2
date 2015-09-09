@include "convert-includes.awk"
BEGIN { RS = "" ; FS = "\n" }
{
	name = extract_value($1)
	korp = extract_value($2)
	socr = extract_value($3)
	code = extract_value($4)
	indx = extract_value($5)
	gninmb = extract_value($6)
	uno = extract_value($7)
	okatd = extract_value($8)
	print "INSERT INTO kladr_doma (id, name, korp, socr, code, index, gninmb, uno, okatd) VALUES (nextval('kladr_seq'), '" name "','"korp"',\t'"socr"',\t'"code"',\t'"indx"',\t'"gninmb"',\t'"uno"',\t'"okatd"')";
}
