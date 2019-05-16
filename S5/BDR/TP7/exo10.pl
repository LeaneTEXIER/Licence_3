schema10([a,b,c,d,e,f,g,h,i,j]).

fds10([ [[a,b],[c]], [[b,d],[e,f]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).

answer10a(K) :- schema10(R),fds10(F), candkey(R,F,K).
answer10b(R) :- schema10(S), fds10(F), mincover(S,F,R).
answer10c(R3NF) :- schema10(R), fds10(F), threenf(R,F,R3NF).

/*
?- answer10a(K).
K = [a, b, d] .

?- answer10b(R).
R = [[[a, b], [c]], [[b], [f]], [[b, d], [e]], [[d], [i, j]], [[f], [g, h]]] .

?- answer10c(R3NF).
R3NF = [[a, b, c], [b, f], [b, d, e], [d, i, j], [f, g, h], [a, b, d]] .
*/
