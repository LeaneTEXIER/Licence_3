/*
  Léane Texier
  Lamine Zenati
*/

?- [sudoku].
grillehex([[11,2,_,7,_,_,_,15,6,14,_,_,_,3,_,13],
          [_,_,_,9,11,_,2,_,_,_,_,_,15,1,_,6],
       	  [_,10,_,12,_,_,7,_,4,_,8,_,_,_,14,_],
          [_,8,_,3,_,_,1,_,_,_,15,12,10,_,4,2],
          [_,_,14,_,_,_,_,_,1,6,10,_,_,_,3,_],
          [3,_,_,_,_,_,_,_,_,4,_,15,8,_,6,11],
          [_,_,_,13,2,_,_,_,_,11,_,_,7,4,_,_],
          [_,11,8,4,0,_,_,13,7,_,9,3,14,_,_,_],
          [_,_,_,15,3,7,_,4,5,_,_,9,0,13,12,_],
          [_,_,12,8,_,_,10,_,_,_,_,11,4,_,_,_],
          [4,9,_,5,14,_,13,_,_,_,_,_,_,_,_,10],
          [_,13,_,_,_,6,12,1,_,_,_,_,_,7,_,_],
          [9,1,_,14,7,4,_,_,_,15,_,_,3,_,13,_],
          [_,3,_,_,_,9,_,2,_,1,_,_,5,_,0,_],
          [15,_,4,11,_,_,_,_,_,3,_,13,1,_,_,_],
					[5,_,13,_,_,_,15,14,9,_,_,_,2,_,11,4]]).

/* Question 13 */
verifieMaxi([]).
verifieMaxi([H|T]):- H ins 0..15, all_distinct(H), verifieMaxi(T).

decoupeMaxi([],[],[],[],[]).
decoupeMaxi([H1,H2,H3,H4|T1],[G1,G2,G3,G4|T2],[F1,F2,F3,F4|T3],[E1,E2,E3,E4|T4],[[H1,H2,H3,H4,G1,G2,G3,G4,F1,F2,F3,F4,E1,E2,E3,E4]|R]):- decoupeMaxi(T1,T2,T3,T4,R).

carresMaxi([], []).
carresMaxi([H1,H2,H3,H4|T], X) :- decoupeMaxi(H1, H2, H3, H4, R), carresMaxi(T, L), append(R,L,X).

solutionMaxiB(Sudoku):- bonnetaille(Sudoku,16), transp(Sudoku,Colonnes),
                        bonnetaille(Colonnes,16), verifieMaxi(Sudoku), verifieMaxi(Colonnes),
                        carresMaxi(Sudoku,Carres), verifieMaxi(Carres).
