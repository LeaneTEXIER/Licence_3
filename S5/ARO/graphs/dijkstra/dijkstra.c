#include "graphe.h"
#include "dijkstra.h"
#include <stdlib.h>
#include <stdio.h>
#include "graphe2visu.h"


int sommetVertTrouve(tTabCouleurs tab, tGraphe graphe){
  int i;
  int nbSommets= grapheNbSommets(graphe);
  for(i=0; i<nbSommets; i++){
    if (tab[i]==VERT)
      return 1;
  }
  return 0;
}


int rechercheSommetVertPiMinimal(tGraphe graphe, tTabCouleurs tab, int * pi){
  int i, piMin;
  tNumeroSommet x;
  int nbSommets= grapheNbSommets(graphe);
  int find = 0;
  for (i=0; i<nbSommets && !find; i++){
    if (tab[i]==VERT){
      x = i;
      piMin = pi[x];
      find = 1;
    }
  }
  for(i=0;i<nbSommets;i++){
    if(tab[i]==VERT && pi[i]<piMin){
      piMin = pi[i];
      x = i;
    }
  }
  return x;
}

void dijkstra(tGraphe g, tNomSommet s ){
  int i;
  tNumeroSommet x, numSommet, y;
  int nbSuccesseurs;
  tTabCouleurs tabCouleurs;
  int nbSommets = grapheNbSommets(g);
  tNumeroSommet * pred=(tNumeroSommet *) malloc (sizeof(tNumeroSommet)* nbSommets);
  int * pi= (int *)malloc(sizeof(int)*nbSommets);
  numSommet= grapheChercheSommetParNom(g, s);
  for(i=0; i<nbSommets; i++){
    if(i!=numSommet) tabCouleurs[i]=BLEU;
  }
  tabCouleurs[numSommet]=VERT;
  pi[numSommet]=0;
  pred[numSommet]=-1;
  while(sommetVertTrouve(tabCouleurs,g)){
    x = rechercheSommetVertPiMinimal(g, tabCouleurs, pi);
    tabCouleurs[x]=ROUGE;
    nbSuccesseurs= grapheNbSuccesseursSommet(g,x);
    for(i=0;i<nbSuccesseurs;i++){
      y = grapheSuccesseurSommetNumero(g,x,i);
      if(tabCouleurs[y]==BLEU || (tabCouleurs[y]==VERT && pi[y]>(pi[x]+grapheRecupValeurArc(g,x,y)))){
        tabCouleurs[y]=VERT;
        pi[y]=pi[x]+grapheRecupValeurArc(g,x,y);
        pred[y]=x;
      }
    }
  }
  for(i=0; i<nbSommets; i++){
    printf("I: %d / Pred: %d / pi: %d\n",i,pred[i],pi[i]);
  }
  graphe2visu(g,"grapheCouleur",tabCouleurs,pred);
  free(pred);
  free(pi);
}



int main(int argc, char *argv[]) {
  tGraphe graphe;
  if (argc<3) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }

  graphe = grapheAlloue();
  grapheChargeFichier(graphe,  argv[1]);
  dijkstra(graphe, argv[2]);
  grapheLibere(graphe);

  return 0;
}
