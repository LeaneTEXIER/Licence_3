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

all: observe multif race

observe: observe.o
	$(CC) $(LDFLAGS) -o $@ $^

multif: multif.o
	$(CC) $(LDFLAGS) -o $@ $^

race: race.o
	$(CC) $(LDFLAGS) -o $@ $^



%.o: %.c
	$(CC) $(CFLAGS) -c $<

test: multif race
	   chmod +xwrxwrxwr tests.sh
		./tests.sh

clean:
	rm -f *.o multif race observe
