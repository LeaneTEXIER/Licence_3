schema3([ename,ssn,bdate,address,dnumber,dname,dmgrssn]).

fds3([ [[ssn],[ename, bdate, address, dnumber]], [[dnumber],[dname,dmgrssn]] ]).

answer3(R) :- schema3(S), fds3(F), mincover(S,F,R).

/*
?- answer3(R).
R = [[[dnumber], [dmgrssn, dname]], [[ssn], [address, bdate, dnumber, ename]]] .
*/
