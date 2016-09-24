#!/bin/bash

function get_kladr_arch() {
    echo `ls -lat . | grep -E KLADR_[0-9]{2}\.[0-9]{2}\.[0-9]{4}.7z | head -n 1 | awk '{print \$9}'`
}
function clean_kladr_dl_page() {
    rm -rf ./kladr_skachat_besplatno.html*
}

function check_kladr() {
    if [ ! -f "./`get_kladr_arch`" ]; then
	echo "KLADR not found, downloading ..."
	clean_kladr_dl_page
	wget http://www.vsepro1s.ru/download/kladr_skachat_besplatno.html
	wget `cat ./kladr_skachat_besplatno.html | perl -n -e'/<strong><a.*href="([^"]+)"[^>]+>\d{2}\.\d{2}\.\d{4}<\/a><\/strong>/ && print "http://www.vsepro1s.ru".$1'`
	clean_kladr_dl_page
    fi
}
