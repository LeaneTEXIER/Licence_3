schema11([a,b,c,d,e,f,g,h,i,j]).

fds11F([ [[a,b],[c]], [[a],[d,e]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).
fds11G([ [[a,b],[c]], [[b,d],[e,f]], [[b],[f]], [[f],[g,h]], [[d],[i,j]] ]).

answer11a(D) :- schema11(R), fds11F(F), bcnf(R,F,D).
answer11b(D) :- schema11(R), fds11G(F), bcnf(R,F,D).

/*
?- answer11a(D).
Scheme to decompose = [a,b,c,d,e,f,g,h,i,j] Offending FD: [a]-->[d,e,i,j]
Scheme to decompose = [a,b,c,f,g,h] Offending FD: [a,c,f]-->[g,h]
Scheme to decompose = [a,b,c,f] Offending FD: [b]-->[f]
Final Result is:
[a,b,c]
[a,d,e,i,j]
[a,c,f,g,h]
[b,f]

D = [[a, b, c], [a, d, e, i, j], [a, c, f, g, h], [b, f]] ;
Scheme to decompose = [a,b,c,f] Offending FD: [b,c]-->[f]
Final Result is:
[a,b,c]
[a,d,e,i,j]
[a,c,f,g,h]
[b,c,f]

D = [[a, b, c], [a, d, e, i, j], [a, c, f, g, h], [b, c, f]] ;
Scheme to decompose = [a,b,c,f,g,h] Offending FD: [a,c,f,g]-->[h]
Scheme to decompose = [a,b,c,f,g] Offending FD: [a,c,f]-->[g]
Scheme to decompose = [a,b,c,f] Offending FD: [b]-->[f]
Final Result is:
[a,b,c]
[a,d,e,i,j]
[a,c,f,g,h]
[a,c,f,g]
[b,f]

D = [[a, b, c], [a, d, e, i, j], [a, c, f, g, h], [a, c, f, g], [b, f]] ;
Scheme to decompose = [a,b,c,f] Offending FD: [b,c]-->[f]
Final Result is:
[a,b,c]
[a,d,e,i,j]
[a,c,f,g,h]
[a,c,f,g]
[b,c,f]

D = [[a, b, c], [a, d, e, i, j], [a, c, f, g, h], [a, c, f, g], [b, c, f]] .


?- answer11b(D).
Scheme to decompose = [a,b,c,d,e,f,g,h,i,j] Offending FD: [a,b]-->[c,f,g,h]
Scheme to decompose = [a,b,d,e,i,j] Offending FD: [a,d]-->[i,j]
Scheme to decompose = [a,b,d,e] Offending FD: [b,d]-->[e]
Final Result is:
[a,b,d]
[a,b,c,f,g,h]
[a,d,i,j]
[b,d,e]

D = [[a, b, d], [a, b, c, f, g, h], [a, d, i, j], [b, d, e]] ;
Scheme to decompose = [a,b,d,e,i,j] Offending FD: [a,d,e]-->[i,j]
Scheme to decompose = [a,b,d,e] Offending FD: [b,d]-->[e]
Final Result is:
[a,b,d]
[a,b,c,f,g,h]
[a,d,e,i,j]
[b,d,e]

D = [[a, b, d], [a, b, c, f, g, h], [a, d, e, i, j], [b, d, e]] .
*/
