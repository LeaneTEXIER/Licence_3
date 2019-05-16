#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <assert.h>
#include <pthread.h>


unsigned long compteur_gc(char *bloc, unsigned long taille) {
    unsigned long i, cptr = 0;
    for (i = 0; i < taille; i++)
        if (bloc[i] == 'G' || bloc[i] == 'C')
            cptr++;
    return cptr;
}

struct gc_s {
  char * bloc;
  unsigned long taille;
  unsigned long res;
};

void * mt_compteur_gc(void * arg){
  struct gc_s *arg_p= (struct gc_s *) arg;
  arg_p->res= compteur_gc(arg_p->bloc, arg_p->taille);
  return NULL;
}

unsigned long compteur_gc2( char* bloc, unsigned long taille, int nb_threads){
    if(nb_threads==0){
      return compteur_gc(bloc,taille);
    }
    else {
      int taille_souschaine= taille/nb_threads;
      struct gc_s arg_p;
      pthread_t thread;
      unsigned long r1,r2;
      arg_p.bloc=bloc;
      arg_p.taille=taille_souschaine;
      assert(pthread_create(&thread,NULL,mt_compteur_gc,(void *)&arg_p)==0);
      r1= compteur_gc2(bloc+taille_souschaine,taille-taille_souschaine,nb_threads-1);
      pthread_join(thread,NULL);
      r2= arg_p.res + r1;
      return r2;
    }
}



int main(int argc, char *argv[]) {
    struct stat st;
    int fd;
    char *tampon;
    int lus;
    int nb_th;
    unsigned long cptr = 0;
    off_t taille = 0;
    struct timespec debut, fin;

    assert(argc > 1);

    /* Quelle taille ? */
    assert(stat(argv[1], &st) != -1);
    tampon = malloc(st.st_size);
    assert(tampon != NULL);

    /* Chargement en mémoire */
    fd = open(argv[1], O_RDONLY);
    assert(fd != -1);
    while ((lus = read(fd, tampon + taille, st.st_size - taille)) > 0)
        taille += lus;
    assert(lus != -1);
    assert(taille == st.st_size);
    close(fd);

    nb_th=16;
    if(argc>=3)
      nb_th =atoi(argv[2]);
    /* Calcul proprement dit */
    assert(clock_gettime(CLOCK_MONOTONIC, &debut) != -1);
    cptr = compteur_gc2(tampon, taille, nb_th);
    assert(clock_gettime(CLOCK_MONOTONIC, &fin) != -1);

    /* Affichage des résultats */
    printf("Nombres de GC:   %ld\n", cptr);
    printf("Taux de GC:      %lf\n", ((double) cptr) / ((double) taille));

    fin.tv_sec  -= debut.tv_sec;
    fin.tv_nsec -= debut.tv_nsec;
    if (fin.tv_nsec < 0) {
        fin.tv_sec--;
        fin.tv_nsec += 1000000000;
    }
    printf("Durée de calcul: %ld.%09ld\n", fin.tv_sec, fin.tv_nsec);
    printf("(Attention: très peu de chiffres après la virgule sont réellement significatifs !)\n");

    return 0;
}










/* main pour les tests



int main(int argc, char *argv[]) {
    struct stat st;
    int fd;
    char *tampon;
    int lus;
    unsigned long cptr = 0;
    off_t taille = 0;
    struct timespec debut, fin;

    assert(argc > 1);


    assert(stat(argv[1], &st) != -1);
    tampon = malloc(st.st_size);
    assert(tampon != NULL);


    fd = open(argv[1], O_RDONLY);
    assert(fd != -1);
    while ((lus = read(fd, tampon + taille, st.st_size - taille)) > 0)
        taille += lus;
    assert(lus != -1);
    assert(taille == st.st_size);
    close(fd);




    int nb_th =atoi(argv[2]);

    float somme_temps=0;
    int j=10;
    for (int i=0; i<j; i++){
      assert(clock_gettime(CLOCK_MONOTONIC, &debut) != -1);
      cptr = compteur_gc2(tampon, taille, nb_th);
      assert(clock_gettime(CLOCK_MONOTONIC, &fin) != -1);
      fin.tv_sec  -= debut.tv_sec;
      fin.tv_nsec -= debut.tv_nsec;
      if (fin.tv_nsec < 0) {
          fin.tv_sec--;
          fin.tv_nsec += 1000000000;
      }
      somme_temps+=fin.tv_sec;
      somme_temps+=fin.tv_nsec*0.000000001;
    }



    printf("Nombres de GC:   %ld\n", cptr);
    printf("Taux de GC:      %lf\n", ((double) cptr) / ((double) taille));

    float duree = somme_temps/j;
    printf("Durée de calcul: %f\n", duree);
    printf("(Attention: très peu de chiffres après la virgule sont réellement significatifs !)\n");

    {
      FILE * f= fopen("res.dat","a+");
      fprintf(f,"%ld %i %f\n",taille,nb_th,duree);

    }
    return 0;
}
*/
