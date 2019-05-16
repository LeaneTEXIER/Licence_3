package postfixees;

%%

%unicode
%line
%column

ENTIER_SIMPLE=[0-9]+
PLUS=[+]|plus
MINUS=[-]|minus
MULT=[*]|mult
QUO=[/]|quo
OPP=opp
ESPACE=" "
FINLIGNE=\n

%%

{ENTIER_SIMPLE}
      { return new Valeur(yytext()); }

{PLUS}
      { return new Plus(yytext()); }

{MINUS}
      { return new Minus(yytext()); }

{MULT}
      { return new Mult(yytext()); }

{QUO}
      { return new Quo(yytext()); }

{OPP}
      { return new Opp(yytext()); }

{ESPACE}|{FINLIGNE}
      {}
