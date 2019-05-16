echo "** Q1 :"
egrep 'nez' Cyrano.txt --color=auto
echo ""
echo "** Q2 :"
egrep '\([[:alpha:][:space:]]*\)' Cyrano.txt --color=auto
echo ""
echo "** Q3 :"
egrep '[[:space:]^][[:alpha:]]{4}[[:space:]$]' Cyrano.txt --color=auto
echo ""
echo "** Q4 :"
egrep '[[:upper:]][[:alpha:]]*[[:space:]]:' Cyrano.txt --color=auto
echo ""
echo "** Q4 (avec option -o) :"
egrep '[[:upper:]][[:alpha:]]*[[:space:]]:' Cyrano.txt --color=auto -o
