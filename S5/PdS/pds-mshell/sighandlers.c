/* mshell - a job manager */

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>
#include <assert.h>


#include "jobs.h"
#include "common.h"
#include "sighandlers.h"

/*
 * wrapper for the sigaction function
 */
int sigaction_wrapper(int signum, handler_t * handler) {
    struct sigaction sa;
    sa.sa_handler=handler;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags=SA_RESTART;
    if(sigaction(signum,&sa,NULL)<0) {
      unix_error("Wrapper error");
    }
    return 0;
}

/*
 * sigchld_handler - The kernel sends a SIGCHLD to the shell whenever
 *     a child job terminates (becomes a zombie), or stops because it
 *     received a SIGSTOP or SIGTSTP signal. The handler reaps all
 *     available zombie children
 */
void sigchld_handler(int sig) {
  struct job_t * j;
  int status;
  pid_t pid;
  if (verbose){
      printf("sigchld_handler: entering\n");
      fflush(stdout);
  }
  while((pid= waitpid(-1,&status,WUNTRACED | WNOHANG  ))>0){
    if((j=jobs_getjobpid(pid))!=NULL ){
      if(WIFEXITED(status)){
          printf("Finished job [%d] %d %s\n", j->jb_jid, j->jb_pid, j->jb_cmdline);
          fflush(stdout);
          jobs_deletejob(pid);
      }
      else if(WIFSTOPPED(status)){
        j->jb_state= ST;
        printf("Stopped job [%d] %d %s\n", j->jb_jid, j->jb_pid, j->jb_cmdline);
        fflush(stdout);
      }
      else if(WIFSIGNALED(status)){
        printf("Deleted job [%d] %d %s\n", j->jb_jid, j->jb_pid, j->jb_cmdline);
        fflush(stdout);
        jobs_deletejob(pid);
      }
    }
  }
  if (verbose){
      printf("sigchld_handler: exiting\n");
      fflush(stdout);
  }
  return;
}

/*
 * sigint_handler - The kernel sends a SIGINT to the shell whenver the
 *    user types ctrl-c at the keyboard.  Catch it and send it along
 *    to the foreground job.
 */
void sigint_handler(int sig) {
    pid_t pid;
    if (verbose){
        printf("sigint_handler: entering\n");
        fflush(stdout);
    }
    if((pid=jobs_fgpid())>0){
      kill(-pid,SIGINT);
    }
    if (verbose){
        printf("sigint_handler: exiting\n");
        fflush(stdout);
    }
    return ;
}

/*
 * sigtstp_handler - The kernel sends a SIGTSTP to the shell whenever
 *     the user types ctrl-z at the keyboard. Catch it and suspend the
 *     foreground job by sending it a SIGTSTP.
 */
void sigtstp_handler(int sig) {
    pid_t pid;
    if (verbose){
        printf("sigtstp_handler: entering\n");
        fflush(stdout);
    }
    if((pid=jobs_fgpid())>0){
      if(kill(-pid,SIGTSTP)<0){
        unix_error("sigint handler error");
      }
    }
    if (verbose){
        printf("sigtstp_handler: exiting\n");
        fflush(stdout);
    }
    return;
}
