#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
  int i;
  int cmt;
  int status;
  int pid;
  int arrive[10];
  /* Lancer les 10 fils */
  for (i = 0; i < 10; i++) {
    switch (fork()) {
      case -1:
        perror("error fork");
        exit(EXIT_FAILURE);
        break;
      case 0:
        for (cmt = 1; cmt <= 100000000; cmt++);
        printf("Je suis le fils %d. J'ai compté jusqu'à 100 millions.\n", getpid());
        fflush(stdout);
        for (cmt = 1; cmt <= 100000000; cmt++);
        exit(EXIT_SUCCESS);
        break;
      default:;
    }
  }
  /* Attendre les 10 fils */
  for (i = 0; i < 10; i++) {
    pid = wait(&status);
    if(WIFEXITED(status) && WEXITSTATUS(status)== EXIT_SUCCESS) {
      arrive[i]=pid;
    }
    else{
      printf("Problème avec un des fils.\n");
    }
  }
  printf("\n");
  printf("Ordre d'arrivée de mes fils:\n");
  for (i = 0; i < 10; i++) {
    printf("%d\n", arrive[i]);
  }
  return 0;

}
