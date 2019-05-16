schema9([a,b,c,d,e,f,g,h,i,j]).

fds9([ [[a,b],[c]], [[a],[d,e]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).

answer9a(K) :- schema9(R),fds9(F), candkey(R,F,K).
answer9b(R) :- schema9(S), fds9(F), mincover(S,F,R).
answer9c(R3NF) :- schema9(R), fds9(F), threenf(R,F,R3NF).

/*
?- answer9a(K).
K = [a, b] .

?- answer9b(R).
R = [[[a], [d, e]], [[a, b], [c]], [[b], [f]], [[d], [i, j]], [[f], [g, h]]] .

?- answer9c(R3NF).
R3NF = [[a, d, e], [a, b, c], [b, f], [d, i, j], [f, g, h]] .
*/
