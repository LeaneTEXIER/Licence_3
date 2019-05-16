/*
  Léane Texier
  Lamine Zenati
*/

?- [sudoku].
/* Question 11 */
grilleCross([[_,5,_,_,_,_,2,3,6],
            [3,_,_,9,_,7,_,4,_],
            [_,_,_,5,2,_,_,_,9],
            [_,4,8,6,_,_,_,_,5],
            [_,_,3,_,1,_,8,_,_],
            [6,_,_,_,_,5,3,9,_],
            [4,_,_,_,7,6,_,_,_],
            [_,3,_,4,_,8,_,_,7],
            [8,7,6,_,_,_,_,5,_]]).

diagonaleG([],_,_,[]).
diagonaleG([[H|_]|T], 0, Y, [H|R]):- S is Y+1, diagonaleG(T, S, S, R).
diagonaleG([[_|T]|T2], X, Y, R):- S is X-1, diagonaleG([T|T2], S, Y, R).

reverseAll([],[]).
reverseAll([H|T], [R|T2]):- reverse(H,R), reverseAll(T,T2).

diagoG(H, R):- diagonaleG(H, 0, 0, R).
diagoD(H, R):- reverseAll(H,R2), diagoG(R2,R).

verifie_diag(Sudoku) :- diagoG(Sudoku, Dg), all_distinct(Dg),
                        diagoD(Sudoku, Dd), all_distinct(Dd).

solutionCross(Sudoku):- bonnetaille(Sudoku,9), transp(Sudoku,Colonnes),
                        bonnetaille(Colonnes,9), verifie(Sudoku), verifie(Colonnes),
                        carres(Sudoku,Carres), verifie(Carres),
                        verifie_diag(Sudoku).
