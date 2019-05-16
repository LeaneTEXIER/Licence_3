#include "graphe.h"
#include <stdlib.h>
#include "graphe2visu.h"
#include <stdio.h>
#include "plusCourteChaine.h"


void plusCourteChaine(tGraphe graphe, tNomSommet sommet){
  tNumeroSommet numSommet;
  int i,j;
  tNumeroSommet x,y;
  tFileSommets file= fileSommetsAlloue();
  tTabCouleurs tabCouleurs;
  int nbSommets= grapheNbSommets(graphe);
  tNumeroSommet * pred=(tNumeroSommet *) malloc (sizeof(tNumeroSommet)* nbSommets);
  int * pi= (int *)malloc(sizeof(int)*nbSommets);
  numSommet= grapheChercheSommetParNom(graphe, sommet);
  for(i=0; i<nbSommets; i++){
    if(i!=numSommet){
      tabCouleurs[i]=BLEU;
    }
  }
  while (!fileSommetsEstVide(file)) {
    fileSommetsDefile(file);
  }
  pi[numSommet]= 0;
  pred[numSommet] = -1;
  tabCouleurs[numSommet]=VERT;
  fileSommetsEnfile(file, numSommet);
  while(!fileSommetsEstVide(file)){
    x=fileSommetsDefile(file);
    j=grapheNbVoisinsSommet(graphe,x);
    for(i=0;i<j;i++){
      y=grapheVoisinSommetNumero(graphe,x,i);
      if(tabCouleurs[y]==BLEU){
        tabCouleurs[y]=VERT;
        fileSommetsEnfile(file,y);
        pi[y]=pi[x]+1;
        pred[y]=x;
      }
    }
    tabCouleurs[x]=ROUGE;
  }
  fileSommetsLibere(file);
  graphe2visu(graphe,"grapheCouleur",numSommet,pred);
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

   plusCourteChaine(graphe, argv[2]);

   grapheLibere(graphe);
   return 0;
 }
