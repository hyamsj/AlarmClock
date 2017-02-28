#!/bin/sh
OUTPUT="../script/version.tex"

echo "Last compiled: ">$OUTPUT
date >> $OUTPUT

echo "\n">>$OUTPUT

echo "Git HEAD Version: ">> $OUTPUT
git rev-list --count --first-parent HEAD >>$OUTPUT


