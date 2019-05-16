$OPTION= -Wall -g -std=c99

All: parcoursProfondeur

parcoursProfondeur: parcoursProfondeur.o graphe.o graphe2visuCouleurs.o
	gcc $(OPTION) -o  parcoursProfondeur graphe.o parcoursProfondeur.o graphe2visuCouleurs.o

graphe2visuCouleurs.o: graphe.h graphe2visuCouleurs.c parcoursProfondeur.h
	gcc $(OPTION) -c graphe2visuCouleurs.c

parcoursProfondeur.o: graphe.h parcoursProfondeur.c parcoursProfondeur.h graphe2visuCouleurs.h
	gcc $(OPTION) -c parcoursProfondeur.c

graphe.o: graphe.c graphe.h
	gcc $(OPTION) -c graphe.c

clean:
	rm -f *.o  parcoursProfondeur grapheCouleur grapheCouleur.dot
