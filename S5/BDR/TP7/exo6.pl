schema6([a,b,c,d,e]).

fds6([ [[a,b],[c]], [[c,d],[e]], [[d,e],[b]] ]).

answer6(K) :- schema6(R), fds6(F), candkey(R,F,K).

/*
?- answer6([a,b]).
false.

?- answer6([a,b,d]).
true .

=> AB is not a candidate key of this relation.
ABD is a candidate key of this relation.
*/
