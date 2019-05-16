echo "** Q1 :"
valeurAttribut='"([^<\"&]*(&[[:alpha:]:_][[:alnum:]:_.-]*;)*)*"'
echo "*avec contact.html: "
egrep "$valeurAttribut" html/contact.html --color=auto -m 10
echo ""
echo "*avec fil.html: "
egrep "$valeurAttribut" html/fil.html --color=auto -m 10
echo ""
echo "** Q2 :"
nomXML='[[:alpha:]:_][[:alnum:]:_.-]*'
refEntite="&$nomXML;"
baliseOuvrante="<$nomXML( $nomXML *= *$valeurAttribut)* *>"
echo "*avec contact.html: "
egrep "$baliseOuvrante" html/contact.html --color=auto -m 10
echo ""
echo "*avec fil.html: "
egrep "$baliseOuvrante" html/fil.html --color=auto -m 10
echo ""
echo "** Q3 :"
plus33="\+33 \(0\) "
numeroTel="(($plus33|[[:digit:]])[[:digit:]])((.[[:digit:]]{2}){4})"
egrep "$numeroTel" html/contact.html html/fil.html --color=auto -o
