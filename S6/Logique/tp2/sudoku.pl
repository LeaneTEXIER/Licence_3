/*
  Léane Texier
  Lamine Zenati
*/

?- use_module(library(clpfd)).
grille([[_,_,_,_,_,_,_,_,_],
        [_,_,_,_,_,3,_,8,5],
        [_,_,1,_,2,_,_,_,_],
        [_,_,_,5,_,7,_,_,_],
        [_,_,4,_,_,_,1,_,_],
        [_,9,_,_,_,_,_,_,_],
        [5,_,_,_,_,_,_,7,3],
        [_,_,2,_,1,_,_,_,_],
        [_,_,_,_,4,_,_,_,9]]).

/* Question 1 */
printline([]):- writeln('|').
printline(['_'|T]) :- write('| '), printline(T).
printline([H|T]) :- H\='_', write('|'), write(H), printline(T).

/* Question 2 */
print([]).
print([H|T]) :- printline(H), print(T).


/* Question 3 */
bonnelongueur([],0).
bonnelongueur([_|T], X) :- S is X-1, bonnelongueur(T, S).

/* Question 4 */
bonnetaille([],_).
bonnetaille([H|T], X) :- bonnelongueur(H, X), bonnetaille(T, X).


/* Question 5 */
verifie([]).
verifie([H|T]):- all_distinct(H), H ins 1..9, verifie(T).

/* Question 6 */
eclate([],X,X).
eclate([H|T],[],[[H]|R]):- eclate(T,[],R).
eclate([H|T],[H1|T1],[[H|H1]|R]):- eclate(T,T1,R).

/* Question 7 */
transp([],_).
transp([H|T],R):- eclate(H, L, R), transp(T, L).

/* Question 8 */
decoupe([],[],[],[]).
decoupe([H1,H2,H3|T1],[G1,G2,G3|T2],[F1,F2,F3|T3],[[H1,H2,H3,G1,G2,G3,F1,F2,F3]|R]):- decoupe(T1,T2,T3,R).

/* Question 9 */
carres([], []).
carres([H1,H2,H3|T], X) :- decoupe(H1, H2, H3, R), carres(T, L), append(R,L,X).

/* Question 10 */
solution(Sudoku):- bonnetaille(Sudoku,9), transp(Sudoku,Colonnes),
                   bonnetaille(Colonnes,9), verifie(Sudoku), verifie(Colonnes),
                   carres(Sudoku,Carres), verifie(Carres).
