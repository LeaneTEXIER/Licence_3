#include "sys/wait.h"
#include "graphe.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "parcoursProfondeur.h"
#include "graphe2visuCouleurs.h"


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


void parcoursProfondeur(tGraphe graphe, tNomSommet sommet){
  tNumeroSommet numSommet;
  int i;
  tNumeroSommet x,y;
  tPileSommets pile= pileSommetsAlloue();
  tTabCouleurs tabCouleurs;
  int nbSommets= grapheNbSommets(graphe);
  numSommet= grapheChercheSommetParNom(graphe, sommet);
  for(i=0; i<nbSommets; i++){
    if(i!=numSommet){
      tabCouleurs[i]=BLEU;
    }
  }
  tabCouleurs[numSommet]=VERT;
  graphe2visuCouleurs(graphe,"grapheCouleur",tabCouleurs);
  getchar();
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
      pileSommetsDepile(pile);
    }
    graphe2visuCouleurs(graphe,"grapheCouleur",tabCouleurs);
    getchar();
  }
}


int main(int argc, char  *argv[]) {
  tGraphe graphe;
  if (argc<3) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }

  graphe = grapheAlloue();
  grapheChargeFichier(graphe,  argv[1]);
  parcoursProfondeur(graphe, argv[2]);
  grapheLibere(graphe);

  return 0;
}
