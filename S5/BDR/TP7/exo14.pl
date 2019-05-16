schema14([m,y,p,mp,c]).

fds14([ [[m],[mp]], [[m,y],[p]], [[mp],[c]] ]).

d([ [m,y,p],[m,mp,c] ]).

answer14a(K) :- schema14(R), fds14(F), candkey(R,F,K).
answer14b1 :- schema14(R), fds14(F), is3NF(R,F).
answer14b2 :- schema14(R), fds14(F), isBCNF(R,F).
answer14c :- schema14(R), fds14(F), d(D), fpd(R,F,D).

/*
?- answer14a([m]).
false.

?- answer14a([m,y]).
true .

?- answer14a([m,c]).
false.

=> {m} and {m,c} aren't candidate keys. However, {m,y} is a candidate key.

?- answer14b1.
false.

?- answer14b2.
false.

=> This schema isn't a 3NF. It's not also a BCNF.

?- answer14c.
Considering [m]-->[mp]
Xplus=[c,m,mp]
Considering [m,y]-->[p]
Xplus=[p,y,c,m,mp]
Considering [mp]-->[c]
Xplus=[c,mp]
true .

=> The decomposition d is lossless.
*/
