$OPTION= -Wall -g -std=c99

All: bellman

bellman: bellman.o graphe.o graphe2visu.o
	gcc $(OPTION) -o  bellman graphe.o bellman.o graphe2visu.o

bellman.o: bellman.c graphe.h graphe2visu.h bellman.h
	gcc $(OPTION) -c bellman.c

graphe.o: graphe.c graphe.h
	gcc $(OPTION) -c graphe.c

graphe2visu.o: graphe.h graphe2visu.c
	gcc $(OPTION) -c graphe2visu.c

clean:
	rm -f *.o  bellman grapheCouleur grapheCouleur.dot
