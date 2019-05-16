schema4([a,b,c,d,e,f,g,h,i,j]).

fds4([ [[a,b],[c]], [[a],[d,e]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).

answer4a(K) :- schema4(R), fds4(F), candkey(R,F,K).
answer4b(R3NF) :- schema4(R), fds4(F), threenf(R,F,R3NF).

/*
?- answer4a(K).
K = [a, b] .

?- answer4b(R3NF).
R3NF = [[a, d, e], [a, b, c], [b, f], [d, i, j], [f, g, h]] .
*/
