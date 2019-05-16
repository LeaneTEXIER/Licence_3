#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "graphe.h"


int main(int argc, char *argv[]) {
  tGraphe graphe;
  int i;
  int nbSommet;
  tNomSommet nom;
  int maxVoisins=0;
  if (argc<2) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }

  graphe= grapheAlloue();
  grapheChargeFichier(graphe,  argv[1]);

  nbSommet= grapheNbSommets(graphe);

  printf("Sommets sans voisins: ");
  for(i=0;i<nbSommet;i++){
    if(grapheNbVoisinsSommet(graphe,i)==0){
      grapheRecupNomSommet(graphe,i,nom);
      printf("%s ", nom);
    }
  }
  printf("\n");

  for (i = 0; i < nbSommet; i++) {
    if(grapheNbVoisinsSommet(graphe,i)>maxVoisins){
      maxVoisins= grapheNbVoisinsSommet(graphe,i);
    }
  }

  printf("Voisins qui ont le plus de sommets: ");
  for (i = 0; i < nbSommet; i++) {
    if(grapheNbVoisinsSommet(graphe,i)==maxVoisins){
      grapheRecupNomSommet(graphe,i,nom);
      printf("%s ", nom);
    }
  }
  printf("\n");



  return 0;
}
