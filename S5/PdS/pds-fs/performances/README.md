#   Léane TEXIER et Lamine ZENATI
#   Performances des entrées/sorties

Avant d'utiliser la commande ./mcat-scd , il faut initialiser au préalable une variable d'environnement `$MCAT_BUFSIZ` contenant la taille du buffer à utiliser en octet.

Les tests se font sur un fichier de 250Mo qui est généré en lançant la commande `make test` (cela peut prendre quelques secondes/minutes).


On remarque en faisant l'experience sur plusieurs fichiers de différentes tailles, que la taille du buffer optimale converge vers les 4096 octets.
