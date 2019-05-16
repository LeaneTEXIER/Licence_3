#include "sys/wait.h"
#include "graphe.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "parcoursLargeur.h"

/* N ́ecessaire pour la macro WEXITSTATUS */
void graphe2visuCouleurs(tGraphe graphe, char *outfile, tTabCouleurs tabCouleurs) {
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
    switch (tabCouleurs[i]) {
      case 0:
        couleur= "red";
        break;
      case 1:
        couleur= "blue";
        break;
      case 2:
        couleur= "green";
        break;
      default:
        couleur= "black";
    }
    fprintf(fic, "%s [color=%s];\n",nom1,couleur);
  }

  for(i=0; i<nbArc; i++){
    arc=grapheRecupArcNumero(graphe,i);
    grapheRecupNomSommet(graphe,arc.orig,nom1);
    grapheRecupNomSommet(graphe,arc.dest,nom2);
    fprintf(fic, "%s %s %s;\n",nom1,separateur, nom2);
  }
  fprintf(fic, "}");

  /*
  on parcourt le graphe pour en tirer les informations
  n ́ecessaires pour graphviz.
  Pour  ́ecrire dans le fichier, on utilise fprintf (qui s’utilise
  comme printf mais on mettant en plus fic comme premier param`etre).
  Ex :
  fprintf(fic, "graph {\n");
  ou
  fprintf(fic, "  %s -> %s\n", origine, destination);
  */
  fclose(fic);
  sprintf(commande, "dot -Tps %s -o %s", dotfile, outfile);
  ret = system(commande);
  if (WEXITSTATUS(ret))
    halt("La commande suivante a  ́echouée\n%s\n", commande);
}



/*int main(int argc, char *argv[]) {
  tGraphe graphe;
  tTabCouleurs tabCouleurs;

  if (argc<3) {
    halt("Usage : %s FichierGraphe\n", argv[0]);
  }


  graphe = grapheAlloue();

  grapheChargeFichier(graphe,  argv[1]);

  graphe2visuCouleurs(graphe, argv[2],tabCouleurs);

  grapheLibere(graphe);

  return 0;
}*/
