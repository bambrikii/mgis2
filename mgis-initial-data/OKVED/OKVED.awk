BEGIN { FS="\n" ; RS="" }
{
	section=""

	code=$1
	gsub(/^ +/, "", code);
	gsub(/ +$/, "", code);

	name=$2
	gsub(/^ +/, "", name);
	gsub(/ +$/, "", name);

	print "INSERT INTO nc_okved (id, section, code, name) VALUES (nextval('nc_okved_seq'),	'"section"',	'"code"',	'"name"');";
}
