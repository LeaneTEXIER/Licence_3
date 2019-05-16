# Les règles de compilation peuvent utiliser les variables suivantes
# Consultez la documentation de make pour en savoir plus (et en
# trouver d’autres)
#
# $@  Le nom de la cible
# $<   Le nom de la première dépendance
# $^   La liste des dépendances

CC      = gcc

CFLAGS  = -Wall -Wextra -ansi -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g

mcat-scd: mcat-scd.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

test: mcat-scd
	chmod +x ./test.sh
	dd if=/dev/zero of=/tmp/bigfile count=20000 bs=4096
	./test.sh
	gnuplot mcat.gp


clean:
	rm -f *.o mcat-scd mcat-tm.dat bigfile
