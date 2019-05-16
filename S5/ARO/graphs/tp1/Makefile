$OPTION= -Wall -g -std=c99

All: testAnalyseur testAnalyseur2 graphe2visu


graphe2visu: graphe.o graphe2visu.o
	gcc $(OPTION) -o  graphe2visu graphe.o graphe2visu.o

testAnalyseur: graphe.o testAnalyseur.o
	gcc $(OPTION) -o  testAnalyseur graphe.o testAnalyseur.o

testAnalyseur2: graphe.o testAnalyseur2.o
	gcc $(OPTION) -o  testAnalyseur2 graphe.o testAnalyseur2.o

graphe2visu.o: graphe.h graphe2visu.c
	gcc $(OPTION) -c graphe2visu.c


graphe.o: graphe.c graphe.h
	gcc $(OPTION) -c graphe.c

testAnalyseur.o: testAnalyseur.c graphe.h
	gcc $(OPTION) -c testAnalyseur.c

testAnalyseur2.o: testAnalyseur2.c graphe.h
	gcc $(OPTION) -c testAnalyseur2.c

clean:
	rm -f *.o testAnalyseur2 testAnalyseur graphe2visu
