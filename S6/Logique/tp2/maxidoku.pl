/*
  Léane Texier
  Lamine Zenati
*/

?- [sudoku].
grillehex([[b,2,_,7,_,_,_,f,6,e,_,_,_,3,_,d],
          [_,_,_,9,b,_,2,_,_,_,_,_,f,1,_,6],
       	  [_,a,_,c,_,_,7,_,4,_,8,_,_,_,e,_],
          [_,8,_,3,_,_,1,_,_,_,f,c,a,_,4,2],
          [_,_,e,_,_,_,_,_,1,6,a,_,_,_,3,_],
          [3,_,_,_,_,_,_,_,_,4,_,f,8,_,6,b],
          [_,_,_,d,2,_,_,_,_,b,_,_,7,4,_,_],
          [_,b,8,4,0,_,_,d,7,_,9,3,e,_,_,_],
          [_,_,_,f,3,7,_,4,5,_,_,9,0,d,c,_],
          [_,_,c,8,_,_,a,_,_,_,_,b,4,_,_,_],
          [4,9,_,5,e,_,d,_,_,_,_,_,_,_,_,a],
          [_,d,_,_,_,6,c,1,_,_,_,_,_,7,_,_],
          [9,1,_,e,7,4,_,_,_,f,_,_,3,_,d,_],
          [_,3,_,_,_,9,_,2,_,1,_,_,5,_,0,_],
          [f,_,4,b,_,_,_,_,_,3,_,d,1,_,_,_],
					[5,_,d,_,_,_,f,e,9,_,_,_,2,_,b,4]]).
          
/* Question 13 */
verif([]).
verif([H|T]):- member(H,[a,b,c,d,e,f,0,1,2,3,4,5,6,7,8,9]), verif(T).

allDistinct([]).
allDistinct([H|T]):- not(member(H,T)), allDistinct(T).


verifieMaxi([]).
verifieMaxi([H|T]):- allDistinct(H), verif(H), verifieMaxi(T).

decoupeMaxi([],[],[],[],[]).
decoupeMaxi([H1,H2,H3,H4|T1],[G1,G2,G3,G4|T2],[F1,F2,F3,F4|T3],[E1,E2,E3,E4|T4],[[H1,H2,H3,H4,G1,G2,G3,G4,F1,F2,F3,F4,E1,E2,E3,E4]|R]):- decoupeMaxi(T1,T2,T3,T4,R).

carresMaxi([], []).
carresMaxi([H1,H2,H3,H4|T], X) :- decoupeMaxi(H1, H2, H3, H4, R), carresMaxi(T, L), append(R,L,X).

solutionMaxi(Sudoku):- bonnetaille(Sudoku,16), transp(Sudoku,Colonnes),
                       bonnetaille(Colonnes,16), verifieMaxi(Sudoku), verifieMaxi(Colonnes),
                       carresMaxi(Sudoku,Carres), verifieMaxi(Carres).
