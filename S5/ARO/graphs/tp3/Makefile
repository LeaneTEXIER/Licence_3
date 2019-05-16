$OPTION= -Wall -g -std=c99

All: plusCourteChaine

plusCourteChaine: plusCourteChaine.o graphe.o graphe2visu.o
	gcc $(OPTION) -o  plusCourteChaine graphe.o plusCourteChaine.o graphe2visu.o

plusCourteChaine.o: plusCourteChaine.c graphe.h graphe2visu.h plusCourteChaine.h
	gcc $(OPTION) -c plusCourteChaine.c

graphe.o: graphe.c graphe.h
	gcc $(OPTION) -c graphe.c

graphe2visu.o: graphe.h graphe2visu.c
	gcc $(OPTION) -c graphe2visu.c

clean:
	rm -f *.o  plusCourteChaine grapheCouleur grapheCouleur.dot
