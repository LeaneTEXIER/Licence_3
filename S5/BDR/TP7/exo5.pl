schema5([a,b,c,d,e,f,g,h,i,j]).

fds5([ [[a,b],[c]], [[b,d],[e,f]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).

answer5a(K) :- schema5(R), fds5(F), candkey(R,F,K).
answer5b(R3NF) :- schema5(R), fds5(F), threenf(R,F,R3NF).

/*
?- answer5a(K).
K = [a, b, d] .

?- answer5b(R3NF).
R3NF = [[a, b, c], [b, f], [b, d, e], [d, i, j], [f, g, h], [a, b, d]] .
*/
