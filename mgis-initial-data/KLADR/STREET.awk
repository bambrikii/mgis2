@include "convert-includes.awk"
BEGIN { RS = "" ; FS = "\n" }
{
	name = extract_value($1)
	socr = extract_value($2)
	code = extract_value($3)
	indx = extract_value($4)
	gninmb = extract_value($5)
	uno = extract_value($6)
	ocatd = extract_value($7)
	print "INSERT INTO kladr_street (id, name, socr, code, index, gninmb, uno, ocatd) VALUES (nextval('kladr_seq'),\t'" name "',\t'"socr"',\t'"code"',\t'"indx"',\t'"gninmb"',\t'"uno"',\t'"ocatd"');";
}
