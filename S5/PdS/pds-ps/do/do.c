#include "makeargv.h"
#include <unistd.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int a_flag=0;
int o_flag=0;
int c_flag=0;
int k_flag=0;



int do_aux(int argc, char *argv[]){
  int status;
  int i;
  int result;
  pid_t * list_pid;
  pid_t id;

  /**si -a et -o en meme temps erreur */
  if(o_flag && a_flag){
    perror("flag error");
    exit(EXIT_FAILURE);
  }

  /* si -k, initialisation de liste pour garder les pid des commandes lancées */
  if(k_flag){
    list_pid= (pid_t *) malloc(sizeof(pid_t) * argc);
  }

  /* Pour chaque argument/commande */
  for (i = 0; i < argc; i++) {
    /* traiter argv[i] */
    char **cmdargv;
    /* création du argv de l'argument i */
    cmdargv = makeargv(argv[i]);
    assert(cmdargv != NULL);
    switch (id =fork()) {
      case -1:
        perror("fork error");
        exit(EXIT_FAILURE);
        break;
      case 0:
        /* Execution de la commande dans le fils */
        execvp(cmdargv[0],cmdargv);
        exit(EXIT_FAILURE);
        break;
      default:
        /* Pere */
        /* si -k, garder le pid de la commande lancée dans la liste destinée*/
        if(k_flag) *(list_pid+i)=id;
        break;
    }
    freeargv(cmdargv);
  }

  /* si -o, disjonction */
  if(o_flag){
    result = EXIT_FAILURE;
    /* tant qu'il y a des fils à attendre */
    while(wait(&status)!=-1){
      /* si c'est un succes, EXIT_SUCCESS */
      if(WIFEXITED(status) && WEXITSTATUS(status)==EXIT_SUCCESS){
        /* si -c, une fois le résultat acquis, il n’est pas utile de laisser poursuivre
        les exécutions des commandes dont le résultat ne sera pas exploité
        Donc exit */
        if(c_flag){
          /* si -k, tuer les commandes non terminées */
          if(k_flag){
            for(i=0;i<argc;i++) kill(list_pid[i],SIGKILL);
          }
          exit(EXIT_SUCCESS);
        }
        result = EXIT_SUCCESS;
      }
    }
  }
  /* ie si -a, conjonction */
  else{
    result = EXIT_SUCCESS;
    /* tant qu'il y a des fils à attendre */
    while(wait(&status)!=-1){
      /* si c'est un echec, EXIT_FAILURE */
      if(!WIFEXITED(status) || (WIFEXITED(status) && WEXITSTATUS(status)!=EXIT_SUCCESS)){
        /* si -c, exit */
        if(c_flag) {
          if(k_flag){
            /* si -k, tuer les commandes non terminées */
            for(i=0;i<argc;i++) kill(list_pid[i],SIGKILL);
          }
          exit(EXIT_FAILURE);
        }
         result = EXIT_FAILURE;
      }
    }
  }
  exit(result);
}


int main(int argc, char *argv[]) {
  int ch;
   while ((ch = getopt(argc, argv, "aock")) != -1) {
      switch (ch) {
      case 'a':
         a_flag=1;
         break;
      case 'o':
         o_flag = 1;
         break;
      case 'c':
         c_flag = 1;
         break;
      case 'k':
        k_flag = 1;
        break;
      }
   }
   argc -= optind;
   argv += optind;
   return do_aux(argc, argv);
}
