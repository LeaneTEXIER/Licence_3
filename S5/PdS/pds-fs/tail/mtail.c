#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <assert.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>


#define TAILLE_TAMPON 4096

int cmtLignes=0;

int n_linesValue= 10;

void mtail_stupid(char * path, int nlines){
  int tmpLines=0;
  char car;
  int readStatus;
  int fd= open(path,O_RDONLY);
  int totalLines=0;
  assert(fd != -1);
  /* Compte nombre de lignes */
  while((readStatus= read(fd, &car,1))!=0){
    if(car=='\n') totalLines++;
  }
  /* Retour au debut du fichier */
  lseek(fd,0, SEEK_SET);
  while((readStatus= read(fd, &car,1))!=0){
    /* si on est dans les lignes à écrire les écrire */
    if((totalLines- tmpLines)<= nlines) write(STDOUT_FILENO,&car,1);
    /* Augmente nombre de lignes si \n */
    else if(car=='\n') tmpLines++;
  }
}


int main_tail(int fd){
    int i;
    int lus=0;
    int endOfFile=0; /*correspond au début du fichier vu qu'on lit à partir de la fin*/
    char buff[TAILLE_TAMPON];
    int currentOffset= lseek(fd,0,SEEK_CUR);
    if(currentOffset<TAILLE_TAMPON && currentOffset>=0){
      /* si le nombre de char est inférieur à la taille du tampon (début du fichier) */
      lseek(fd,0,SEEK_SET);
      lus=read(fd,buff,currentOffset); /*lecture */
      lseek(fd,0,SEEK_SET);
      endOfFile=1; /* plus rien à lire */
    }else{
      lseek(fd,-TAILLE_TAMPON,SEEK_CUR);
      lus=read(fd,buff,TAILLE_TAMPON); /*lecture*/
      lseek(fd,-TAILLE_TAMPON,SEEK_CUR);
    }
    for (i=lus-1; i>=0; i--) { /*on parcours les char à partir du dernier dernier lu*/
      if(buff[i]=='\n'){ /* si on est sur un saut de ligne*/
        if(cmtLignes==n_linesValue){ /* si  on a atteint le nombre de lignes, on affiche et on s'arr^ete*/
          i++; /* pour ne pas afficher '\n' */
          write(STDOUT_FILENO,buff+i,lus-i); /*on affiche */
          return 0;
        }
        else cmtLignes++; /* sinon on incremente */
      }
    }
    if(!endOfFile) main_tail(fd); /*si on n'est pas au début du fichier (et qu'on n'a pas atteint le nombre de lignes demandés) */
    write(STDOUT_FILENO,buff,lus);
    return 0;
}

int tail_efficace(char * path){
  int fd= open(path,O_RDONLY);
  lseek(fd, 0, SEEK_END);
  return main_tail(fd);
}

int main(int argc, char **argv) {
  int ch;
  while ((ch = getopt(argc, argv, "n:")) != -1) {
     switch (ch) {
     case 'n':
        n_linesValue= atoi(optarg);
        assert(argc==4);
        break;
     default:
        exit(1);
       /*usage(argv [0]); */
     }
  }
  argc -= optind;
  argv += optind;
  tail_efficace(argv[0]);
  return 0;
}
