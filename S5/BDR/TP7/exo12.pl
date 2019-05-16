schema12F([a,b,c,d,e]).

fds12F([ [[a,b],[c]], [[c,d],[e]], [[d,e],[b]] ]).

answer12Fa(K) :- schema12F(R), fds12F(F), candkey(R,F,K).
answer12Fb(R3NF) :- schema12F(R), fds12F(F), threenf(R,F,R3NF).
answer12Fc(D) :- schema12F(R), fds12F(F), bcnf(R,F,D).

/*
?- answer12Fa(K).
K = [a, d, e] ;
K = [a, c, d] ;
K = [a, b, d] .

?- answer12Fb(R3NF).
R3NF = [[a, b, c], [c, d, e], [d, e, b], [a, d, e]] ;
R3NF = [[a, b, c], [c, d, e], [d, e, b], [a, c, d]] ;
R3NF = [[a, b, c], [c, d, e], [d, e, b], [a, b, d]] ;
R3NF = [[a, b, c], [c, d, e], [d, e, b], [a, d, e]] ;
R3NF = [[a, b, c], [c, d, e], [d, e, b], [a, c, d]] .

?- answer12Fc(D).
Scheme to decompose = [a,b,c,d,e] Offending FD: [a,b]-->[c]
Scheme to decompose = [a,b,d,e] Offending FD: [d,e]-->[b]
Final Result is:
[a,d,e]
[a,b,c]
[d,e,b]

D = [[a, d, e], [a, b, c], [d, e, b]] ;
Scheme to decompose = [a,b,c,d,e] Offending FD: [a,b,e]-->[c]
Scheme to decompose = [a,b,d,e] Offending FD: [d,e]-->[b]
Final Result is:
[a,d,e]
[a,b,e,c]
[d,e,b]

D = [[a, d, e], [a, b, e, c], [d, e, b]] ;
Scheme to decompose = [a,b,c,d,e] Offending FD: [b,c,d]-->[e]
Scheme to decompose = [a,b,c,d] Offending FD: [a,b]-->[c]
Final Result is:
[a,b,d]
[b,c,d,e]
[a,b,c]

D = [[a, b, d], [b, c, d, e], [a, b, c]] ;
Scheme to decompose = [a,b,c,d] Offending FD: [c,d]-->[b]
Final Result is:
[a,c,d]
[b,c,d,e]
[c,d,b]

D = [[a, c, d], [b, c, d, e], [c, d, b]] ;
Scheme to decompose = [a,b,c,d,e] Offending FD: [c,d]-->[b,e]
Final Result is:
[a,c,d]
[c,d,b,e]

D = [[a, c, d], [c, d, b, e]] ;
Scheme to decompose = [a,b,c,d,e] Offending FD: [c,d,e]-->[b]
Scheme to decompose = [a,c,d,e] Offending FD: [c,d]-->[e]
Final Result is:
[a,c,d]
[c,d,e,b]
[c,d,e]

D = [[a, c, d], [c, d, e, b], [c, d, e]] ;
Scheme to decompose = [a,b,c,d,e] Offending FD: [d,e]-->[b]
Scheme to decompose = [a,c,d,e] Offending FD: [c,d]-->[e]
Final Result is:
[a,c,d]
[d,e,b]
[c,d,e]

D = [[a, c, d], [d, e, b], [c, d, e]] .
*/

schema12R([courseno,secno,offeringdept,credithours,courselevel,instructorssn,semester,year,dayshours,roomno,noofstudents]).

fds12R([ [[courseno],[offeringdept,credithours,courselevel]],
         [[courseno,secno,semester,year],[dayshours,roomno,noofstudents,instructorssn]],
         [[roomno,dayshours,semester,year],[instructorssn,courseno,secno]] ]).

answer12Ra(K) :- schema12R(R),fds12R(F), candkey(R,F,K).
answer12Rb(R3NF) :- schema12R(R), fds12R(F), threenf(R,F,R3NF).
answer12Rc(D) :- schema12R(R),fds12R(F), bcnf(R,F,D).

/*
?- answer12Ra(K).
K = [semester, year, dayshours, roomno] ;
K = [courseno, secno, semester, year] .

?- answer12Rb(R3NF).
R3NF = [[courseno, courselevel, credithours, offeringdept], [courseno, secno, semester, year, dayshours, noofstudents, roomno], [roomno, dayshours, semester, year, courseno, instructorssn|...]] .

?- answer12Rc(D).
Scheme to decompose = [courseno,secno,offeringdept,credithours,courselevel,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno]-->[courselevel,credithours,offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,courselevel,credithours,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, courselevel, credithours, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,credithours,courselevel,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno]-->[courselevel,credithours,offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,courselevel,credithours,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, courselevel, credithours, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,credithours,courselevel,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,offeringdept]-->[courselevel,credithours]
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, semester, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester,dayshours]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,dayshours,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, semester, dayshours, offeringdept]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester,dayshours,roomno]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,dayshours,roomno,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, semester, dayshours, roomno|...]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester,dayshours,roomno,noofstudents]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,dayshours,roomno,noofstudents,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, semester, dayshours, roomno|...]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester,dayshours,noofstudents]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,dayshours,noofstudents,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, semester, dayshours, noofstudents|...]] ;
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester,roomno]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,roomno,offeringdept]

D = [[courseno, secno, instructorssn, semester, year, dayshours, roomno, noofstudents], [courseno, secno, offeringdept, courselevel, credithours], [courseno, secno, instructorssn, semester, roomno, offeringdept]] .
Scheme to decompose = [courseno,secno,offeringdept,instructorssn,semester,year,dayshours,roomno,noofstudents] Offending FD: [courseno,secno,instructorssn,semester,roomno,noofstudents]-->[offeringdept]
Final Result is:
[courseno,secno,instructorssn,semester,year,dayshours,roomno,noofstudents]
[courseno,secno,offeringdept,courselevel,credithours]
[courseno,secno,instructorssn,semester,roomno,noofstudents,offeringdept]

*/
