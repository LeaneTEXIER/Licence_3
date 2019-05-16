schema2([ename,ssn,bdate,address,dnumber,dname,dmgrssn]).

fds2([ [[ssn],[ename, bdate, address, dnumber]], [[dnumber],[dname,dmgrssn]] ]).

answer2a(XPLUS) :- schema2(R), fds2(F), xplus(R,F,[ssn],XPLUS).
answer2b(XPLUS) :- schema2(R), fds2(F), xplus(R,F,[dnumber],XPLUS).

/*
?- answer2a(R).
R = [address, bdate, dmgrssn, dname, dnumber, ename, ssn].

?- answer2b(R).
R = [dmgrssn, dname, dnumber].
*/
