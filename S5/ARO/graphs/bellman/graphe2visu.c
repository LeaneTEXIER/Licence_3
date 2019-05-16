#include "sys/wait.h"
#include "graphe.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "graphe2visu.h"

void graphe2visu(tGraphe graphe, char *outfile, tNumeroSommet depart, tNumeroSommet pred[MAX_SOMMETS]) {
  FILE *fic;
  char * separateur;
  char commande[80];
  char dotfile[80]; /* le fichier dot pour cr ́eer le ps */
  int ret;
  int nbArc;
  int i;
  int nbSommets;
  char * couleur;
  tArc arc;
  tNomSommet nom1;
  tNomSommet nom2;
  /* on va cr ́eer un fichier pour graphviz, dans le fichier "outfile".dot */
  strcpy(dotfile, outfile);
  strcat(dotfile, ".dot");
  fic = fopen(dotfile, "w");
  if (fic==NULL)
    halt ("Ouverture du fichier %s en  ́ecriture impossible\n", dotfile);

  if(grapheEstOriente(graphe)){
    separateur="->";
    fprintf(fic, "digraph {\n");
  }else{
    separateur="--";
    fprintf(fic, "graph {\n");
  }

  nbSommets=grapheNbSommets(graphe);
  nbArc= grapheNbArcs(graphe);

  for(i=0;i<nbSommets;i++){
    grapheRecupNomSommet(graphe,i,nom1);
    if(i==depart) {
      fprintf(fic, "%s [color=blue];\n",nom1);
    }
    else{
      fprintf(fic, "%s [color=black];\n",nom1);
    }
  }

  for(i=0; i<nbArc; i++){
    arc=grapheRecupArcNumero(graphe,i);
    grapheRecupNomSommet(graphe,arc.orig,nom1);
    grapheRecupNomSommet(graphe,arc.dest,nom2);
    if(pred[arc.dest]!=arc.orig){
      fprintf(fic, "%s %s %s [color=black];\n",nom1,separateur, nom2);
    }else{
      fprintf(fic, "%s %s %s [color=blue];\n",nom1,separateur, nom2);
    }
  }
  fprintf(fic, "}");
  fclose(fic);
  sprintf(commande, "dot -Tps %s -o %s", dotfile, outfile);
  ret = system(commande);
  if (WEXITSTATUS(ret))
    halt("La commande suivante a  ́echouée\n%s\n", commande);
}
