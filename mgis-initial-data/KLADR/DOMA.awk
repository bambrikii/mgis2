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
	ocatd = extract_value($8)
	print "INSERT INTO kladr_doma (id, name, korp, socr, code, index, gninmb, uno, ocatd) VALUES (nextval('kladr_seq'),\t'"name"','"korp"',\t'"socr"',\t'"code"',\t'"indx"',\t'"gninmb"',\t'"uno"',\t'"ocatd"');";
}
