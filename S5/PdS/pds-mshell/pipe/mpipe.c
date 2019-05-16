#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>


/** tests sur les pipes **/

void pipe2(int argc, char const *argv[]){
  int fd[2];
  pipe(fd);

  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      close(fd[0]);
      dup2(fd[1],STDOUT_FILENO);
      close(fd[1]);
      execlp(argv[1], argv[1],NULL);
      exit(1);
    default:
      break;
  }

  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      close(fd[1]);
      dup2(fd[0],STDIN_FILENO);
      close(fd[0]);
      execlp(argv[2],argv[2],NULL);
      exit(1);
    default:
      break;
  }
  close(fd[0]);
  close(fd[1]);
  wait(NULL);
  wait(NULL);
}



void pipe3(int argc, char const *argv[]){
  int fd[2];
  pipe(fd);

  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      close(fd[0]);
      dup2(fd[1],STDOUT_FILENO);
      close(fd[1]);
      execlp(argv[1], argv[1],NULL);
      exit(1);
    default:
      break;
  }
  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:

      dup2(fd[0],STDIN_FILENO);
      close(fd[0]);
      dup2(fd[1],STDOUT_FILENO);
      close(fd[1]);
      execlp(argv[2],argv[2],NULL);
      exit(1);
    default:
      break;
  }
  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      close(fd[1]);
      dup2(fd[0],STDIN_FILENO);
      close(fd[0]);
      execlp(argv[3],argv[3],NULL);
      exit(1);
    default:
      break;
  }
  close(fd[0]);
  close(fd[1]);
  wait(NULL);
  wait(NULL);
  wait(NULL);
}



int main(int argc, char const *argv[]) {

  pipe2(argc, argv);

  return 0;
}
