#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

typedef int (*func_t) (char *);

int testfunc(char * string){
  if(strcmp(string, "true")==0){
    return EXIT_SUCCESS;
  }
  else {
    return EXIT_FAILURE;
  }
}



int multif (func_t f[], char *args[], int n){
  pid_t pid;
  int tmp;
  int i;
  int result=0;
  for (i = 0; i < n; i++) {
    pid= fork();
    switch (pid) {
      case -1:
        perror("error fork");
        exit(EXIT_FAILURE);
        break;
      case 0:
        exit(f[i](args[i]));
        break;
      default:;
    }
  }
  for (i = 0; i < n; i++) {
    /* Attendre tous les fils */
    wait(&tmp);
    /* si un echec, alors res = 1 */
    if(WIFEXITED(tmp) && WEXITSTATUS(tmp)== EXIT_FAILURE) {
      result = 1;
    }
  }
  return result;
}


int main(int argc, char **argv) {
  int i;
  int res;
  func_t * fonctions= (func_t *)malloc(sizeof(func_t) * (argc -1));
  for (i = 0; i < argc-1; i++) {
    fonctions[i]=testfunc;
  }
  res = multif(fonctions, argv+1, argc-1);
  printf("%i\n",res);
  return res;
}
