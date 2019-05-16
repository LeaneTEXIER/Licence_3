echo "#Limitation du nombre de lignes affichées (10 lignes)"
echo "** Q1 :"
egrep "^[^,]+,[[:digit:]]+(BIS|TER),.*" bano-59009.csv --color=auto -m 10
echo ""
echo "** Q2 :"
egrep "^[^,]+,[^,]+,Ruelle .*" bano-59009.csv --color=auto -m 10
echo ""
echo "** Q3 :"
egrep "^[^,]+,[^,]+,[[:upper:] ]+,.*" bano-59009.csv --color=auto -m 10
