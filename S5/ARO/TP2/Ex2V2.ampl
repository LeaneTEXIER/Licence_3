set TYPE;
param budget >= 0;
param prix {TYPE} >=0;
param nbrPersToucheesParType {TYPE} >=0;
param mini {TYPE} >=0;
var nbrMinutes {t in TYPE} >= mini[t] ;
maximize personnesTouchees:
  sum {t in TYPE} nbrPersToucheesParType[t] * nbrMinutes[t];
subject to budgetLimite:
  sum {t in TYPE} (prix[t] * nbrMinutes[t]) <= budget;

data;
set TYPE := minutesTV pagesMagazines;
param budget := 1000000;
param :            prix        nbrPersToucheesParType     mini:=
minutesTV         20000       1800000                    10
pagesMagazines    10000       1000000                    0;
