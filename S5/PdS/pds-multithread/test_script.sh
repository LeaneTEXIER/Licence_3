echo "taille_genome nb_threads temps" > res.dat;
  for taille_genome in `awk 'BEGIN { for( i=100; i<=1000000000; i*=10 ) print i }'`; do
    ./aleazard $taille_genome > /tmp/genome.txt;
    for nb_threads in `awk 'BEGIN { for( i=1; i<=32; i*=2 ) print i }'`; do
    ./compteur-gc /tmp/genome.txt $nb_threads;
  done
done
