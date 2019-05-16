#include <limits.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <dirent.h>
#include <stdio.h>
#include <string.h>
#include <search.h>
#include <stdlib.h>
#include <errno.h>

static int opt_apparent_size = 0;
static int opt_follow_links = 0;

int isValidName(char * name){
  /* Pour éviter les boucles */
  if(strcmp(name, ".")==0){
    return 0;
  }
  /* Pour ne pas aller en arrière */
  else if(strcmp(name, "..")==0){
    return 0;
  }
  else return 1;
}

int du_file(char * pathname){
  int size=0;
  struct stat st;
  int status;
  ENTRY e, *ep;
  char tmpKey[18];

  int accessOk= access(pathname,R_OK);
  if (accessOk!= 0) {
    perror("mdu");
    exit(errno);
  }
  if (opt_follow_links){
    /* Infos sur fichier pointé par pathname */
    status= stat(pathname, &st);
  }else{
    /* Infos sur le lien et non le fichier cible si lien symb */
    status= lstat(pathname, &st);
  }
  assert(status==0);
  sprintf(tmpKey,"%li:%li",st.st_ino,st.st_dev);
  /* Hachage pour pas reparcourir ceux déjà parcouru */
  e.key=tmpKey;
  ep=hsearch(e,FIND);

  if(ep==NULL){
    char * newKey= (char *)malloc(18);
    assert(newKey!= NULL);
    strcpy(newKey,tmpKey);
    e.key= newKey;
    hsearch(e, ENTER);
    if (opt_apparent_size) {
      size+=st.st_size;
    }else{
      size+=st.st_blocks;
    }
    if(S_ISDIR(st.st_mode)){
      struct dirent * drt;
      DIR * dossier;
      dossier=opendir(pathname);
      assert(dossier!=NULL);
      while ((drt=readdir(dossier))) {
        if (isValidName(drt->d_name)) {
          char xpathname[PATH_MAX+1];
          snprintf(xpathname,PATH_MAX,"%s/%s",pathname,drt->d_name);
          size+=du_file(xpathname);
        }
      }
    }
    printf("%-8i %s\n",size, pathname);
  }
  return size;
}

void usage (char *prog) {
   printf("usage : %s [bL] file\n", prog);
}

int main (int argc, char **argv) {
   int ch;
   while ((ch = getopt(argc, argv, "Lb")) != -1) {
      switch (ch) {
      case 'L':
         opt_follow_links = 1;
         break;
      case 'b':
         opt_apparent_size = 1;
         break;
      default:
        usage(argv [0]);
      }
   }
   argc -= optind;
   argv += optind;
   if(argc!=1){
     usage("./mdu");
     exit(1);
   }
	 hcreate(20);
   du_file(*argv);
   return 0;
}
