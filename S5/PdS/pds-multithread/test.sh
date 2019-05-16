for taille_genome in `awk 'BEGIN { for( i=0; i<=20; i+=1 ) print i }'`; do
  ./tri-rapide -tcavl 24 -n 3 tri.txt
done
