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

mpipe: mpipe.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c
	$(CC) $(CFLAGS) -c $<

test: mtail
	   chmod +xwrxwrxwr tests.sh
		./tests.sh

clean:
	rm -f *.o mtail tailtest.txt mtailtest.txt
