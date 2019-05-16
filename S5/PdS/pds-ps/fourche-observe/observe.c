#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>


#ifndef N
#define N 3
#endif

int main(){
  int i;
  pid_t forkStatus=1;
  for(i=0;i<N;i++){
    if(forkStatus!=0){
      forkStatus=fork();
      if(forkStatus==-1) {
        printf("fork error\n"); exit(1);
      }
    }
  }
  if(forkStatus==0){
    printf("processus vivant : %d\n",getpid());
    sleep(10);
  }
  if (forkStatus!=0) {
    system("ps -f -a");
    getchar();
    for(i=0;i<N;i++){
      printf("processus terminé: %d\n",wait(NULL) );
    }
  }
  return 0;
}
