$OPTION= -Wall -g -std=c99

All: dijkstra

dijkstra: dijkstra.o graphe.o graphe2visu.o
	gcc $(OPTION) -o  dijkstra graphe.o dijkstra.o graphe2visu.o

dijkstra.o: dijkstra.c graphe.h graphe2visu.h dijkstra.h
	gcc $(OPTION) -c dijkstra.c

graphe.o: graphe.c graphe.h
	gcc $(OPTION) -c graphe.c

graphe2visu.o: graphe.h graphe2visu.c
	gcc $(OPTION) -c graphe2visu.c

clean:
	rm -f *.o  dijkstra grapheCouleur grapheCouleur.dot
