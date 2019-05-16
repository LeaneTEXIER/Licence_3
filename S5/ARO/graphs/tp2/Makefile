$OPTION= -Wall -g -std=c99

All: parcoursLargeur

parcoursLargeur: parcoursLargeur.o graphe.o graphe2visuCouleurs.o
	gcc $(OPTION) -o  parcoursLargeur graphe.o parcoursLargeur.o graphe2visuCouleurs.o


graphe2visuCouleurs.o: graphe.h graphe2visuCouleurs.c parcoursLargeur.h
	gcc $(OPTION) -c graphe2visuCouleurs.c

parcoursLargeur.o: graphe.h parcoursLargeur.c parcoursLargeur.h graphe2visuCouleurs.h
	gcc $(OPTION) -c parcoursLargeur.c

graphe.o: graphe.c graphe.h
	gcc $(OPTION) -c graphe.c

clean:
	rm -f *.o  parcoursLargeur
