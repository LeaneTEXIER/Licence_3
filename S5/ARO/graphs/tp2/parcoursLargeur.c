#include "sys/wait.h"
#include "graphe.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "parcoursLargeur.h"
#include "graphe2visuCouleurs.h"




void parcoursLargeur(tGraphe graphe, tNomSommet sommet){
  tNumeroSommet numSommet;
  int i,j;
  tNumeroSommet x,y;
  tFileSommets file= fileSommetsAlloue();
  tTabCouleurs tabCouleurs;
  int nbSommets= grapheNbSommets(graphe);
  numSommet= grapheChercheSommetParNom(graphe, sommet);
  for(i=0; i<nbSommets; i++){
    if(i!=numSommet) tabCouleurs[i]=BLEU;
    graphe2visuCouleurs(graphe,"grapheCouleur",tabCouleurs);
    getchar();
  }

  tabCouleurs[numSommet]=VERT;

  graphe2visuCouleurs(graphe,"grapheCouleur",tabCouleurs);

  getchar();
  fileSommetsEnfile(file, numSommet);

  while(!fileSommetsEstVide(file)){
    x=fileSommetsDefile(file);
    j=grapheNbVoisinsSommet(graphe,x);
    for(i=0;i<j;i++){
      y=grapheVoisinSommetNumero(graphe,x,i);
      if(tabCouleurs[y]==BLEU){
        tabCouleurs[y]=VERT;
        graphe2visuCouleurs(graphe,"grapheCouleur",tabCouleurs);
        getchar();
        fileSommetsEnfile(file,y);
      }
    }
    tabCouleurs[x]=ROUGE;
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

  parcoursLargeur(graphe, argv[2]);

  grapheLibere(graphe);
  return 0;
}
