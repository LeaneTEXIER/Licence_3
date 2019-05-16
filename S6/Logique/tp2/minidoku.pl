/*
  Léane Texier
  Lamine Zenati
*/

?- [sudoku].
grillemini([[2,_,_,3],
          [_,_,2,_],
          [_,4,1,_],
          [1,_,_,_]]).

/* Question 12 */
verifieMini([]).
verifieMini([H|T]):- all_distinct(H), H ins 1..4, verifieMini(T).

decoupeMini([],[],[]).
decoupeMini([H1,H2|T1],[G1,G2|T2],[[H1,H2,G1,G2]|R]):- decoupeMini(T1,T2,R).

carresMini([], []).
carresMini([H1,H2|T], X) :- decoupeMini(H1, H2, R), carresMini(T, L), append(R,L,X).

solutionMini(Sudoku):- bonnetaille(Sudoku,4), transp(Sudoku,Colonnes),
                       bonnetaille(Colonnes,4), verifieMini(Sudoku), verifieMini(Colonnes),
                       carresMini(Sudoku,Carres), verifieMini(Carres).
