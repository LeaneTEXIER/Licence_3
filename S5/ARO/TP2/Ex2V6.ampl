set TYPE;
param budget >= 0;
param prix {TYPE} >=0;
param nbrPersToucheesParType {TYPE} >=0;
param mini {TYPE} >=0;
param personnesSemaines {TYPE} >=0;
param persSemainesDispo >=0;
var nbrMinutes {t in TYPE} >= mini[t] ;
maximize personnesTouchees:
  sum {t in TYPE} nbrPersToucheesParType[t] * nbrMinutes[t];
subject to budgetLimite:
  sum {t in TYPE} (prix[t] * nbrMinutes[t]) <= budget;
subject to persSemaineLimite:
  sum {t in TYPE} (personnesSemaines[t] * nbrMinutes[t]) <= persSemainesDispo;

data;
set TYPE := minutesTV pagesMagazines radio;
param budget := 1000000;
param persSemainesDispo := 100;
param :            prix        nbrPersToucheesParType    mini     personnesSemaines:=
minutesTV         20000       1800000                    10       1
pagesMagazines    10000       1000000                    0        3
radio             2000        250000                     120      1;
