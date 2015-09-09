@include "convert-includes.awk"
BEGIN { RS = "" ; FS = "\n" }
{
	code = extract_value($1)
	np = extract_value($2)
	gninmb = extract_value($3)
	name = extract_value($4)
	indx = extract_value($5)
	uno = extract_value($6)
	print "INSERT INTO kladr_flat (id, code, np, gninmb, name, index, uno) VALUES (nextval('kladr_seq'), '" code "','"np"',\t'"gninmb"',\t'"name"',\t'"indx"',\t'"uno"')";
}
