/* mshell - a job manager */
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "pipe.h"
#include "jobs.h"
#include "cmd.h"
#include <string.h>

void f_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg){
  pid_t pgid;
  char name[200];
  int i,j;
  if(nbcmd==2){ /* pipe entre 2 cmd*/
    int fd[2];
    pipe(fd);
    switch (pgid =fork()) {
      case -1:
        perror("Fork:");
        exit(1);
      case 0:
        setpgid(0,0);
        close(fd[0]);
        dup2(fd[1],STDOUT_FILENO);
        close(fd[1]);
        execvp(cmds[0][0], cmds[0]);
        exit(1);
      default:
        break;
    }
    switch (fork()) {
      case -1:
        perror("Fork:");
        exit(1);
      case 0:
        setpgid(0,pgid);
        close(fd[1]);
        dup2(fd[0],STDIN_FILENO);
        close(fd[0]);
        execvp(cmds[1][0], cmds[1]);
        exit(1);
      default:
        break;
    }
    close(fd[0]);
    close(fd[1]);
}else if(nbcmd>=3){ /* pipe entre N cmd*/
  int pipes[MAXCMDS][2];
  for(i=0;i<nbcmd-1;i++){ /* initialisation des pipes*/
    pipe(pipes[i]);
  }
  switch (pgid =fork()) { /* premiere commande*/
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,0);
      for(i=1;i<nbcmd-1;i++){ /* on ferme les pipes inutiles ici*/
        close(pipes[i][0]);
        close(pipes[i][1]);
      }
      close(pipes[0][0]);
      dup2(pipes[0][1],STDOUT_FILENO);
      close(pipes[0][1]);
      execvp(cmds[0][0], cmds[0]);
      exit(1);
    default:
      break;
  }
  for(j=1;j<nbcmd-1;j++){ /* commandes 1 => N-1 */
    switch (fork()) {
      case -1:
        perror("Forks:");
        exit(1);
      case 0:
        setpgid(0,pgid);
        for(i=0;i<j-1;i++){ /* on ferme les pipes inutiles ici*/
          close(pipes[i][0]);
          close(pipes[i][1]);
        }
        for(i=j+1;i<nbcmd-1;i++){ /* on ferme les pipes inutiles ici*/
          close(pipes[i][0]);
          close(pipes[i][1]);
        }
        close(pipes[j-1][1]);
        close(pipes[j][0]);
        dup2(pipes[j-1][0],STDIN_FILENO);
        dup2(pipes[j][1],STDOUT_FILENO);
        close(pipes[j-1][0]);
        close(pipes[j][1]);
        execvp(cmds[j][0], cmds[j]);
        exit(1);
      default:
        break;
    }
  }

  switch (fork()) { /* derniere commande N */
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,pgid);
      for(i=0;i<nbcmd-2;i++){
        close(pipes[i][0]);
        close(pipes[i][1]);
      }
      close(pipes[nbcmd-2][1]);
      dup2(pipes[nbcmd-2][0],STDIN_FILENO);
      close(pipes[nbcmd-2][0]);
      execvp(cmds[nbcmd-1][0], cmds[nbcmd-1]);
      exit(1);
    default:
      break;
  }
  for(j=0; j<nbcmd-1; j++){
    close(pipes[j][0]);
    close(pipes[j][1]);
  }

}

  for(j=0;j<nbcmd;j++){ /* formatage du nom */
    if(j>0) strcat(name," | ");
    for(i=0;cmds[j][i];i++){
      if(i>0) strcat(name," ");
      strcat(name,cmds[j][i]);
    }
  }

  if(bg){ /* ajout du job */
    strcat(name," &");
    jobs_addjob(pgid,BG, name);
  }else {
    jobs_addjob(pgid,FG, name);
    waitfg(pgid);
  }
}


void do_pipe(char *cmds[MAXCMDS][MAXARGS], int nbcmd, int bg) {
    f_pipe(cmds,nbcmd,bg);
    return;
}



/* pipe 3
else if(nbcmd==3){
  int pipe1[2], pipe2[2];
  pipe(pipe1);
  pipe(pipe2);
  switch (pgid =fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,0);
      close(pipe1[0]);
      close(pipe2[0]);
      close(pipe2[1]);
      dup2(pipe1[1],STDOUT_FILENO);
      close(pipe1[1]);
      execvp(cmds[0][0], cmds[0]);
      exit(1);
    default:
      break;
  }
  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,pgid);
      close(pipe1[1]);
      close(pipe2[0]);
      dup2(pipe1[0],STDIN_FILENO);
      dup2(pipe2[1],STDOUT_FILENO);
      close(pipe1[0]);
      close(pipe2[1]);
      execvp(cmds[1][0], cmds[1]);
      exit(1);
    default:
      break;
  }
  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,pgid);
      close(pipe1[0]);
      close(pipe1[1]);
      close(pipe2[1]);
      dup2(pipe2[0],STDIN_FILENO);
      close(pipe2[0]);
      execvp(cmds[2][0], cmds[2]);
      exit(1);
    default:
      break;
  }
  close(pipe1[0]);
  close(pipe1[1]);
  close(pipe2[1]);
  close(pipe2[0]);
}*/




/* test avec deux pipes

else if(nbcmd>=3){
  int pipe1[2], pipe2[2];
  pipe(pipe1);
  pipe(pipe2);
  switch (pgid =fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,0);
      close(pipe1[0]);
      close(pipe2[0]);
      close(pipe2[1]);
      dup2(pipe1[1],STDOUT_FILENO);
      close(pipe1[1]);
      execvp(cmds[0][0], cmds[0]);
      exit(1);
    default:
      break;
  }
  for(i=1;i<nbcmd-1;i++){
    switch (pid=fork()) {
      case -1:
        perror("Fork:");
        exit(1);
      case 0:
        setpgid(0,pgid);
        if(i%2!=0){
          close(pipe1[1]);
          close(pipe2[0]);
          dup2(pipe1[0],STDIN_FILENO);
          dup2(pipe2[1],STDOUT_FILENO);
          close(pipe1[0]);
          close(pipe2[1]);
        }else{
          close(pipe2[1]);
          close(pipe1[0]);
          dup2(pipe2[0],STDIN_FILENO);
          dup2(pipe1[1],STDOUT_FILENO);
          close(pipe2[0]);
          close(pipe1[1]);
        }
        execvp(cmds[i][0], cmds[i]);
        exit(1);
      default:
        break;
    }
  }
  switch (fork()) {
    case -1:
      perror("Fork:");
      exit(1);
    case 0:
      setpgid(0,pgid);
      if(i%2==0){
        close(pipe1[0]);
        close(pipe1[1]);
        close(pipe2[1]);
        dup2(pipe2[0],STDIN_FILENO);
        close(pipe2[0]);
      }else{
        close(pipe2[0]);
        close(pipe2[1]);
        close(pipe1[1]);
        dup2(pipe1[0],STDIN_FILENO);
        close(pipe1[0]);
      }
      execvp(cmds[i][0], cmds[i]);
      exit(1);
    default:
      break;
  }
  close(pipe1[0]);
  close(pipe1[1]);
  close(pipe2[1]);
  close(pipe2[0]);
} */
