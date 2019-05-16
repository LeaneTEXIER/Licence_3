schema7([courseno,secno,offeringdept,credithours,courselevel,instructorssn,semester,year,dayshours,roomno,noofstudents]).

fds7([ [[courseno],[offeringdept,credithours,courselevel]],
       [[courseno,secno,semester,year],[dayshours,roomno,noofstudents,instructorssn]],
       [[roomno,dayshours,semester,year],[instructorssn,courseno,secno]] ]).

answer7a(K) :- schema7(R),fds7(F), candkey(R,F,K).
answer7b(R3NF) :- schema7(R), fds7(F), threenf(R,F,R3NF).

/*
?- answer7a(K).
K = [semester, year, dayshours, roomno] ;
K = [courseno, secno, semester, year] .

?- answer7b(R3NF).
R3NF = [[courseno, courselevel, credithours, offeringdept], [courseno, secno, semester, year, dayshours, noofstudents, roomno], [roomno, dayshours, semester, year, courseno, instructorssn|...]] .
*/
