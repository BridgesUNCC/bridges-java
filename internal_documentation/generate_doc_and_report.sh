#!/bin/sh

(
    cat bridges_doxygen_java.cfg
    echo "PROJECT_NUMBER = `git describe --tags`"
) | doxygen - 2>&1  | tee log

rm report*

grep warning log | sed 's/.*\/\([[:alpha:]_[:digit:]]*\.java\).*/\1/g' | sort | uniq -c > report

for file in $(grep warning log | sed 's/.*\/\([[:alpha:]_[:digit:]]*\.java\).*/\1/g' | sort | uniq);
do
    grep /$file log > report.${file}
done

    
echo
echo
echo
cat report
echo
echo ===========================================
echo See per file report with "cat report.$file"
echo ===========================================

