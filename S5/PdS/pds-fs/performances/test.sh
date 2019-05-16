#! /bin/sh -uf
# mcat -- campagne d'appels à mcat-scd
#
# Exemple de script shell, à compléter pour vos tests

# La commande à tester
MCAT=./mcat-scd
# le fichier à lui mettre en entrée
MCAT_INPUT=/tmp/bigfile
# Le fichier de temps à générer
TIME_FILE=mcat-tm.dat

# la commande gnu time
TIME_CMD="/usr/bin/time"
# les options de cette commande


# initialisation du fichier de résultats
rm -f $TIME_FILE && echo "# bufsize real user sys" > $TIME_FILE

for size in `awk 'BEGIN { for( i=1; i<=8388608; i*=2 ) print i }'`; do
    export MCAT_BUFSIZ=$size
    TIME_OPT="-f $MCAT_BUFSIZ %e %U %S"
    echo $MCAT_BUFSIZ
    $TIME_CMD "$TIME_OPT" $MCAT $MCAT_INPUT > /dev/null 2>> $TIME_FILE
done
