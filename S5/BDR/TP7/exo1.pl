schema1([a,c,d,e,h]).

fdsF([ [[a],[c]], [[a,c],[d]], [[e],[a,d]], [[e],[h]] ]).
fdsG([ [[a],[c,d]], [[e],[a,h]] ]).

answer1 :- schema1(R), fdsF(FF), fdsG(FG), equiv(R,FF,FG).

/*
?- answer1.
true .
*/
