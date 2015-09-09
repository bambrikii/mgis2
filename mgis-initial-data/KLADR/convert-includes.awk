function extract_value(str) {
	split(str, arr, ":")
	result = arr[2]
	gsub(/^ /, "", result)
	gsub(/ $/, "", result)
	return result
}
