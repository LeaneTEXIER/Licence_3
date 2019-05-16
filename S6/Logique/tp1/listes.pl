% Très bon travail 5/5. Faites attention à ne pas compliquer inutilement vos
% définitions.

/*
  Léane Texier
  Lamine Zenati
*/

/* Question 1 */
longueur([],0).
longueur([_|Tail],N):- longueur(Tail,Y), N is 1+Y.

/* Question 2 */
somme([],0).
somme([Head|Tail],N):- somme(Tail,Y), N is Y+Head.

/* Question 3 */
membre(X,[X|_]).
membre(X,[_|Tail]):- membre(X,Tail).

/* Question 4 */
ajoute_en_tete(X,L,[X|L]).

/* Question 5 */
ajoute_en_queue(X,[],[X]).
ajoute_en_queue(X,[Head|Tail],[Head|R]):- ajoute_en_queue(X,Tail,R).

/* Question 6 */
extraire_tete(L,X,R):- ajoute_en_tete(X,R,L).

/* Question 7 */
concatene([],L,L).
concatene([Head|Tail],L,[Head|R]) :- concatene(Tail,L,R).

/* Question 8 */
retourne([],L,L).
retourne([Head|Tail], L1, L2) :- retourne(Tail, [Head|L1], L2).

/* Tris sur les listes */

/* Question 9 */
insert_tri(X,[],[X]).
insert_tri(X,[Head|Tail],[X|[Head|Tail]]) :- X=<Head.
insert_tri(X,[Head|Tail],[Head|Y]) :- X>Head, insert_tri(X,Tail,Y).

/* Question 10 */
tri_insert([],[]).
tri_insert([Head|Tail], R) :- tri_insert(Tail,Y), insert_tri(Head, Y, R).

/* Question 11 */
divise([],[], []).
divise([Head], [Head], []).
divise([Head|[Head2|Tail]], [Head | L1], [Head2| L2]) :- divise(Tail,L1,L2) .

/* Question 12 */
fusion([], L, L).
fusion(L, [], L).
fusion([Head|Tail], [Head2|Tail2], [Head|Y]) :- Head=<Head2, fusion(Tail, [Head2|Tail2], Y).
fusion([Head|Tail], [Head2|Tail2], [Head2|Y]) :- Head>Head2, fusion([Head|Tail], Tail2, Y).

/* Question 13 */
tri_fusion([],[]).
tri_fusion([X],[X]).
tri_fusion(L, R) :- divise(L,L1,L2), tri_fusion(L1,R1), tri_fusion(L2,R2), fusion(R1,R2,R).
% il est préférable décrire
% tri_fusion([A,B|L], R) :- divise([A,B|L],L1,L2), tri_fusion(L1,R1), tri_fusion(L2,R2), fusion(R1,R2,R).
% pour éviter qu'il y ait des calculs qui bouclent pour la liste singleton...

/* Question 14 */
balance(_,[],[],[]).
balance(X, [Head|Tail], L1, [Head|Y]) :- X=<Head, balance(X, Tail, L1, Y).
balance(X, [Head|Tail], [Head|Y], L2) :- X>Head, balance(X, Tail, Y, L2).

/* Question 15 */
tri_rapide([],[]).
tri_rapide([Head|Tail],R):- balance(Head, Tail, L1, L2), tri_rapide(L1, R1), tri_rapide(L2, R2), concatene(R1, [Head|R2], R).

/* Opérations de base sur les ensembles */

/* Question 16 */
est_vide([]).

/* Question 17 */
ajoute_ensemble(X, [X|Tail], [X|Tail]).
ajoute_ensemble(X, [], [X]).
ajoute_ensemble(X, [Head|Tail], [Head|Y]) :- X\=Head, ajoute_ensemble(X, Tail, Y).

/* Question 18 */
sous_ensemble([],_).
sous_ensemble([Head|Tail],L):- ajoute_ensemble(Head,L,L),sous_ensemble(Tail,L).
% n'était-il pas plus simple d'utiliser membre(Head,L) plutôt que ajoute_ensemble ?
/* Question 19 */
union([],L2,L2).
union([Head|Tail],L2,R):- ajoute_ensemble(Head,L2,Y), union(Tail,Y,R).

/* Question 20 */
intersect([],_,[]).
intersect([Head|Tail],L2,R):- not(sous_ensemble([Head], L2)), intersect(Tail, L2, R).
intersect([Head|Tail],L2,[Head|R]):- sous_ensemble([Head],L2), intersect(Tail, L2, R).

/* Question 21 */
diff([], _, []).
diff([Head|Tail],L2,[Head|R]):- not(sous_ensemble([Head], L2)), diff(Tail, L2, R).
diff([Head|Tail],L2,R):- sous_ensemble([Head],L2), diff(Tail, L2, R).
% n'était-il pas plus simple d'utiliser membre(Head,L2) plutôt que sous-ensemble([Head],L2) ?