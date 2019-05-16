#include "graphe.h"
#include "bellman.h"
#include <stdlib.h>
#include <stdio.h>
#include "graphe2visu.h"


int voisinBleuTrouve(tTabCouleurs tabCouleurs, tGraphe graphe, int x){
  int i, j;
  tNumeroSommet y;
  j = grapheNbSuccesseursSommet(graphe,x);
	for(i=0; i<j; i++){
    y=grapheSuccesseurSommetNumero(graphe,x,i);
		if(tabCouleurs[y]==BLEU){
			return y;
		}
	}
	return -1;
}


void triTopologique(tGraphe graphe, tNomSommet sommet, tNumeroSommet * resultTab, int tabSize, tTabCouleurs tabCouleurs){
  tNumeroSommet numSommet;
  int i, index;
  index=tabSize-1;
  tNumeroSommet x,y;
  tPileSommets pile= pileSommetsAlloue();
  int nbSommets= grapheNbSommets(graphe);
  numSommet= grapheChercheSommetParNom(graphe, sommet);
  for(i=0; i<nbSommets; i++){
    if(i!=numSommet){
      tabCouleurs[i]=BLEU;
    }
  }
  tabCouleurs[numSommet]=VERT;
  pileSommetsEmpile(pile, numSommet);
  while(!pileSommetsEstVide(pile)){
    x = pileSommetsTete(pile);
    y = voisinBleuTrouve(tabCouleurs, graphe, x);
    if (y>-1){
      tabCouleurs[y]=VERT;
      pileSommetsEmpile(pile,y);
    }
    else{
      tabCouleurs[x]=ROUGE;
      resultTab[index--]=x;
      pileSommetsDepile(pile);
    }
  }
  for(i=0;i<tabSize;i++){
    if(tabCouleurs[i]==BLEU){
      resultTab[index--]=i;
    }
  }
}


void bellman(tGraphe g, tNomSommet s ){
  int i,j;
  tNumeroSommet x, numSommet, y;
  int valeurArc;
  int nbPred;
  int nbSommets = grapheNbSommets(g);
  tTabCouleurs tabCouleurs;
  tNumeroSommet * pred=(tNumeroSommet *) malloc (sizeof(tNumeroSommet)* nbSommets);
  int * pi= (int *)malloc(sizeof(int)*nbSommets);
  tNumeroSommet * tab= (tNumeroSommet *) malloc(sizeof(tNumeroSommet)*nbSommets);
  triTopologique(g,s,tab,nbSommets,tabCouleurs);
  pi[tab[0]]=0;
  pred[tab[0]]=-1;
  for(i=1; i<nbSommets;i++){
    x= tab[i];
    nbPred= grapheNbPredecesseursSommet(g, x);
    y=graphePredecesseurSommetNumero(g,x,0);
    pred[x]=y;
    pi[x]=grapheRecupValeurArc(g,y,x)+pi[y];
    for(j=1;j<nbPred; j++){
      y=graphePredecesseurSommetNumero(g,x,j);
      valeurArc= grapheRecupValeurArc(g,y,x) +pi[y];
      if(valeurArc<pi[x]) {
        pred[x]=y;
        pi[x]=valeurArc;
      }
    }
  }
  for(i=0;i<nbSommets;i++){
    printf("I: %d / Pred: %d / pi: %d\n",tab[i],pred[i],pi[i]);
  }
  graphe2visu(g,"grapheCouleur",* tabCouleurs,pred);
  free(tab);
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
  bellman(graphe, argv[2]);
  grapheLibere(graphe);

  return 0;
}
