@include "convert-includes.awk"
BEGIN { RS = "" ; FS = "\n" }
{
	source = extract_value($1)
	target = extract_value($2)
	value = extract_value($3)
	print "INSERT INTO kladr_altnames (id, source, target, value) VALUES (nextval('kladr_seq'),\t'"source"',\t'"target"',\t'"value"')";
}
